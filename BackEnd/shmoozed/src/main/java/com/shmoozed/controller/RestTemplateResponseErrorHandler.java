package com.shmoozed.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

  private Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    return (httpResponse.getStatusCode().series() == CLIENT_ERROR
        || httpResponse.getStatusCode().series() == SERVER_ERROR);
  }

  @Override
  public void handleError(ClientHttpResponse httpResponse) throws IOException {
    if (httpResponse.getStatusCode().series() == SERVER_ERROR) {
      // handle SERVER_ERROR
      logger.error("Server Error Occurred in RestTemplate call. httpResponse={}", httpResponse);
    }
    else if (httpResponse.getStatusCode().series() == CLIENT_ERROR) {
      // handle CLIENT_ERROR
      logger.warn("Client Error Occurred in RestTemplate call. httpResponse={}", httpResponse);
      if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
        try {
          throw new ChangeSetPersister.NotFoundException();
        }
        catch (ChangeSetPersister.NotFoundException e) {
          logger.error("Not Found Exception", e);
        }
      }
    }
  }
}