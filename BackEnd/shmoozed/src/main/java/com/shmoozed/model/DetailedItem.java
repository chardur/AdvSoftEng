package com.shmoozed.model;

public class DetailedItem {

  private Item item;
  private WalmartItem walmartItem;

  public DetailedItem() {
  }

  public DetailedItem(Item item, WalmartItem walmartItem) {
    this.item = item;
    this.walmartItem = walmartItem;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public WalmartItem getWalmartItem() {
    return walmartItem;
  }

  public void setWalmartItem(WalmartItem walmartItem) {
    this.walmartItem = walmartItem;
  }

  @Override
  public String toString() {
    return "DetailedItem{" +
      "item=" + item +
      ", walmartItem=" + walmartItem +
      '}';
  }

}
