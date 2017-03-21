//reads in Mnist data and creates pairs of activations and outputs 
//for use in our network

public class MnistReader
{
    static SingleData[] images;
    static MnistFile label;
    static MnistFile image;

    
    public static SingleData[] getData(String[] fileNames, int num){
       //gets data from *label and *image filenames stored as string
       //num is the number of Mnist images we would like to load
       //if num==0, we will load all the images. otherwise we will load "num" images
       
       label = new MnistFile(fileNames[0]);
       image = new MnistFile(fileNames[1]);
       buildDataSet(num);
       return images;
    }
    
    public static SingleData[] getData(String[] fileNames){
       //wrapper getData, used to load all images in our file
       
       return getData(fileNames, 0);
       
    }
    
     private static void buildDataSet(int num) {
         
         int numOfImages; //number of images in our file
         int numberOfRows; //number of rows and columns each image has
         int numberOfColumns;
         
         //mnst data files are formatted as follows...
         //For Image Files:
         //first 4 bytes are "magic number" thrown away (used to confirm correct file is used)
         //next 4 bytes are the integer number of images
         //next 4 bytes are the integer number of pixels per row of image
         //next 4 bytes are the integer number of pixels per column of image
         //each remaining byte is the value of a pixel between [0,255), stored row-wise
         //
         //Label Files
         //first 4 bytes are "magic number" thrown away (used to confirm correct file is used)
         //next 4 bytes are the integer number of images (should match from image file)
         //each remaining byte is the ideal numerical value of the image [0,p]
         
            //gets our info from the image file
            int magicNumber = image.fetch(4); 
            numOfImages = image.fetch(4);
            numberOfRows = image.fetch(4);
            numberOfColumns = image.fetch(4);
            
            //gets our info from the label file
            magicNumber = label.fetch(4);
            numOfImages = label.fetch(4);
            
            //the number of images we would like to load
            int imagesToLoad;
            if(num==0)
            imagesToLoad = numOfImages;
            else
            imagesToLoad = num;
            
            //array to store our data in
            images = new SingleData[imagesToLoad];
            
            //loads our images activation and output and stores it into our array
            for(int i = 0;i<imagesToLoad;i++){
               
                //prints out our progress in 10% increments
                if(i%(imagesToLoad/10)==0){
                System.out.println("Loading Images "+ i+ " of "+imagesToLoad);
                }
                
                //activation of our next image
                double[] activation  = new double[numberOfRows*numberOfColumns];
                for(int j = 0;j<activation.length;j++)
                        activation[j] = ((double)image.fetch(1))/255;
                        
                //the number our image is supposed to represent
                int imageNumber = label.fetch(1);
                
                //creates our output based on the number of our image
                double[] output = new double[10];
                for(int q = 0;q<output.length;q++)
                    output[q] = 0;
                output[imageNumber] = 1;
                
                //stores our image
                images[i] = new SingleData(activation,output);
                    }
                        
            image.close();      
            label.close();     
           System.out.println("Images Loaded");
        
    }
    
    
     public static void printImage(SingleData a){
     int i = 0;
     int width = 28;
     for(int blerg = 0;blerg<width;blerg++){
     for(int index = 0;index<width;index++){
        if(a.activation[i]>.8)
            System.out.print("X");
        else if(a.activation[i]>.6)
            System.out.print("0");
        else
            System.out.print(" ");
        i++;
        }
     System.out.println();
    }
    int solution = 0;
    for(int q =0;q<a.output.length;q++)
        if(a.output[q]>0)    
            solution = q;
    System.out.println("Mnist classifies this as a "+solution);
    }


}
