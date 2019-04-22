package com.shmoozed.remote;

import java.util.List;

import com.shmoozed.controller.RestTemplateResponseErrorHandler;
import com.shmoozed.model.BestBuyItem;
import com.shmoozed.model.BestBuyItemResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BestBuyClient {
    private Logger logger = LoggerFactory.getLogger(BestBuyClient.class);
    private final RestTemplate restTemplate;

    private static final String API_KEY = "<BEST_BUY_API_KEY>";
    private static final String API_BASE_URL = "https://api.bestbuy.com/";
    private static final String V1_SEARCH_URL = "/v1/products({searchmap})?format=json&apiKey={api_key}";

    //https://api.bestbuy.com/v1/products(search=xbox)?format=json&apiKey=<BEST_BUY_API_KEY>

    public BestBuyClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .rootUri(API_BASE_URL)
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

  /*public BestBuyItem getItemById(int itemId) {
    logger.debug("Attempting to retrieve bestbuy item from API by id itemId={}", itemId);
    return restTemplate.getForObject(V1_SEARCH_URL, BestBuyItem.class, itemId, API_KEY);
  }*/

    public List<BestBuyItem> searchBestBuySiteForItem(String searchTerm)
    {
        logger.debug("Attempting to search bestbuy API for searchTerm={}", searchTerm);

        String bbSearchTerm = ""; // searchTerm;
        for(String s : searchTerm.split("%20")){
            if(bbSearchTerm == "")
            {
                bbSearchTerm = "search=" + s;
            }
            else
            {
                bbSearchTerm += "&serach=" + s;
            }
        }
        logger.debug("Merged search term bbSearchTerm={}", bbSearchTerm);

        BestBuyItemResponse bestBuyItemResponse = restTemplate.getForObject(V1_SEARCH_URL, BestBuyItemResponse.class, bbSearchTerm, API_KEY);
        logger.debug("bestBuyItemResponse={}", bestBuyItemResponse.getProducts());
        return bestBuyItemResponse.getProducts();
    }

}
