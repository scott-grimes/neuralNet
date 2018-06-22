import random
import numpy as np
# sizes is an array containing the number of nodes in each layer
# of the network. sizes[0] is the input layer

class NeuralNet(object):

  def __init__(self, sizes):
    # sizes is an array representing the number of nodes in each layer
    # sizes[0] is the activation layer
    # weights is a three-dimenstional matrix representing the weight between each node in our network
    # biases is a two-dimensional matrix representing the bias of each node in our network
   
    self.numLayers = len(sizes)
    self.sizes = sizes
    self.biases = [np.random.randn( i, 1) for i in sizes[1:]]
    self.weights = [np.random.randn(j, i)  for i,j in zip(sizes[:-1], sizes[1:])]
  
  def feedForward(self, a):
    # a is the input to our network for the activation layer
    # the output of each layer becomes the input for each successive layer.
    # an individual nodes output is calculated as sigmoid(weights*input_previous_layer+node_bias)

    for bias, weight in zip(self.biases, self.weights):
        a = sigmoid(np.dot(weight, a)+bias)
    return a
  
  def SGD(self, trainingData, epochs, miniBatchSize, eta, testData = None):

    # stociatric gradient descent function (eta is the learning rate)
        
    # nTest is the length of our testData (only used if we wish to display the accuracy of each epoch of testing

    if testData: 
      nTest = len(testData)
    
    n = len(trainingData)
    for i in xrange(epochs):
      random.shuffle(trainingData)
      miniBatches = [trainingData[j:j+miniBatchSize] for j in xrange(0,n,miniBatchSize)]
      for miniBatch in miniBatches:
        self.updateMiniBatch(miniBatch,eta)
      if testData:
        pcnt = self.evaluate(testData)/nTest

        print "Epoch {0}: {1}%".format( i , round(pcnt/100))
      else:
        print "Epoch {0}: complete".format(i)
    print "Training Complete"
  
def backprop(a):
  # backpropegation for our network
  # a in a single activation
  
