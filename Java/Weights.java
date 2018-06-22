public class Weights{
    
    //weights is a 3-d matrix representing the weights between nodes in two different layers
    //for example weights[1] stores the weights connecting the second and third layers
    //(since layer 0 is the first layer)
    
    //let weights[1] be matrix w. w[j][k] stores the weight between the kth neuron in the 
    //second layer and the jth neuron in the third layer
    
    double weights[][][];
    public Weights(int[] s, boolean rnd){
        weights = new double[s.length-1][][];
        
        for(int num  =  0;num<weights.length;num++)
            weights[num] = new double[s[num+1]][s[num]];
        
        for(int num = 0;num<weights.length;num++)
            weights[num] = Util.fillMatrix(weights[num],rnd);
    }
    
    public void print(){
        for(int i = 0;i<weights.length;i++){
            System.out.println("Weights Layer "+i);
                for(int j = 0;j<weights[i].length;j++){
                    for(int k = 0;k<weights[i][j].length;k++){
                        System.out.println("Layer "+(i)+" node "+k+", layer "+(i+1)+" node "+j+" "+weights[i][j][k]);
                    }
                    System.out.println();
                }
           System.out.println();
        }
        
    }
    
}