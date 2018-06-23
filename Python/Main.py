import numpy as np
import struct


def loadData():
  # reads the MNIST data, splits it up into three sections
  # Training Data is an array with 50k units, each unit containing one image (784 inputs for 28*28px) and an expected output for the image
  # validationData and testData are formatted the same, but each only contains 10,000 units

  fNameImages = "../data/t10k-images.idx3-ubyte"
  fNameLabels = "../data/t10k-labels.idx1-ubyte"
  imageCount = 10000
  
  with open(fNameImages, 'rb') as fid:
        magic, num, rows, cols = struct.unpack(">IIII", fid.read(16))
        images = np.fromfile(fid, dtype=np.uint8).reshape(imageCount, rows, cols)
  
  with open(fNameLabels,'rb') as fid:
    magic, num = struct.unpack(">II", fid.read(8))
    labels = np.fromfile(fid, dtype=np.int8)
  
  tupules = np.array(list(zip(images,labels)))
  return tupules  



    