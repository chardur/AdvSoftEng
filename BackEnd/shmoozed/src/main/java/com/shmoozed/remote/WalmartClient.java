package com.shmoozed.remote;

import java.util.List;

import com.shmoozed.controller.RestTemplateResponseErrorHandler;
import com.shmoozed.model.WalmartItem;
import com.shmoozed.model.WalmartItemResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WalmartClient {

  private Logger logger = LoggerFactory.getLogger(WalmartClient.class);

  private final RestTemplate restTemplate;

  private static final String API_KEY = "<WALMART_API_KEY>";
  private static final String API_BASE_URL = "http://api.walmartlabs.com";
  private static final String V1_ITEMS_URL = "/v1/items/{item_id}?format=json&apiKey={api_key}";
  private static final String V1_SEARCH_URL = "/v1/search?apiKey={api_key}&query={query}&sort=bestseller";




  public WalmartClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder
      .rootUri(API_BASE_URL)
      .errorHandler(new RestTemplateResponseErrorHandler())
      .build();
  }

  public WalmartItem getItemById(int itemId) {
    logger.debug("Attempting to retrieve walmart item from API by id itemId={}", itemId);
    return restTemplate.getForObject(V1_ITEMS_URL, WalmartItem.class, itemId, API_KEY);
  }

  public List<WalmartItem> searchWalmartSiteForItem(String searchTerm)
  {
      logger.debug("Attempting to search walmart API for searchTerm={}", searchTerm);
      WalmartItemResponse walmartItemResponse = restTemplate.getForObject(V1_SEARCH_URL, WalmartItemResponse.class, API_KEY, searchTerm);
      return walmartItemResponse.getItems();
  }

}
