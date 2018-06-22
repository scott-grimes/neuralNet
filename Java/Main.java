
public class Main
{
    public static void main(String[] args)
    {
     //network sizes    
     int[] sizes = new int[] {784,30,10}; 
     
     //our label and image MNIST data files
     String[] fileNames = {"../data/t10k-labels.idx1-ubyte","../data/t10k-images.idx3-ubyte"}; 
     
     
     //Data data = new Data(MnistReader.getData(fileNames,5000)); //load the first 5000 images
      Data data = new Data(MnistReader.getData(fileNames,0)); //loads all images
     
    
     //create a new network with random weights and biases
     Network network = new Network(sizes);
     
     //train our network on our test data
     network.SGD(data.training_data, 30, 10, 3.0, data.test_data);
     
     //export our network for later use and testing!
     try{
     network.export("mynetwork.txt");
    }catch(Exception e){System.out.println(e);};
       
    
     //imports our saved network
     //prints out a digit and our networks guess as to what the digit is
     try{
         
     Network network2 = new Network("mynetwork.txt");
     SingleData test_image = data.test_data[0];
     MnistReader.printImage(test_image);
     int guess = Util.Max(network2.feedforward(test_image.activation));
     System.out.println("Network thinks this is a "+guess);
     
        }catch(Exception e){System.out.println(e);};
    }
}
