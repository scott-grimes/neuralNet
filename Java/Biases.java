
public class Biases
{
    //Biases is a matrix where each row denotes a layer in the matrix
    //and each column represents the bias of a node in the layer
    //layer[0] has no biases because the first layer is the input layer
    
    double biases[][];
    
  
    
    public Biases(int [] sizes, boolean rnd){
        //creates new biases, fills them with number a
        //note! biases[2] is for the second layer of our network!
        //layer 1 does not have any bias! it is just the input layer
        biases = new double[sizes.length-1][];
        
        for(int i = 0;i<biases.length;i++)
            biases[i] = new double[sizes[i+1]];
            
        for(int i = 0;i<biases.length;i++)          //initializes each bias to a random number
            biases[i] = Util.fillArray(biases[i],rnd);      
        
    }
    
    public void print(){
        //note biases[0] is for the second layer in our network!
        //layer 1 does not have any bias! it is just the input layer
        for(int i = 0;i<biases.length;i++){
            System.out.println("Biases Layer "+(i+1));
                for(int k = 0;k<biases[i].length;k++)
                    System.out.println(biases[i][k]+" ");      
            System.out.println();
         }
    
    

}
}