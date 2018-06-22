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
  
def backprop(self,a,expectedOut):
  # backpropegation for our network
  # a in a single activation
  # aOut is the output of a

  #creates a new set biases and weights initalized at zero
  nablaB = [np.zeros(b.shape) for b in self.biases]
  nablaW = [np.zeros(w.shape) for w in self.weights]

  #activations stores each layers output
  #inputs stores each layers inputs
  activation = a
  inputs = [a]

  # list of z vectors
  zs = []

  # feedforward our input through the neural net, storing each 
  # layers inputs and outputs into inputs and activations
  for b,w in zip(self.biases, self.weights):
    z = np.dot(w,activation)+b
    zs.append(z)
    activation = sigmoid(z)
    inputs.append(activation)
  
  # compute error of last layer
  delta = self.costDerivative(activations[-1], expectedOut) * sigmoidPrime(zs[-1])
  nablaB[-1] = delta
  nablaW[-1] = np.dot( delta, activations[-2].transpose() )

  # backprop our error through the network, storing the changes in weights and biases
  # layer by layer

  for i in xrange(2,self.numLayers):
    z = zs[-i]
    sp = sigmoidPrime(z)
    delta = np.dot(self.weights[-i+1].transpose() , delta ) * sp
    nablaB[-i] = delta
    nablaW[-i] = np.dot( delta, activations[-i-1].transpose() )

  # returns the gradient of our weights and biases
  return (nablaB, nablaW)

def updateMiniBatch(self, miniBatch, eta):
  # Update the network's weights and biases by applying
  # gradient descent using backpropagation to a single mini batch.
  # The "miniBatch" is an array of data sets, and the eta which is
  # is the learning rate.

  # empty set of weights and biases
  nablaB = [np.zeros(b.shape) for b in self.biases]
  nablaW = [np.zeros(w.shape) for w in self.weights]

  # add dnb to nb element-wise, add dnw to nw element-wise
  for a, aOut in miniBatch:
    deltaNablaB, deltaNablaW = self.backprop(a,aOut)
    nablaB = [ nb+dnb for nb, dnb in zip(nablaB, deltaNablaB)]
    nablaW = [ nw+dnw for nw, dnw in zip(nablaW, deltaNablaW)]
  

  # compute our new biases and weights
  diff = eta/len(miniBatch)

  # store new weights and biases
  self.weights = [w-diff*nw for w, nw in zip(self.weights, nablaW))]
  self.biases = [b-diff*nw for b, nb in zip(self.biases, nablaB)]

  def costDerivative(self, aOut, expectedOut):
    # computes the cost derivative for our function by
    # subtracting the actual answer from our desired output element-wise
    # returns a new vector

    return (aOut-expectedOut)

  def evaluate(self, testData):
    # evaluates our network on an array of test_data
    # returns the number of correct solutions obtained
    testResults = [(np.argmax(self.feedforward( a ), expectedOut) ) for (a, expectedOut) in testData]
    return sum( int(actualOut==expectedOut ) for (actualOut, expectedOut ) in testResults

def sigmoud(z):
    return 1.0/(1.0+np.exp(-z))

def sigmoidPrime(z):
    return sigmoid(z)*(1-sigmoid(z))
  
