package com.shmoozed.controller;

import java.math.BigDecimal;
import java.util.List;

import com.shmoozed.model.WalmartItem;
import com.shmoozed.service.WalmartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin // Allow All CORS Requests. See https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
@RestController
@RequestMapping(path = "/walmart")
public class WalmartController {

  private Logger logger = LoggerFactory.getLogger(WalmartController.class);

  private WalmartService walmartService;
  //private ItemService itemService;
  //private ItemPriceHistoryService itemPriceHistoryService;

  @Autowired
  public WalmartController(WalmartService walmartService) { //}, ItemService itemService, ItemPriceHistoryService itemPriceHistoryService) {

    this.walmartService = walmartService;
    //this.itemService = itemService;
    //this.itemPriceHistoryService = itemPriceHistoryService;
  }

  @GetMapping(
    path="/itemid/{itemid}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<WalmartItem> getItemById(@PathVariable("itemid") int itemId) {
    logger.debug("Request for item by id. itemId={}", itemId);
    WalmartItem walmartItem = walmartService.getItemById(itemId);
    return new ResponseEntity<>(walmartItem, HttpStatus.OK);
  }

  @GetMapping(
    path="/search/{searchterm}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<List<WalmartItem>> searchWalmartSiteForItem(@PathVariable("searchterm") String searchTerm){
    logger.debug("Request to search for searchTerm={}", searchTerm);
    List<WalmartItem> walmartItems = walmartService.searchWalmartSiteForItem(searchTerm);
    return new ResponseEntity<>(walmartItems, HttpStatus.OK);

  }

  @PostMapping(
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<WalmartItem> insertNewWalmartItem(@RequestHeader("Authorization") String token,
                                                          @RequestBody WalmartItem walmartItem) {
    logger.debug("Request to add new walmartItem. token={}, walmartItem={}", token, walmartItem);

    WalmartItem newWalmartItem = walmartService.insertNewWalmartItem(walmartItem);

    return new ResponseEntity<>(newWalmartItem, HttpStatus.OK);
  }

  @PostMapping(
    value = "/url",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<WalmartItem> addItemByUrl(@RequestHeader("Authorization") String token,
                                                                @RequestBody String url) {
    logger.debug("Request to add walmart item by url. token={}, url={}", token, url);
    WalmartItem newWalmartItem = walmartService.insertNewWalmartItem(walmartService.findWalmartItemByUrl(url));
    return new ResponseEntity<>(newWalmartItem, HttpStatus.OK);
  }

  @PostMapping(
    value = "/urlbuyerdetails",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<WalmartItem> addItemByUrlWithBuyerDetails(@RequestHeader("Authorization") String token,
                                                                @RequestBody String url, @RequestParam("quantity") int quantity, @RequestParam("userId") int userId, @RequestParam("price") BigDecimal price) {
    logger.debug("Request to add walmart item by url with quantity and price details, with . token={}, url={}, quantity={}, price{}= userId={}", token, url, quantity, price, userId);
    WalmartItem newWalmartItem = walmartService.insertNewWalmartItemWithBuyerInfo(walmartService.findWalmartItemByUrl(url), quantity, price, userId);
    return new ResponseEntity<>(newWalmartItem, HttpStatus.OK);
  }
}
