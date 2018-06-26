package com.nagual.irnet.controller;

import com.nagual.irnet.model.network.NNObject;
import com.nagual.irnet.model.pojo.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PredictController {
  Logger logger = LoggerFactory.getLogger(PredictController.class);

  @RequestMapping(value = "/predict", method = RequestMethod.GET)
  public String greeting() {
    return "Predict";
  }

  @RequestMapping(value="/predict", method=RequestMethod.POST)
  @ResponseBody
  public Image addUser(@ModelAttribute("url") Image image) {
    if (image != null && !image.getUrl().isEmpty()) {
      image.setPrediction(NNObject.getInstance().getNeuralNetwork().predict(image.getUrl()));
      logger.info("Success for image: " + image.getUrl());
      return image;
    } else {
      logger.info("Fail");
      return null;
    }

  }
}
