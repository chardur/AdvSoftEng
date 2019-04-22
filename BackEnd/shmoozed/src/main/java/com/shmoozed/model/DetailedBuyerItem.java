package com.shmoozed.model;

public class DetailedBuyerItem extends BuyerItem {

  private DetailedItem detailedItem;

  public DetailedBuyerItem() {
  }

  public DetailedBuyerItem(BuyerItem buyerItem, DetailedItem detailedItem) {
    super(buyerItem.getId(), buyerItem.getItemId(), buyerItem.getPrice(), buyerItem.getUserId(), buyerItem.getNotifyUser());
    this.detailedItem = detailedItem;
  }

  public DetailedItem getDetailedItem() {
    return detailedItem;
  }

  public void setDetailedItem(DetailedItem detailedItem) {
    this.detailedItem = detailedItem;
  }

  @Override
  public String toString() {
    return "DetailedBuyerItem{" +
      "buyerItem=" + super.toString() +
      ", detailedItem=" + detailedItem +
      '}';
  }

}
