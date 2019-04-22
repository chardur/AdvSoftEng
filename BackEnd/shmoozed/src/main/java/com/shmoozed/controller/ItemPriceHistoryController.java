package com.shmoozed.controller;

import java.util.List;

import com.shmoozed.model.ItemPriceHistory;
import com.shmoozed.service.ItemPriceHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin // Allow All CORS Requests. See https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
@RestController
@RequestMapping(path = "/itemhistory")
public class ItemPriceHistoryController {

  private Logger logger = LoggerFactory.getLogger(ItemPriceHistoryController.class);

  private ItemPriceHistoryService itemPriceHistoryService;

  @Autowired
  public ItemPriceHistoryController(ItemPriceHistoryService itemPriceHistoryService) {this.itemPriceHistoryService = itemPriceHistoryService;
  }

  /**
   * Gets a single {@link ItemPriceHistory} based on the provided ID
   *
   * @param itemId The ID of the item to retrieve
   * @return The Item with the matching ID. Returns a Not Found response if no item with that
   * ID is found.
   */
  @GetMapping(
    path="/{item_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<List<ItemPriceHistory>> getItemHistory(@PathVariable("item_id") int itemId) {
    logger.debug("Request for item. itemId={}", itemId);

    return itemPriceHistoryService.getItemPriceHistories(itemId)
      .map(i -> new ResponseEntity<>(i, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping(
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<ItemPriceHistory> insertNewItemHistory(@RequestHeader("Authorization") String token,
                                                          @RequestBody ItemPriceHistory itemPriceHistory) {
    logger.debug("Request to add new itemPriceHistory. token={}, itemPriceHistory={}", token, itemPriceHistory);

    ItemPriceHistory newItemPriceHistory = itemPriceHistoryService.insertNewItemHistory(itemPriceHistory);

    return new ResponseEntity<>(newItemPriceHistory, HttpStatus.OK);
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<List<ItemPriceHistory>> getAll(@RequestHeader("Authorization") String token) {
    logger.debug("Request to get all itemPriceHistory. token={}", token);

    List<ItemPriceHistory> priceHistories = itemPriceHistoryService.getAll();

    return new ResponseEntity<>(priceHistories, HttpStatus.OK);
  }
}

