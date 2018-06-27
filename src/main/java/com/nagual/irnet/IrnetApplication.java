package com.nagual.irnet;

import com.nagual.irnet.model.network.DL4J.DL4JObject;
import com.nagual.irnet.model.network.boofcv.BoofCVObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IrnetApplication {

  public static void main(String[] args) {
    SpringApplication.run(IrnetApplication.class, args);
    //DL4JObject.getInstance().init();
    BoofCVObject.getInstance().init();
  }
}
