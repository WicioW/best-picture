package com.project.bestpicture.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class HttpRequestUtil {

    public static String getRequest(String url){
      HttpResponse<String> response = null;
      try{
          response = HttpClient
                  .newBuilder()
                  .build()
                  .send(java.net.http.HttpRequest.newBuilder().uri(URI.create(url))
                  .build(), HttpResponse.BodyHandlers.ofString());
      }catch (IOException | InterruptedException e){
          e.printStackTrace();
      }
      return  response != null ? response.body() : "";
    }
}
