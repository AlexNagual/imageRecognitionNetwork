package com.nagual.irnet.model.network.boofcv;

import boofcv.abst.scene.ImageClassifier;
import boofcv.factory.scene.ClassifierAndSource;
import boofcv.factory.scene.FactoryImageClassifier;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.Planar;
import com.nagual.irnet.model.utils.FileUtil;
import deepboof.io.DeepBoofDataBaseOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class BoofCVNetwork {
  Logger logger = LoggerFactory.getLogger(BoofCVNetwork.class);

  private ClassifierAndSource vgg_cifar10 = FactoryImageClassifier.vgg_cifar10();
  private ClassifierAndSource nin_imagenet = FactoryImageClassifier.nin_imagenet();
  private final String CLASSIFIER_PATH = "download_data";
  private List<String> categories;
  private ImageClassifier classifier;

  public BoofCVNetwork() {
    init();
  }

  private void init() {
    classifier = getClassifier(CLASSIFIER_PATH, nin_imagenet);
    categories = classifier != null ? classifier.getCategories() : Collections.emptyList();
  }
/*
  public String predict(String urlImage) {
    try {
      URL url = new URL(urlImage);
      classifier.classify(getImage(url));
      return categories.get(classifier.getBestResult());
    } catch (MalformedURLException e) {
      System.out.println("Во время загрузки изображения произошла ошибка");
    }
    return "";
  }
*/
  public String predict(String photoStream) {
    InputStream inputStream = new ByteArrayInputStream(photoStream.getBytes(StandardCharsets.UTF_8));
    classifier.classify(getImage(inputStream));
    return categories.get(classifier.getBestResult());
  }

  private Planar<GrayF32> getImage(InputStream inputStream) {
    File file = FileUtil.inputStreamToFile(inputStream, "photo.bmp");
    BufferedImage buffered = UtilImageIO.loadImage(file.getName());
    file.delete();
    Planar<GrayF32> image = new Planar<>(GrayF32.class, buffered.getWidth(), buffered.getHeight(), 3);
    ConvertBufferedImage.convertFromPlanar(buffered,image,true,GrayF32.class);
    return image;
  }

  private Planar<GrayF32> getImage(URL url) {
    BufferedImage buffered = UtilImageIO.loadImage(url);
    Planar<GrayF32> image = new Planar<>(GrayF32.class, buffered.getWidth(), buffered.getHeight(), 3);
    ConvertBufferedImage.convertFromPlanar(buffered,image,true,GrayF32.class);
    return image;
  }

  private ImageClassifier getClassifier(String filePath, ClassifierAndSource classifierName) {
    logger.info("Начинаем загружать модель");
    File file = DeepBoofDataBaseOps.downloadModel(classifierName.getSource(),new File(filePath));
    ImageClassifier<Planar<GrayF32>> classifier = classifierName.getClassifier();
    try {
      classifier.loadModel(file);
      logger.info("Модель загружена");
      return classifier;
    } catch (IOException e) {
      logger.info("Не удалось загрузить модель");
    }
    return null;
  }

  public ImageClassifier getClassifier() {
    return classifier;
  }

  public void setClassifier(ImageClassifier classifier) {
    this.classifier = classifier;
  }
}
