package com.nagual.irnet.model.network.DL4J;

public class DL4JObject {
  private static DL4JObject instance = new DL4JObject();
  private DL4JNetwork dl4JNetwork;

  public void init() {
    dl4JNetwork = new DL4JNetwork();
  }

  public static DL4JObject getInstance() {
    return instance;
  }

  public DL4JNetwork getDl4JNetwork() {
    return dl4JNetwork;
  }

  public void setDl4JNetwork(DL4JNetwork dl4JNetwork) {
    this.dl4JNetwork = dl4JNetwork;
  }
}
