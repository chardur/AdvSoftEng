package com.shmoozed.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

/**
 * Handles logging request and response attributes for requests made with {@link RestTemplate}.
 */
@Component
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor, Ordered {

  private static final String STANDARD_LOG_PATTERN =
    "method={} duration={} status={} uri={} request_headers=\"{}\"";

  private static final String STANDARD_LOG_PATTERN_WITH_RESPONSE_HEADERS =
    STANDARD_LOG_PATTERN + " response_headers=\"{}\"";

  private static final String STANDARD_EXCEPTION_LOG_PATTERN_WITH_EXCEPTION_MESSAGE =
    STANDARD_LOG_PATTERN + " exception=\"{}\"";

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public ClientHttpResponse intercept(final HttpRequest request, final byte[] requestbody,
                                      final ClientHttpRequestExecution execution) throws IOException {
    final StopWatch stopWatch = new StopWatch();
    ClientHttpResponse response = null;
    IOException exceptionToLog = null;

    stopWatch.start();
    try {
      response = execution.execute(request, requestbody);
    }
    catch (IOException ex) {
      exceptionToLog = ex;
      throw ex;
    }
    finally {
      stopWatch.stop();
      if (response != null) {
        warnRequestResponseExecution(request, stopWatch.getTotalTimeMillis(), response);
      }
      if (exceptionToLog != null) {
        warnRequestResponseException(request, stopWatch.getTotalTimeMillis(), exceptionToLog);
      }
    }

    return response;
  }

  private void warnRequestResponseExecution(HttpRequest request, long duration, ClientHttpResponse response)
    throws IOException {

    logger.debug(STANDARD_LOG_PATTERN_WITH_RESPONSE_HEADERS,
                request.getMethod(), duration, response.getStatusCode(), request.getURI(),
                request.getHeaders(), response.getHeaders());
  }

  private void warnRequestResponseException(HttpRequest request, long duration, IOException exception) {
    logger.warn(STANDARD_EXCEPTION_LOG_PATTERN_WITH_EXCEPTION_MESSAGE,
                request.getMethod(), duration, "exception", request.getURI(), request.getHeaders(),
                exception.getClass().getName() + ':' + exception.getMessage());
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
