package com.shmoozed.service;

import java.util.ArrayList;
import java.util.List;

import com.shmoozed.model.BestBuyItem;
import com.shmoozed.model.Item;
import com.shmoozed.model.WalmartItem;
import com.shmoozed.remote.WalmartClient;
import com.shmoozed.repository.WalmartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
  private Logger logger = LoggerFactory.getLogger(SearchService.class);
  private WalmartService walmartService;
  private BestBuyService bestBuyService;

  @Autowired
  public SearchService(WalmartService walmartService, BestBuyService bestBuyService) {
    this.walmartService = walmartService;
    this.bestBuyService = bestBuyService;
  }
  public List<WalmartItem> search(String searchTerm){
    logger.debug("Attempting to search all vendors for searchTerm={}", searchTerm);
    List<WalmartItem> walmartItems = walmartService.searchWalmartSiteForItem(searchTerm);
    List<BestBuyItem> bestBuyItems = bestBuyService.searchBestBuySiteForItem(searchTerm);
    logger.debug("bestBuyItems search results bestBuyItems={}", bestBuyItems);
    if (bestBuyItems != null) {
      walmartItems.addAll(walmartService.convertBestBuyToWalmart(bestBuyItems));
    }
    return walmartItems;
  }
}


