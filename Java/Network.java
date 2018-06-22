import java.text.DecimalFormat;
import java.io.*;
public class Network{
    // sizes is an array containing the number of nodes in each layer
    // of the network. sizes[0] is the input layer
    
    int[] sizes;
    int num_layers;
    Biases biases;
    Weights weights;
    
    //when running the network, if test is True, display the accuracy 
    //of the network during each epoch of testing
    //this significantly slows performance!
    boolean test;
    
    //test_data is used to test the accuracy of our network
    SingleData[] test_data; 
    
     public Network(int[] sizes){
        //sizes is an array representing the number of notes in each layer
        //sizes[0] is the activation layer
        //weights is a three-dimenstional matrix representing the weight between each node in our network
        //biases is a two-dimensional matrix representing the bias of each node in our network
        //see the Biases and Weights class for more information
        
        this.sizes = sizes;
        num_layers = sizes.length;
        biases = new Biases(sizes, true);   //if true, use random values for our initial weights/bias values
        weights = new Weights(sizes, true);
        test = false;
             
    }
    
    public double[] feedforward(double[] a){
       //a is the input to our network for the activation layer
       //the output of each layer becomes the input for each successive layer.
       //an individual nodes output is calculated as sigmoid(weights*input_previous_layer+node_bias);
       
       //java is persnictity. for matrix manipulation to work correctly with a vector we must define our vector
       //a as a[n][1], not as a[n] 
       //the utility class converts between these two array shapes as needed
       
       //converts input a[n] into a two-dimensional array with shape double[n][1]
       double[][] output = Util.arrayToVect(a);
      
       //feedforward our input through the neural net      
       for(int i = 0;i<num_layers-1;i++){
       double[][] wa = Util.dot(weights.weights[i],output); 
       double[][] z = Util.matAdd(wa,biases.biases[i]);
       output = sigmoid(z);
        }
        
        
     //converts the output of our final layer back into a single dimensional array
     double[] out1 = Util.vectToArray(output);
     return out1;
    }
    
    public Gradient backprop(SingleData a){
        //backpropegation for our network
        
        //creates a new set biases and weights initalized at zero
        Biases new_b = new Biases(sizes,false);
        Weights new_w = new Weights(sizes,false);
        double[][] nb = new_b.biases;
        double[][][] nw = new_w.weights;
        
        //activation and output of our data stored
        double[] activation = a.activation;
        double[] y = a.output;
        
        //activations stores each layers output
        //inputs stores each layers inputs
        double[][] activations = new double[num_layers][];
        double[][] inputs = new double[num_layers-1][];
        activations[0] = activation;
        
       //convert input activation[n] into a two-dimensional array with sizes double[n][1]
       double[][] output = Util.arrayToVect(activation);
       
       //feedforward our input through the neural net, storing each 
       //layers inputs and outputs into inputs and activations
       for(int i = 0;i<num_layers-1;i++){
       double[][] wa = Util.dot(weights.weights[i],output);
       double[][] layer_input = Util.matAdd(wa,biases.biases[i]);
       inputs[i] = Util.vectToArray(layer_input);
       output = sigmoid(layer_input);
       activations[i+1] = Util.vectToArray(output);
        }
        
        
       //compute error of last layer
       double[] delta = cost_derivative(activations[activations.length-1],y);
       delta = Util.hadamard(delta,sigmoid_prime(inputs[inputs.length-1]));
       nb[nb.length-1] = Util.copyArr(delta);
       nw[nw.length-1] =  Util.dot(
                            Util.arrayToVect(delta),
                            Util.transpose(Util.arrayToVect(activations[activations.length-2])));
                            
      //backprop our error through the network, storing the changes in weights and biases
      //layer by layer
     for(int L = 2;L<num_layers;L++){
         double[] z = inputs[inputs.length-L];
         double[] sp = sigmoid_prime(z);
         double[][] transposed = Util.transpose(weights.weights[weights.weights.length-L+1]);
         
         delta = Util.vectToArray(Util.dot(transposed,delta));
         delta = Util.hadamard(delta,sp);
         
         nb[nb.length-L] = Util.copyArr(delta);
         nw[nw.length-L] = Util.dot(
                                    Util.arrayToVect(delta),
                                    Util.transpose(Util.arrayToVect(activations[activations.length-L-1])));
         
          }
     
     //returns the gradient of our weights and biases
     return (new Gradient(nb,nw));
    }
    
    public void update_mini_batch(SingleData[] mini_batch, double eta){
       //Update the network's weights and biases by applying
       //gradient descent using backpropagation to a single mini batch.
       //The "mini_batch" is an array of data sets, and a double eta which is
       //is the learning rate.
       
       //empty set of biases and weights
       Biases nb = new Biases(sizes,false);
       Weights nw = new Weights(sizes,false);
       
       
       for(SingleData i : mini_batch){
           Gradient temp = backprop(i);
           
           //add d_nb to nb element-wise, add d_nw to nw element-wise
           nb.biases = Util.matAdd(nb.biases,temp.biases);
           nw.weights = Util.matAdd(nw.weights,temp.weights);
       
        }
       
        //compute our new biases and weights
       double diff = eta/((double)mini_batch.length);
       
       //saves our new weights
        nw.weights = Util.matMult(nw.weights,-diff);
        weights.weights = Util.matAdd(weights.weights,nw.weights);
        
        //saves our new biases
        nb.biases = Util.matMult(nb.biases,-diff);
        biases.biases = Util.matAdd(biases.biases,nb.biases);
        
    }
    
   
    
    public double[] cost_derivative(double[] output_activation, double[] y){
        //computes the cost derivative for our function by
        //subtracting the actual answer from our desired output element-wise
        //returns a new vector
        
        double[] answer = new double[y.length];
        
        for(int i = 0;i<y.length;i++)
            answer[i] = output_activation[i]-y[i];
        return answer;
    }
    
    public void SGD(SingleData[] training_data, int epochs, int mini_batch_size, double eta){
        //stociatric gradient descent function (eta is the learning rate)
        
        //n_test is the length of our test_data (only used if we wish to display
        //the accuracy of each epoch of testing
        int n_test = 0;
        if(test){
            n_test = test_data.length;
        }
        
        
        int n = training_data.length;
        int numOfMiniBatches = n/mini_batch_size;
            
        for(int i = 0;i<epochs;i++){
            
          training_data =  Data.shuffle(training_data);
          int start = 0;
          
          for(int j = 0;j<numOfMiniBatches;j+=mini_batch_size){
            SingleData[] mini_batch = Data.subset(start,start+mini_batch_size,training_data);
            update_mini_batch( mini_batch , eta);
            start += mini_batch_size;
        }
            
        //if we have test data, display how accurate our network is once every epoch
        if(test){
            double correct = evaluate(test_data);
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            System.out.println("Epoch "+i+": "+ (int)correct+"/"+n_test+" : "+decimalFormat.format(correct/((double)n_test)*100)+"%");
        }
        else{
            System.out.println("Epoch "+i+" Complete ");
        }
            
        }
        
        System.out.println("Training Complete");
            
        }
    
        

    public void SGD(SingleData[] training_data, int epochs, int mini_batch_size, double eta, SingleData[] test_data){
        //wrapper to call SGD and display accuracy of our network at the same time
        //slows performance significantly, only useful for debugging!
        
        this.test = true;
        this.test_data = test_data;
        
        SGD(training_data,epochs,mini_batch_size,eta);
        
        this.test = false;
        this.test_data = null;
        
    }

    public double evaluate(SingleData[] test_data){
        //evaluates our network on an array of test_data
        //returns the number of correct solutions obtained
        
        double correct = 0;
        for(SingleData i : test_data){
            double[] answer = feedforward(i.activation);
          
            if(Util.Max(answer) == Util.Max(i.output))
                correct++;
                
        }
        return correct;
    }
    
    public static double sigmoid(double z){
        //computs the sigmoid function
        return 1.0/(1.0+Math.exp(-z));
        
    }
    
    
    public static double[][] sigmoid(double[][] input){
        //computes the sigmoid function on a vector with shape input[n][1]
        double[][] output = new double[input.length][1];
        for(int i = 0;i<input.length;i++){
            
            output[i][0] = sigmoid(input[i][0]);
            
        }
       return output;
    }
    
    public static double[] sigmoid(double[] input){
        //computes the sigmoid function on a vector with shape input[n]
        double[] output = new double[input.length];
        for(int i = 0;i<input.length;i++){
            
            output[i] = sigmoid(input[i]);
            
        }
       return output;
    }
    
    public static double[] sigmoid_prime(double[] z){
        //computes sigmoid_prime on our vector elementwise
        //vector has a shape z[size][1]
        // sig_prime = sig*(1-sig)
        double[] a = sigmoid(z);
        double[] b = Util.matMult(sigmoid(z),-1);
        b = Util.matAdd(b,1);
        
        return Util.hadamard(a,b);
    }
    
    
    
    public void export(String fileName) throws IOException {
        //exports our networks biases and weights
        //the first line of the file is our networks sizes
        //the next lines are our networks biases, layer-by-layer
        //the following lines are our networks weights, layer by layer and node by node
        
        //file to write network info to
        File file = new File(fileName); 
      
         // creates the file
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));

       //first line of the file is Sizes
       for(int i : sizes)
       writer.write(i+" ");
       writer.newLine();
       
       //next set of lines is biases, layer by layer
       for(double[] layer : biases.biases)
       {
           for(double node : layer){
               writer.write(node+ " ");
            }
            writer.newLine();
        }
        
       //finially, print out each set of weights, layer by layer, node by node
       for(double[][] between : weights.weights){
           for(double[] layer : between){
               for(double node : layer){
                  writer.write(node+" ");
                }
                writer.newLine();
            }
           
        }
             writer.flush();
             writer.close();
       System.out.println("Network Exported Successfully");      
    }
    
    public Network(String fileName) throws IOException{
       //imports  our networks biases and weights
        //the first line of the file is our networks sizes
        //the next lines are our networks biases, layer-by-layer
        //the following lines are our networks weights, layer by layer and node by node
        
        //reader pointed at our file
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        
            //read in the first line, which is our sizes
            String line = br.readLine();
            sizes = new int[line.split(" ").length];
            for(int i = 0;i<sizes.length;i++)
                sizes[i] = Integer.parseInt(line.split(" ")[i]);
            
            
        this.sizes = sizes;
        this.num_layers = sizes.length;
        
        //creates a blank set of weights and biases for our network
        biases = new Biases(sizes, false);
        weights = new Weights(sizes, false);
        
        
            //read in and store the biases from our file line by line
            for(int i = 0;i<biases.biases.length;i++){
                String[] splitup = br.readLine().split(" ");
                double[] temp = new double[splitup.length];
                for(int j = 0;j<temp.length;j++){
                    temp[j] = Double.parseDouble(splitup[j]);
                }
                biases.biases[i] = temp;
            }
            
            
            //read in and store the weights of our network layer by layer, node by node
            for(int base = 0;base<weights.weights.length;base++){
                double[][] between = new double[sizes[base+1]][];
                for(int i = 0;i<between.length;i++){
                    String[] splitup = br.readLine().split(" ");
                    double[] temp = new double[splitup.length];
                    for(int j = 0;j<splitup.length;j++){
                        temp[j] = Double.parseDouble(splitup[j]);
                        //System.out.print(splitup[j]+" ");
                    }
                    between[i] = temp;
                    //System.out.println();
                }
                weights.weights[base] = between;
            }
            br.close();
            
            System.out.println("Network Imported Successfully");
    }
    
}