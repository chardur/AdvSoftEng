package com.shmoozed.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.shmoozed.model.BuyerItem;
import com.shmoozed.model.DetailedBuyerItem;
import com.shmoozed.model.DetailedSellerItem;
import com.shmoozed.model.SellerItem;
import com.shmoozed.service.BuyerSellerItemsService;
import com.shmoozed.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin // Allow All CORS Requests. See https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
@RestController
@RequestMapping(path = "/item")
public class BuyerSellerItemsController {

  private Logger logger = LoggerFactory.getLogger(BuyerSellerItemsController.class);

  private BuyerSellerItemsService buyerSellerItemsService;
  private ItemService itemService;

  @Autowired
  public BuyerSellerItemsController(BuyerSellerItemsService buyerSellerItemsService,
                                    ItemService itemService) {
    this.buyerSellerItemsService = buyerSellerItemsService;
    this.itemService = itemService;
  }

  @PostMapping(
    path = "/seller",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<SellerItem> insertNewSellerItem(@RequestHeader("Authorization") String token,
                                                                @RequestBody SellerItem sellerItem) {
    logger.debug("Request to add new seller item. token={}, sellerItem={}", token, sellerItem);

    SellerItem newSellerItem = buyerSellerItemsService.insertNewSellerItemIfNotAlreadyPresent(sellerItem);

    return new ResponseEntity<>(newSellerItem, HttpStatus.OK);
  }

  @PostMapping(
    path = "/buyer",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<BuyerItem> insertNewBuyerItem(@RequestHeader("Authorization") String token,
                                                                      @RequestBody BuyerItem buyerItem) {
    logger.debug("Request to add new buyer item. token={}, buyerItem={}", token, buyerItem);

    BuyerItem newBuyerItem = buyerSellerItemsService.insertNewBuyerItemIfNotAlreadyPresent(buyerItem);

    return new ResponseEntity<>(newBuyerItem, HttpStatus.OK);
  }

  @GetMapping(
    path = "/seller",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody
  ResponseEntity<List<SellerItem>> getSellerItems(@RequestHeader("Authorization") String token) {
    logger.debug("Request to get all seller items. token={}", token);
    List<SellerItem> allSellerItems = buyerSellerItemsService.getAllSellerItems();
    return new ResponseEntity<>(allSellerItems, HttpStatus.OK);
  }

  @GetMapping(
    path = "/buyer",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody
  ResponseEntity<List<BuyerItem>> getBuyerItems(@RequestHeader("Authorization") String token) {
    logger.debug("Request to get all seller items. token={}", token);
    List<BuyerItem> allBuyerItems = buyerSellerItemsService.getAllBuyerItems();
    return new ResponseEntity<>(allBuyerItems, HttpStatus.OK);
  }

  @GetMapping(
    path = "/seller/{seller_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody
  ResponseEntity<List<SellerItem>> getSellerItemsForSeller(@RequestHeader("Authorization") String token,
                                                           @PathVariable("seller_id") String sellerId) {
    logger.debug("Request to get seller items for specific seller. token={} sellerId={}", token, sellerId);
    List<SellerItem> sellerItems =
      buyerSellerItemsService.getSellerItemsBySellerId(Integer.valueOf(sellerId));

    if (sellerItems.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(sellerItems, HttpStatus.OK);
  }

  @GetMapping(
    path = "/seller/{seller_id}/details",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody
  ResponseEntity<List<DetailedSellerItem>> getDetailedSellerItemsForSeller(@RequestHeader("Authorization") String token,
                                                                           @PathVariable("seller_id") String sellerId) {
    logger.debug("Request to get detailed seller items for specific seller. token={} sellerId={}", token, sellerId);

    // Fetch all the Seller Items
    List<SellerItem> sellerItems =
      buyerSellerItemsService.getSellerItemsBySellerId(Integer.valueOf(sellerId));

    if (sellerItems.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Fetch all DetailedSellerItems for items in the Seller Items
    List<DetailedSellerItem> detailedSellerItems = sellerItems.stream()
      .map(sellerItem -> buyerSellerItemsService.getDetailedSellerItemBySellerItemId(sellerItem.getId()))
      .filter(Objects::nonNull) // Filter out any nulls (see TODO in BuyerSellerItemsService)
      .collect(Collectors.toList());

    return new ResponseEntity<>(detailedSellerItems, HttpStatus.OK);
  }

  @GetMapping(
    path = "/buyer/{buyer_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody
  ResponseEntity<List<BuyerItem>> getBuyerItemsForBuyer(@RequestHeader("Authorization") String token,
                                                        @PathVariable("buyer_id") String buyerId) {
    logger.debug("Request to get buyer items for specific buyer. token={} buyerId={}", token, buyerId);
    List<BuyerItem> buyerItems =
      buyerSellerItemsService.getBuyerItemsByBuyerId(Integer.valueOf(buyerId));

    if (buyerItems.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(buyerItems, HttpStatus.OK);
  }

  @GetMapping(
    path = "/buyer/alert/{buyer_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody
  ResponseEntity<List<BuyerItem>> getAlertsForUser(@RequestHeader("Authorization") String token,
                                                   @PathVariable("buyer_id") String buyerId) {
    logger.debug("Request to get alert items for specific user. token={} user_Id={}", token, buyerId);
    List<BuyerItem> buyerItem = buyerSellerItemsService.getAlertsByUserId(Integer.valueOf((buyerId)));

    if (buyerItem.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(buyerItem, HttpStatus.OK);
  }

  @PostMapping(path = "/buyer/alert/{item_id}",
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE
    )
    public @ResponseBody ResponseEntity<BuyerItem> resetItemAlert(@RequestHeader("Authorization") String token,
                                                                  @RequestBody BuyerItem updateItem) {
      logger.debug("Request to reset buyer item notification. token={}, buyerItem={}", token, updateItem);

      buyerSellerItemsService.resetNotification(updateItem);

      return new ResponseEntity<>(HttpStatus.OK);
    }

  @GetMapping(
    path = "/buyer/{buyer_id}/details",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody
  ResponseEntity<List<DetailedBuyerItem>> getDetailedBuyerItemsForBuyer(@RequestHeader("Authorization") String token,
                                                                        @PathVariable("buyer_id") String buyerId) {
    logger.debug("Request to get detailed buyer items for specific buyer. token={} buyerId={}", token, buyerId);

    // Fetch all the Buyer Items
    List<BuyerItem> buyerItems =
      buyerSellerItemsService.getBuyerItemsByBuyerId(Integer.valueOf(buyerId));

    if (buyerItems.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Fetch all DetailedBuyerItems for items in the Seller Items
    List<DetailedBuyerItem> detailedBuyerItems = buyerItems.stream()
      .map(buyerItem -> buyerSellerItemsService.getDetailedBuyerItemByBuyerItemId(buyerItem.getId()))
      .filter(Objects::nonNull) // Filter out any nulls (see TODO in BuyerSellerItemsService)
      .collect(Collectors.toList());

    return new ResponseEntity<>(detailedBuyerItems, HttpStatus.OK);
  }

  @PostMapping(path = "/seller/update/{item_id}",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<SellerItem> updateSellerItem(@RequestHeader("Authorization") String token,
                                                                   @RequestBody SellerItem updateItem) {
    logger.debug("Request to update user item. token={}, sellerItem={}", token, updateItem);

    buyerSellerItemsService.updateSellerItemPrice(updateItem);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping(path = "/seller/{seller_item_id}")
  public @ResponseBody ResponseEntity<Void> deleteSellerItemById(@RequestHeader("Authorization") String token,
                                                           @PathVariable("seller_item_id") String sellerItemId) {
    logger.debug("Request to delete seller item. token={}, sellerId={}", token, sellerItemId);

    buyerSellerItemsService.deleteSellerItem(Integer.valueOf(sellerItemId));

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping(path = "/buyer/{buyer_item_id}")
  public @ResponseBody ResponseEntity<Void> deleteBuyerItemById(@RequestHeader("Authorization") String token,
                                                                 @PathVariable("buyer_item_id") String buyerItemId) {
    logger.debug("Request to delete buyer item. token={}, buyerItemId={}", token, buyerItemId);

    buyerSellerItemsService.deleteBuyerItem(Integer.valueOf(buyerItemId));

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
