package com.shmoozed.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.shmoozed.model.BestBuyItem;
import com.shmoozed.model.BuyerItem;
import com.shmoozed.model.Item;
import com.shmoozed.model.ItemPriceHistory;
import com.shmoozed.model.WalmartItem;
import com.shmoozed.remote.WalmartClient;
import com.shmoozed.repository.WalmartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalmartService {
  private Logger logger = LoggerFactory.getLogger(WalmartService.class);
  private WalmartClient walmartClient;
  private WalmartRepository walmartRepository;
  private ItemService itemService;
  private ItemPriceHistoryService itemPriceHistoryService;
  private BuyerSellerItemsService buyerSellerItemsService;

  @Autowired
  public WalmartService(WalmartClient walmartClient, WalmartRepository walmartRepository, ItemService itemService,
                        ItemPriceHistoryService itemPriceHistoryService,
                        BuyerSellerItemsService buyerSellerItemsService) {
    this.walmartClient = walmartClient;
    this.walmartRepository = walmartRepository;
    this.itemService = itemService;
    this.itemPriceHistoryService = itemPriceHistoryService;
    this.buyerSellerItemsService = buyerSellerItemsService;
  }

  public WalmartItem getItemById(int itemId) {
    logger.debug("Attempting to find walmart item by id on Walmart.com itemId={}", itemId);
    return walmartClient.getItemById(itemId);
  }

  public List<WalmartItem> searchWalmartSiteForItem(String searchTerm){
    logger.debug("Attempting to search walmart API for searchTerm={}", searchTerm);
    return walmartClient.searchWalmartSiteForItem(searchTerm);
  }

  public WalmartItem insertNewWalmartItem(WalmartItem walmartItem) {
    logger.debug("Attempting to insert walmartItem={}", walmartItem);
    logger.trace("walmartRepository={}", walmartRepository);

    //get or create the walmart item
    WalmartItem newWalmartItem = getOrCreateWalmartItem(walmartItem, 1);
    logger.debug("New walmartItem inserted into database. newWalmartItem={}", newWalmartItem);

    //insert item price history
    ItemPriceHistory newItemPriceHistory = insertItemPriceHistory(newWalmartItem);
    logger.debug("New ItemPriceHistory inserted into database. newItemPriceHistory={}", newItemPriceHistory);

    return newWalmartItem;
  }

  public WalmartItem insertNewWalmartItemWithBuyerInfo(WalmartItem walmartItemIn, int quantity, BigDecimal price,
                                                       int userId) {
    logger.debug("Attempting to insert walmartItem={}", walmartItemIn);
    logger.trace("walmartRepository={}", walmartRepository);

    //get or create the walmart item
    WalmartItem newWalmartItem = getOrCreateWalmartItem(walmartItemIn, quantity);
    logger.debug("New walmartItem inserted into database. newWalmartItem={}", newWalmartItem);

    //insert item price history
    ItemPriceHistory newItemPriceHistory = insertItemPriceHistory(newWalmartItem);
    logger.debug("New ItemPriceHistory inserted into database. newItemPriceHistory={}", newItemPriceHistory);

    //insert buyer item
    BuyerItem buyerItem = getOrCreateBuyerItem(newWalmartItem.getLinkedItemId(), price, userId);
    logger.debug("New buyerItem inserted into database. buyerItem={}", buyerItem);

    return newWalmartItem;
  }

  private BuyerItem getOrCreateBuyerItem(int itemId, BigDecimal price, int userId){
    Optional<BuyerItem> buyerItem = buyerSellerItemsService.getBuyerItemByItemIdAndUserId(itemId, userId);
    return buyerItem.orElseGet(() -> insertBuyerItem(itemId, price, userId));
  }

  private WalmartItem getOrCreateWalmartItem(WalmartItem walmartItem, int quantity){
    Optional<WalmartItem> existingWalmartItem = walmartRepository.findById(walmartItem.getItemId());

    if (existingWalmartItem.isPresent()) {
      logger.debug("WalmartItem with walmart itemId={} already exists", walmartItem.getItemId());
      return existingWalmartItem.get();
    }
    else {
      logger.debug("WalmartItem with walmart itemId={} does not yet exist. Creating new WalmartItem",
                   walmartItem.getItemId());

      //create the item
      Item newItem = insertItem(walmartItem, quantity);
      logger.debug("New Item inserted into database. newItem={}", newItem);
      walmartItem.setLinkedItemId(newItem.getId());
      //save walmart item
      WalmartItem newWalmartItem = walmartRepository.save(walmartItem);
      logger.debug("New walmartItem inserted into database. newWalmartItem={}", newWalmartItem);
      return newWalmartItem;
    }
  }

  private Item insertItem(WalmartItem walmartItem, int quantity){
    Item item = new Item();
    item.setName(walmartItem.getName());
    item.setQuantity(quantity);
    return itemService.insertNewItem(item);
  }

  public List<Item> convertToItem(List<WalmartItem> walmartItems){
    List<Item> Items = new ArrayList<>();
    for(WalmartItem walmartItem : walmartItems)
    {
      Item item = convertToItem(walmartItem);
      Items.add(item);
    }
    return Items;
  }

  public Item convertToItem(WalmartItem walmartItem){
    Item item = new Item();
    item.setName(walmartItem.getName());
    item.setQuantity(1);
    return item;
  }

  private BuyerItem insertBuyerItem(int itemId, BigDecimal price, int userId){
    BuyerItem buyerItem = new BuyerItem();
    buyerItem.setItemId(itemId);
    buyerItem.setPrice(price);
    buyerItem.setUserId(userId);
    return buyerSellerItemsService.insertNewBuyerItemIfNotAlreadyPresent(buyerItem);
  }

  private ItemPriceHistory insertItemPriceHistory(WalmartItem walmartItem){
    ItemPriceHistory itemPriceHistory = new ItemPriceHistory();
    itemPriceHistory.setItemId(walmartItem.getLinkedItemId());
    itemPriceHistory.setPrice(new BigDecimal(walmartItem.getSalePrice()));
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    itemPriceHistory.setDate(timestamp);
    itemPriceHistory.setLastUpdateDate(timestamp);
    return itemPriceHistoryService.insertNewItemHistory(itemPriceHistory);
  }

  public WalmartItem findWalmartItemByUrl(String theUrl) {
    //https://www.walmart.com/ip/PAW-Patrol-Paw-Patrol-Ultimate-Rescue-Fire-Truck-with-Extendable-2-ft-Tall-Ladder/814913483
    logger.debug("in getItemByUrl theUrl={}", theUrl);
    int itemId = 0;
    try {
      String path = theUrl.substring(theUrl.lastIndexOf("/") + 1);
      if(path.indexOf('?') > -1){
        path = path.substring(0,path.indexOf('?'));
      }
      if(path.indexOf('/') > -1){
        path = path.substring(0,path.indexOf('/'));
      }
      if(path.indexOf('\"') > -1){
        path = path.substring(0,path.indexOf('\"'));
      }
      logger.debug("in getItemByUrl path={}", path);
      itemId = Integer.parseInt(path);
      logger.debug("in getItemByUrl itemId={}", itemId);
    }
    catch (Exception e ){
      //in case we cannot get the id from the url, will trap and return item 0
    }
    return walmartClient.getItemById(itemId);
  }

  private String decode(String value) {
    try {
      return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

  public void refreshAllItems() {
    walmartRepository.findAllByOrderByItemId().stream()
      .map(walmartItem -> walmartClient.getItemById(walmartItem.getItemId()))
      .filter(this::itemExistedInWalmartApi) // Filter out any items which had an error when calling Walmart API
      .peek(this::updateWalmartItemInDatabase)
      .forEach(this::insertItemPriceHistory);
  }

  private boolean itemExistedInWalmartApi(WalmartItem walmartItem) {
    // If walmart erturned a 4XX level error, the resulting WalmartItem will have an ID of 0 and should be filtered out
    boolean itemWasFound = walmartItem.getItemId() != 0;

    if (!itemWasFound) {
      logger.warn("Item Not Found in Walmart API");
    }

    return itemWasFound;
  }

  private void updateWalmartItemInDatabase(WalmartItem refreshedWalmartItem) {
    logger.debug("Updating walmart item. refreshedWalmartItem={}", refreshedWalmartItem);

    WalmartItem existingWalmartItem = walmartRepository.findById(refreshedWalmartItem.getItemId())
      .orElseThrow(IllegalArgumentException::new);

    logger.debug("Found existing walmart item. existingWalmartItem={}", existingWalmartItem);

    // Make sure that the linked item remains the same
    refreshedWalmartItem.setLinkedItemId(existingWalmartItem.getLinkedItemId());

    walmartRepository.save(refreshedWalmartItem);
  }

  public List<WalmartItem> convertBestBuyToWalmart(List<BestBuyItem> bestBuyItems)
  {
    if(bestBuyItems == null){
      return Collections.emptyList();
    }
    List<WalmartItem> walmartItems = new ArrayList<WalmartItem>();
    for(BestBuyItem bbi : bestBuyItems){
      WalmartItem walmartItem = new WalmartItem();
      walmartItem.setName(bbi.getName());
      walmartItem.setMsrp(bbi.getRegularPrice());
      walmartItem.setSalePrice(bbi.getSalePrice());
      walmartItem.setThumbnailImage(bbi.getThumbnailImage());
      walmartItem.setLargeImage(bbi.getLargeImage());
      walmartItem.setModelNumber(bbi.getModelNumber());
      walmartItem.setAddToCartUrl(bbi.getAddToCartUrl());
      walmartItem.setUpc(bbi.getUpc());
      walmartItems.add(walmartItem);
    }
    return walmartItems;
  }
}
