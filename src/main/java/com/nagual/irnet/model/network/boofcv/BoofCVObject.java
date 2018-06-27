package com.nagual.irnet.model.network.boofcv;

public class BoofCVObject {
  private static BoofCVObject instance = new BoofCVObject();
  private BoofCVNetwork boofCVNetwork;

  public void init() {
    boofCVNetwork = new BoofCVNetwork();
  }

  public static BoofCVObject getInstance() {
    return instance;
  }

  public BoofCVNetwork getBoofCVNetwork() {
    return boofCVNetwork;
  }
}
