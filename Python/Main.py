import ImportMnist
import NeuralNet

trainingData, validationData, testData = ImportMnist.loadData()
net = NeuralNet.NeuralNet([784, 30, 10])
net.SGD(trainingData, 30, 10, 3.0, testData)
