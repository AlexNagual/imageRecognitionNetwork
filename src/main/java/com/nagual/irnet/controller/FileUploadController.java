package com.nagual.irnet.controller;

import com.nagual.irnet.model.network.boofcv.BoofCVObject;
import com.nagual.irnet.model.pojo.Image;
import com.nagual.irnet.model.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Controller
public class FileUploadController {
  private StorageService storageService;

  public FileUploadController(StorageService storageService) {
    this.storageService = storageService;
  }

  @PostMapping("/")
  public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {
    storageService.store(file);
    File file1 = FileUtil.convert(file);
    Image image = new Image();
    image.setPhoto(file.getOriginalFilename());
    image.setPrediction(BoofCVObject.getInstance().getBoofCVNetwork().predict(file1));

    return image.getPrediction();
  }

}
