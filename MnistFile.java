import java.io.*;
import java.nio.ByteBuffer;

public class MnistFile
{
   FileInputStream inputFile;

    public MnistFile(String fileName)
    {
       try{
        inputFile = 
                new FileInputStream(fileName);
            }catch(IOException ex) {
            System.out.println("error opening file");              
        }       
    }
    
public int fetch(int num){
        //returns the integer represented by the next *num of bytes in the stream
        
        byte[] temp = new byte[num];
        if(num!=1){
         try {
        inputFile.read(temp);
    }catch(IOException ex) {
            ex.printStackTrace();
        }
        ByteBuffer bb = ByteBuffer.wrap(temp);
        return bb.getInt();
    }
    
    
     try {
        inputFile.read(temp);
    }catch(IOException ex) {
            ex.printStackTrace();
        }
        return (temp[0] & 0xff);
    }
    
    public void close(){
        try{
        inputFile.close();
    }catch(IOException ex){
        ex.printStackTrace();
    }
    }
}
