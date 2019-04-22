package com.shmoozed.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.shmoozed.model.BuyerItem;
import com.shmoozed.model.DetailedBuyerItem;
import com.shmoozed.model.DetailedItem;
import com.shmoozed.model.DetailedSellerItem;
import com.shmoozed.model.SellerItem;
import com.shmoozed.repository.BuyerItemRepository;
import com.shmoozed.repository.SellerItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerSellerItemsService {

  private Logger logger = LoggerFactory.getLogger(BuyerSellerItemsService.class);

  private final BuyerItemRepository buyerItemRepository;
  private final SellerItemRepository sellerItemRepository;
  private final ItemService itemService;

  @Autowired
  public BuyerSellerItemsService(BuyerItemRepository buyerItemRepository,
                                 SellerItemRepository sellerItemRepository,
                                 ItemService itemService) {

    this.buyerItemRepository = buyerItemRepository;
    this.sellerItemRepository = sellerItemRepository;
    this.itemService = itemService;

  }

  public List<SellerItem> getAllSellerItems() {
    logger.debug("Fetching all seller items");
    return (List<SellerItem>) sellerItemRepository.findAll();
  }

  public List<BuyerItem> getAllBuyerItems() {
    logger.debug("Fetching all buyer items");
    return (List<BuyerItem>) buyerItemRepository.findAll();
  }

  public List<SellerItem> getSellerItemsBySellerId(int sellerId) {
    logger.debug("Fetching all seller items for sellerId={}", sellerId);
    return (List<SellerItem>) sellerItemRepository.findSellerItemsByUserId(sellerId);
  }

  public Optional<SellerItem> getSellertemByItemIdAndUserId(int itemId, int userId) {
    logger.debug("Fetching SellerItem with itemId={} and userId={}", itemId, userId);
    return sellerItemRepository.findByItemIdEqualsAndUserIdEquals(itemId, userId);
  }

  public DetailedSellerItem getDetailedSellerItemBySellerItemId(int sellerItemId) {
    logger.debug("Fetching detailed seller item for sellerItemId={}", sellerItemId);

    Optional<SellerItem> possibleSellerItem = sellerItemRepository.findById(sellerItemId);

    if (possibleSellerItem.isPresent()) {
      SellerItem sellerItem = possibleSellerItem.get();
      Optional<DetailedItem> possibleDetailedItem = itemService.getDetailedItem(sellerItem.getItemId());

      if (possibleDetailedItem.isPresent()) {
        // TODO: What do we do about Detailed Items which only have and Item? Wouldn't we still want to return them?
        DetailedSellerItem detailedSellerItem = new DetailedSellerItem(sellerItem, possibleDetailedItem.get());
        logger.debug("Found detailed seller item for sellerItemId={} detailedSellerItem={}", sellerItemId, detailedSellerItem);
        return detailedSellerItem;
      }
      else {
        logger.warn("Seller Item found for sellerItemId={} but no detailed item found. sellerItem={}", sellerItemId, sellerItem);
      }
    }
    return null;
  }

  public List<BuyerItem> getBuyerItemsByBuyerId(int buyerId) {
    logger.debug("Fetching all buyer items for buyerId={}", buyerId);
    return (List<BuyerItem>) buyerItemRepository.findBuyerItemsByUserId(buyerId);
  }

  public Optional<BuyerItem> getBuyerItemByItemIdAndUserId(int itemId, int userId) {
    logger.debug("Fetching BuyerItem with itemId={} and userId={}", itemId, userId);
    return buyerItemRepository.findByItemIdEqualsAndUserIdEquals(itemId, userId);
  }

  public DetailedBuyerItem getDetailedBuyerItemByBuyerItemId(int buyerItemId) {
    logger.debug("Fetching detailed seller item for buyerItemId={}", buyerItemId);

    Optional<BuyerItem> possibleBuyerItem = buyerItemRepository.findById(buyerItemId);

    if (possibleBuyerItem.isPresent()) {
      BuyerItem buyerItem = possibleBuyerItem.get();
      Optional<DetailedItem> possibleDetailedItem = itemService.getDetailedItem(buyerItem.getItemId());

      if (possibleDetailedItem.isPresent()) {
        // TODO: What do we do about Detailed Items which only have and Item? Wouldn't we still want to return them?
        DetailedBuyerItem detailedBuyerItem = new DetailedBuyerItem(buyerItem, possibleDetailedItem.get());
        logger.debug("Found detailed buyer item for buyerItemId={} detailedBuyerItem={}", buyerItemId, detailedBuyerItem);
        return detailedBuyerItem;
      }

      else {
        logger.warn("Buyer Item found for buyerItemId={} but no detailed item found. buyerItem={}", buyerItemId, buyerItem);
      }
    }

    return null;
  }

  public SellerItem insertNewSellerItemIfNotAlreadyPresent(SellerItem sellerItem) {
    logger.debug("Attempting to insert sellerItem={}", sellerItem);

    // If there is already a seller item with that item id and this user id then don't insert it again.
    Optional<SellerItem> existingSellerItem =
      sellerItemRepository.findByItemIdEqualsAndUserIdEquals(sellerItem.getItemId(), sellerItem.getUserId());

    if (existingSellerItem.isPresent()) {
      logger.debug("SellerItem with itemId={} and userId={} already exists. Not attempting to insert again.");
      return existingSellerItem.get();
    }
    else {
      SellerItem newSellerItem = sellerItemRepository.save(sellerItem);
      logger.debug("New seller item inserted. sellerItem={}", newSellerItem);
      return newSellerItem;
    }
  }

  public BuyerItem insertNewBuyerItemIfNotAlreadyPresent(BuyerItem buyerItem) {
    logger.debug("Attempting to insert buyerItem={}", buyerItem);

    // If there is already a buyer item with that item id and this user id then don't insert it again.
    Optional<BuyerItem> existingBuyerItem =
      buyerItemRepository.findByItemIdEqualsAndUserIdEquals(buyerItem.getItemId(), buyerItem.getUserId());

    if (existingBuyerItem.isPresent()) {
      logger.debug("BuyerItem with itemId={} and userId={} already exists. Not attempting to insert again.");
      return existingBuyerItem.get();
    }
    else {
      BuyerItem newBuyerItem = buyerItemRepository.save(buyerItem);
      logger.debug("New buyer item inserted. buyerItem={}", newBuyerItem);
      return newBuyerItem;
    }
  }

  public List<BuyerItem> getAlertsByUserId(int userId) {
    logger.debug("Fetching all alerts for userId={}", userId);
    return buyerItemRepository.findBuyerItemsByUserIdEqualsAndNotifyUserIsTrue(userId);
  }

  public void buyerNotification(SellerItem sellerItem){

    //Sorts through all items that match the sellerItem's Item Id.
    for (BuyerItem buyerItem : buyerItemRepository.findAllByItemId(sellerItem.getItemId())) {
      logger.debug("Found item on: " + buyerItem.toString());

      if (buyerItem.getPrice().compareTo(sellerItem.getPrice()) >= 0 ){

        logger.debug("Notify user set buyerItem notify to true");
        if(buyerItem.getNotifyUser() == false) {
          buyerItem.setNotifyUser(true);
        }
      }
      else
      {
        if(buyerItem.getNotifyUser() == true) {
          buyerItem.setNotifyUser(false);
        }
      }
    }

  }

  public void resetNotification(BuyerItem buyerItem){

    logger.debug("Resetting notifyUser");

    Optional<BuyerItem> itemToReset = buyerItemRepository.findById(buyerItem.getId());
    if(itemToReset.isPresent()){
      BuyerItem buyerItemToUpdate = itemToReset.get();
      if(buyerItemToUpdate.getNotifyUser() == true){
        buyerItemToUpdate.setNotifyUser(false);
        buyerItemRepository.save(buyerItemToUpdate);
      }
    }
  }

  public void updateSellerItemPrice(SellerItem itemToUpdate) {

    Optional<SellerItem> itemCheck = sellerItemRepository.findByItemIdEqualsAndUserIdEquals(itemToUpdate.getItemId(),itemToUpdate.getUserId());
    BigDecimal newPrice = itemToUpdate.getPrice();
    BigDecimal newCost = itemToUpdate.getSellerCost();

    //Check that seller item exists
    if(itemCheck.isPresent())
    {
      SellerItem sellerItem = itemCheck.get();

      if(!newPrice.equals(sellerItem.getPrice())) {

        logger.debug("Updating item sellerItem{} to itemToUpdate{}",sellerItem,itemToUpdate);

        //Set new item price
        sellerItem.setPrice(newPrice);
        buyerNotification(sellerItem);
        //Update sellerItem in DB
        sellerItemRepository.save(sellerItem);

      }

      if(!newCost.equals(sellerItem.getSellerCost())){

        logger.debug("Updating item sellerItem{} to itemToUpdate{}",sellerItem,itemToUpdate);

        sellerItem.setSellerCost(newCost);

        //Update sellerItem in DB
        sellerItemRepository.save(sellerItem);
      }

    }
    else {
      logger.debug("Item did not exist, check item id.");
    }

  }

  public void deleteSellerItem(int sellerItemId) {
    logger.debug("Deleting seller item sellerItemId={}", sellerItemId);
    sellerItemRepository.deleteById(sellerItemId);
  }

  public void deleteBuyerItem(int buyerItemId) {
    logger.debug("Deleting seller item buyerItemId={}", buyerItemId);
    buyerItemRepository.deleteById(buyerItemId);
  }

  public void insertDefaultBuyerItemsForNewUser(int userId) {
    logger.debug("Inserting default BuyerItems for userId={}", userId);
    BuyerItem buyerItem01 = new BuyerItem();
    buyerItem01.setUserId(userId);
    buyerItem01.setPrice(new BigDecimal(5.62));
    buyerItem01.setItemId(91);

    BuyerItem buyerItem02 = new BuyerItem();
    buyerItem02.setUserId(userId);
    buyerItem02.setPrice(new BigDecimal(180.37));
    buyerItem02.setItemId(92);

    insertNewBuyerItemIfNotAlreadyPresent(buyerItem01);
    insertNewBuyerItemIfNotAlreadyPresent(buyerItem02);
  }

}
