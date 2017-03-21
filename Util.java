import java.util.Random;
/*
 * A series of utility functions to assist with our network software.
 * Many of these functions perform matrix-related operations for which
 * Java has no build-in functions
 */
public class Util{
    
    static Random rnd = new Random();
    
    public Util(){
        //initalizes a random variable for to use later
        rnd = new Random();
    }
    
      
    public static double[][] hadamard(double[][] a, double[][] b){
        //calculates the hadamard product of two vectors a[size][1] and b[size][1]
        //returns a new vector
        
        double[] a_2 = vectToArray(a);
        double[] b_2 = vectToArray(b);
        
        double[] out = hadamard(a_2,b_2);
        
        return arrayToVect(out);
        
    }
    
    public static double[] hadamard(double[] a, double[] b){
        //calculates the hadamard product of two arrays a and b 
        //returns a new vector
        double[] out = new double[a.length];
        for(int i = 0;i<out.length;i++)
            out[i] = a[i]*b[i];
       
        return out;
    }
    
    public static double[][] arrayToVect(double[] input){
        //converts a 1-d array into a 2-d vector (for easier matrix manipulation in java)
        //input[size] into output[size][1]
        double[][] output = new double[input.length][1];
       for(int i=0;i<input.length;i++)
            output[i][0] = input[i];
       return output;
    }
    
    public static double[] vectToArray(double[][] input){
         //converts a 2-d vector into a 1-d array (for easier matrix manipulation in java)
        //input[size][1] into output[size]
        double[] output = new double[input.length];
     for(int i = 0;i<input.length;i++)
        output[i] = input[i][0];
        
        return output;
    }
    
    public static double getRand(){
        //generates a random number
        return rnd.nextGaussian();
        
    }
    
    
    public static double[] fillArray(double a[], boolean rnd){
        //if rnd is true, fills an array with random numbers
        //if rnd is false, fills the array with zeroes
        double[] b = new double[a.length];
        for(int i = 0;i<a.length;i++)
            if(rnd)
            b[i] = getRand();
            else
            b[i] = 0;
        
        return b;
    }
    
    public static double[][] fillMatrix(double a[][], boolean rnd){
        //if rnd is true, fills a matrix with random numbers
        //if rnd is false, fills the matrix with zeroes
        double[][] b = new double[a.length][];
        for(int i = 0;i<a.length;i++)
            b[i] = fillArray(a[i],rnd);
        
        return b;
    }
  
    public static double[][][] fillMatrix(double a[][][], boolean rnd){
        //if rnd is true, fills a matrix with random numbers
        //if rnd is false, fills the matrix with zeroes
        double[][][] b = new double[a.length][][];
        for(int i = 0;i<a.length;i++)
            b[i] = fillMatrix(a[i],rnd);
            
        return b; 
    }
   
    
    public static double[][] dot(double[][] a, double[] b){
        //multiplies a matrix by an array
        //java is persnickity, b must be a size b[n][1] instead of just b[n]
        
        return dot( a , arrayToVect(b) );
    }
    
    public static double[][] dot(double[][] a, double[][] b){
        //returns the dot product of two matrices a and b
        
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        double[][] c = new double[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }
    
    public static double[] copyArr(double[] input){
        //creates a deep copy of an array
        
        double[] output = new double[input.length];
        
        for(int i = 0;i<output.length;i++)
            output[i] = input[i];
        
        return output;
    }
    
    public double[][] copyArr(double[][] input){
        //creates a deep copy of a 2d array with input[size][1]
        double[][] output = new double[input.length][1];
        
        for(int i = 0;i<output.length;i++)
            output[i][0] = input[i][0];
        
        return output;
        
    }
   
    public static double[] matAdd(double[] a , double b){
        //adds *b to *a element-wise
        //returns a new array
         double[] output = new double[a.length];
        for(int i = 0;i<output.length;i++)
            output[i] = a[i]+b;
        
        return output;
        
    }
    
    public static double[] matAdd(double[] a, double[] b){
        //adds two arrays together element-by-element, returns the new array
        
        double[] output = new double[a.length];
        for(int i = 0;i<output.length;i++)
            output[i] = a[i]+b[i];
        
        return output;
    }
    
    public static double[][] matAdd(double[][] a, double b){
        //adds two arrays together element-by-element, returns the new array
        
        double[][] output = new double[a.length][];
        for(int i = 0;i<output.length;i++)
            output[i] = matAdd(a[i],b);
        
        return output;
    }
    
    public static double[][] matAdd(double[][] a, double[][] b){
        //adds two matrices together element-wise, a and b must have the same shape
        
        double[][] output = new double[a.length][];
        for(int i = 0;i<output.length;i++){
            output[i] = new double[a[i].length];
            for(int j = 0;j<output[i].length;j++){
                output[i][j] = a[i][j]+b[i][j];
            }
        }
        return output;
        
    }
    
    public static double[][][] matAdd(double[][][] a, double[][][] b){
        //adds two 3-d matrices together element-wise, a and b must have the same shape
        double[][][] output = new double[a.length][][];
        for(int i = 0;i<output.length;i++){
            output[i] = matAdd(a[i],b[i]);
        }
        return output;
    }
    
    public static double[][] matAdd(double[][] a, double[] b){
        //wrapper method to add two arrays together element-wise
        //shape of the arrays is a[size][1] and b[size]
        double[][] output = new double[a.length][1];
        for(int i = 0;i<output.length;i++)
            output[i][0] = a[i][0]+b[i];
        
        return output;
    }
    
    public static double[][][] matAdd(double[][][] a, double b){
        //wrapper method to add number b to matrix a element-wise
        double[][][] output = new double[a.length][][];
        for(int i = 0;i<output.length;i++)
            output[i] = matAdd(a[i],b);
        
        return output;
    }
    
    public static int Max(double[] a){
        //returns the index of the largest value in a
        int index = 0;
        double max_val = -1;
        for(int i=0;i<a.length;i++)
            if(a[i]>max_val){
                index = i;
                max_val = a[i];
            }
        return index;        
    }
        
        public static double[][] transpose(double[][] input)
    {   //returns a new matrix, which is the transposed matrix of input
        int m = input.length;
        int n = input[0].length;
    
        double[][] output = new double[n][m];
    
        for(int x = 0; x < n; x++)
        {
            for(int y = 0; y < m; y++)
            {
                output[x][y] = input[y][x];
            }
        }
    
        return output;
    }
    
        public static double[][] transpose(double[] input)
    {   //transposes a vector input[size] into output[1][size]
        
        return transpose(arrayToVect(input));
    }
    
    
    
    public static double[] matMult(double[] a, double b){
        //multiplies *a matrix by *b, element-wise
        //returns a new matrix
        double[] out = new double[a.length];
        for(int i = 0;i<a.length;i++)
            out[i] = a[i]*b;
        return out;
        
    }
    
    public static double[][] matMult(double[][] a, double b){
        //multiplies *a matrix by *b, element-wise
        //returns a new matrix
        double[][] out = new double[a.length][];
        for(int i = 0;i<a.length;i++)
        out[i] = matMult(a[i],b);
        
        return out;
    }
    
    public static double[][][] matMult(double[][][] a, double b){
        //multiplies *a matrix by *b, element-wise
        //returns a new matrix
        double[][][] out = new double[a.length][][];
        for(int i = 0;i<a.length;i++)
                out[i] = matMult(a[i],b);
        
        return out;
    }
    
    public static double[][] matMult(double[][] a, double[][]b){
       //multiplies two 2-d matrices element-wise
        double[][] out = new double[a.length][];
        for(int i = 0; i<a.length;i++){
            out[i] = new double[a[i].length];
            for(int j = 0;j<a[i].length;j++)
                    out[i][j] = a[i][j] * b[i][j];
                }
        return out; 
    }
    
    public static double[][][] matMult(double[][][] a, double[][][] b){
        //multiplies two 3-d matrices element-wise
        double[][][] out = new double[a.length][][];
        for(int i = 0; i<a.length;i++){
            out[i] = new double[a[i].length][];
            for(int j = 0;j<a[i].length;j++){
                out[i][j] = new double[a[i][j].length];
                for(int k = 0;k<a[i][j].length;k++)
                    out[i][j][k] = a[i][j][k] * b[i][j][k];
                }
            }
        return out;
    }
       
    public static void printArr(double[] a){
        //prints an array on a single line
        for(double i : a)
            System.out.print(i+" ");
        System.out.println();
    }
    
    public static void printArr(double[][] a){
        //prints a 2d-array with the shape a[size][0]
        for(int i = 0;i<a.length;i++)
            printArr(a[i]);
        System.out.println();
        
    }
    
    public static void printArr(double[][][] a){
         //prints a 3d-array with the shape a[size][0]
        for(int j = 0;j<a.length;j++){
        for(int i = 0;i<a[j].length;i++)
            printArr(a[j][i]);
        System.out.println();    
        }
        System.out.println();
        
    }
    
    
}