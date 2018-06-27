package com.nagual.irnet.model.network.DL4J;

import com.nagual.irnet.model.utils.AWSUtil;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.modelimport.keras.trainedmodels.TrainedModels;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DL4JNetwork {
  Logger logger = LoggerFactory.getLogger(DL4JNetwork.class);
  private ComputationGraph vgg16 = null;

  public DL4JNetwork() {
    loadModel();
  }
  private String awsBucket = "neural.network.models";
  private String awsFile = "vgg16_dl4j_inference.zip";

  private void loadModel() {
    try {
      /*
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("static/vgg16_dl4j_inference.zip").getFile());
      vgg16 = ModelSerializer.restoreComputationGraph(file);
      */
      InputStream is = AWSUtil.getObject(awsBucket, awsFile).getObjectContent();
      logger.info("Начинаем загружать модель из потока");
      vgg16 = ModelSerializer.restoreComputationGraph(is);
      is.close();
      logger.info("Модель загружена");
    } catch (IOException e) {
      logger.info("Не удалось загрузить модель", e);
    }
  }

  public String predict(String imageUrl) {
    try {
      NativeImageLoader loader = new NativeImageLoader(229, 229, 3);
      URL url = new URL(imageUrl);
      InputStream input = url.openStream();
      INDArray image = loader.asMatrix(input);
      input.close();
      INDArray[] output = vgg16.output(false, image);
      return TrainedModels.VGG16.decodePredictions(output[0]);
    } catch (IOException e) {
      System.out.println("Во время загрузки изображения произошла ошибка");
    }
    return "";
  }

}
