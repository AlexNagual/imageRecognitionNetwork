package com.nagual.irnet.model.utils;

import java.io.*;

public class FileUtil {
  public static File inputStreamToFile(InputStream inputStream, String filename) {
    try {
      OutputStream os = new FileOutputStream(filename);
      byte[] buffer = new byte[1024];
      int bytesRead;
      //read from is to buffer
      while((bytesRead = inputStream.read(buffer)) !=-1){
        os.write(buffer, 0, bytesRead);
      }
      inputStream.close();
      //flush OutputStream to write any buffered data to file
      os.flush();
      os.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new File(filename);
  }
}
