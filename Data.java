public class Data
{
    
    public static SingleData[] training_data; //data to train the neural net on
    public static SingleData[] validation_data; //used to validate
    public static SingleData[] test_data; //used to test our networks accuracy
    
     public Data(SingleData[] all_data)
    {
        //shuffle the data 
        all_data = shuffle(all_data);
        
        //size of the test and validation sets
        int smallSetSize = all_data.length/7;
        
        //validation data is 1/7 of our total data
        //test data is 1/7 of our total data
        //training_data is 5/7 (the rest of) our data
        
        validation_data = subset(0,smallSetSize,all_data);
        test_data = subset(smallSetSize,smallSetSize*2,all_data);
        training_data = subset(smallSetSize*2, all_data.length,all_data);
        
        all_data = null;
            
        
    }
    
    public static SingleData[] subset(int start, int finish, SingleData[] input){
        //returns slice of our input array
        // the slice begins at index "start" (inclusive), and goes up to but do not include index "finish"
        SingleData[] temp = new SingleData[finish-start];
        int index = 0;
        for(int i = start;i<finish;i++){
            temp[index] = new SingleData(input[i]);
            index++;}
       return temp;
    }
    
    public static SingleData[] shuffle(SingleData[] input){
       //returns a new array of data, which is the input array shuffled
       SingleData[] output = new SingleData[input.length];
       //deep copy of the input array
       for(int i = 0;i<output.length;i++)
        output[i] = new SingleData(input[i]);
       
        
        for(int i = output.length-1;i>0;i--){
            SingleData temp = new SingleData(output[i]);
            int random = (int)(Math.random()*i);
            output[i] = new SingleData(output[random]);
            output[random] = temp;
        }
        
        return output;
    }
    
}