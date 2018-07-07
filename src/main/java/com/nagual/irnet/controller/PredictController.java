package com.nagual.irnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PredictController {
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String greeting() {
    return "Index";
  }
}
