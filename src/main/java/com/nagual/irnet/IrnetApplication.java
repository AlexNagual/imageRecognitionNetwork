package com.nagual.irnet;

import com.nagual.irnet.model.network.NNObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IrnetApplication {

  public static void main(String[] args) {
    NNObject.getInstance().init();
    SpringApplication.run(IrnetApplication.class, args);
  }
}
