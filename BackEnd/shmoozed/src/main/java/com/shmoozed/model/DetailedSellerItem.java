package com.shmoozed.model;

public class DetailedSellerItem extends SellerItem {

  private DetailedItem detailedItem;

  public DetailedSellerItem() {
  }

  public DetailedSellerItem(SellerItem sellerItem, DetailedItem detailedItem) {
    super(sellerItem.getId(), sellerItem.getItemId(), sellerItem.getPrice(), sellerItem.getUserId(),sellerItem.getSellerCost());
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
    return "DetailedSellerItem{" +
      "sellerItem=" + super.toString() +
      ", detailedItem=" + detailedItem +
      '}';
  }

}
