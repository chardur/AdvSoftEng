package com.shmoozed.model;

import java.util.List;

public class WalmartItemResponse {
  private List<WalmartItem> items;


  public List<WalmartItem> getItems() {
    return items;
  }

  public void setItems(List<WalmartItem> items) {
    this.items = items;
  }
}
