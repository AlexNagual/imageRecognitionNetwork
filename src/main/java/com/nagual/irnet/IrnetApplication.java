package com.nagual.irnet;

import com.nagual.irnet.controller.StorageService;
import com.nagual.irnet.model.network.DL4J.DL4JObject;
import com.nagual.irnet.model.network.boofcv.BoofCVObject;
import com.nagual.irnet.model.storage.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class IrnetApplication {

  public static void main(String[] args) {
    SpringApplication.run(IrnetApplication.class, args);
    //DL4JObject.getInstance().init();
    BoofCVObject.getInstance().init();
  }

  @Bean
  CommandLineRunner init(StorageService storageService) {
    return (args) -> {
      storageService.deleteAll();
      storageService.init();
    };
  }
}
