package com.nagual.irnet.controller;

import com.nagual.irnet.model.network.boofcv.BoofCVObject;
import com.nagual.irnet.model.pojo.Image;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Controller
public class PredictController {
  Logger logger = LoggerFactory.getLogger(PredictController.class);

  @RequestMapping(value = "/predict", method = RequestMethod.GET)
  public String greeting() {
    return "Predict";
  }

  @RequestMapping(value="/predict", method=RequestMethod.POST)
  @ResponseBody
  public Image addUser(@ModelAttribute("photo") Image image) {
    if (image != null && !image.getPhoto().isEmpty()) {
      //image.setPrediction(DL4JObject.getInstance().getDl4JNetwork().predict(image.getPhoto()));
      //image.setPrediction(BoofCVObject.getInstance().getBoofCVNetwork().predict(image.getPhoto()));


      String d = getFile();
      image.setPrediction(BoofCVObject.getInstance().getBoofCVNetwork().predict(d));
      logger.info(String.format("На картинке url: %s обнаружен $s", image.getPhoto(), image.getPrediction()));
      return image;
    } else {
      logger.info("Не получилось :(");
      return null;
    }
  }

  String getFile() {
    try {
      InputStream initialStream = new FileInputStream("C:/Users/alexk/Pictures/bitmapInput.bmp");
      String result = IOUtils.toString(initialStream, StandardCharsets.UTF_8);
      return result;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
