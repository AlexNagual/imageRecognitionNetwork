package com.nagual.irnet.model.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;

public class AWSUtil {
  public static S3Object getObject(String bucket, String key) {
    AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
        .withRegion("us-east-1")
        .build();
    return s3Client.getObject(bucket, key);
  }
}
