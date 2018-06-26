package com.nagual.irnet.model.network;

public class NNObject {
  private static NNObject instance = new NNObject();
  private NeuralNetwork neuralNetwork;

  public void init() {
    neuralNetwork = new NeuralNetwork();
  }

  public static NNObject getInstance() {
    return instance;
  }

  public NeuralNetwork getNeuralNetwork() {
    return neuralNetwork;
  }

  public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
    this.neuralNetwork = neuralNetwork;
  }
}
