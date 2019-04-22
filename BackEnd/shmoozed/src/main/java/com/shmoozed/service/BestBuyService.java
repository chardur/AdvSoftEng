package com.shmoozed.service;

import java.util.List;

import com.shmoozed.model.BestBuyItem;
import com.shmoozed.remote.BestBuyClient;
import com.shmoozed.remote.WalmartClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BestBuyService {
  private Logger logger = LoggerFactory.getLogger(WalmartService.class);
  private BestBuyClient bestBuyClient;
  private ItemService itemService;

  @Autowired
  public BestBuyService(ItemService itemService, BestBuyClient bestBuyClient) {
    this.bestBuyClient = bestBuyClient;
    this.itemService = itemService;
  }

  public List<BestBuyItem> searchBestBuySiteForItem(String searchTerm){
    logger.debug("Attempting to search bestbuy API for searchTerm={}", searchTerm);
    return bestBuyClient.searchBestBuySiteForItem(searchTerm);
  }
}
