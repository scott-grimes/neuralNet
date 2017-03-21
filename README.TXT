---------------------------
Simple Neural Net
By: Scott Grimes
---------------------------

Implements a simple feedforward neural net in java using stochastic gradient descent
and the quadratic cost function.

This simple network demonstrates the power neural nets have in image recognition.
In just a few minutes the network can be trained to recognize digits from the MNIST
dataset with over 90% accuracy

For more reading and information about neural nets, see
https://en.wikipedia.org/wiki/Artificial_neural_network

Sample Output (run main.java)

Loading Images 0 of 10000
Loading Images 1000 of 10000
Loading Images 2000 of 10000
Loading Images 3000 of 10000
Loading Images 4000 of 10000
Loading Images 5000 of 10000
Loading Images 6000 of 10000
Loading Images 7000 of 10000
Loading Images 8000 of 10000
Loading Images 9000 of 10000
Images Loaded
Epoch 0: 419/1428 : 29.34%
Epoch 1: 586/1428 : 41.04%
Epoch 2: 701/1428 : 49.09%
Epoch 3: 795/1428 : 55.67%
Epoch 4: 966/1428 : 67.65%
Epoch 5: 1037/1428 : 72.62%
Epoch 6: 1091/1428 : 76.4%
Epoch 7: 1127/1428 : 78.92%
Epoch 8: 1112/1428 : 77.87%
Epoch 9: 1153/1428 : 80.74%
Epoch 10: 1172/1428 : 82.07%
Epoch 11: 1190/1428 : 83.33%
Epoch 12: 1182/1428 : 82.77%
Epoch 13: 1211/1428 : 84.8%
Epoch 14: 1216/1428 : 85.15%
Epoch 15: 1229/1428 : 86.06%
Epoch 16: 1225/1428 : 85.78%
Epoch 17: 1248/1428 : 87.39%
Epoch 18: 1249/1428 : 87.46%
Epoch 19: 1239/1428 : 86.76%
Epoch 20: 1261/1428 : 88.31%
Epoch 21: 1256/1428 : 87.96%
Epoch 22: 1261/1428 : 88.31%
Epoch 23: 1254/1428 : 87.82%
Epoch 24: 1272/1428 : 89.08%
Epoch 25: 1255/1428 : 87.89%
Epoch 26: 1272/1428 : 89.08%
Epoch 27: 1271/1428 : 89.01%
Epoch 28: 1289/1428 : 90.27%
Epoch 29: 1296/1428 : 90.76%
Training Complete
Network Exported Successfully
Network Imported Successfully
------------------------------
                            
                            
                            
              XX            
            XXXX            
          0XX     0         
         XX0      XX        
        XXX        X0       
       XXX         XX       
       XX           X0      
      XX0           XX      
      XX0           0X0     
      XX             X0     
      0X0            0X     
       X0            0X     
       XX            0X     
       0X0           0X     
        XX           0X     
         XX          0X     
         0XX0        XX     
          XXX0     0XX      
            XXXXXXXXX0      
             0XXXXX         
                            
                            
                            
                            
                            
Mnist classifies this as a 0
------------------------------

Network thinks this is a 0
