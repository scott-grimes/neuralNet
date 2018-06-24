import numpy as np
import struct


def loadData():
  # reads the MNIST data, splits it up into three sections
  # Training Data is an array with 1/2 of the total units, each unit containing one array with 784 elements ( representing the 28*28px image) and the expected output for the image
  # validationData and testData are formatted the same, but each only contain 1/4 of the total units

  fNameImages = "../data/t10k-images.idx3-ubyte"
  fNameLabels = "../data/t10k-labels.idx1-ubyte"
  imageCount = 10000

  #fNameImages = "../data/train-images.idx3-ubyte"
  #fNameLabels = "../data/train-labels.idx1-ubyte"
  #imageCount = 60000
  
  
  with open(fNameImages, 'rb') as fid:
        magic, num, rows, cols = struct.unpack(">IIII", fid.read(16))
        images = np.fromfile(fid, dtype=np.uint8).reshape(imageCount, rows, cols)
  
  with open(fNameLabels,'rb') as fid:
    magic, num = struct.unpack(">II", fid.read(8))
    labels = np.fromfile(fid, dtype=np.int8)
  
  images = [np.reshape(i/255.0, (784, 1)) for i in images]
  labels = [vectorize(j) for j in labels]

  tupules = np.array(zip(images,labels))
  return np.split(tupules, [len(tupules)*8/10, len(tupules)*9/10])

def vectorize(val):
  # given an integer value, returns a vectorized solution for our MNIST data. EX (3) => [0,0,0,1,0,0,0,0,0,0]
  sol = np.zeros((10, 1))
  sol[val] = 1.0
  return sol
