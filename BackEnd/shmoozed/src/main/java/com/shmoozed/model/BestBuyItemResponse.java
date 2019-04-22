package com.shmoozed.model;

import java.util.List;

public class BestBuyItemResponse {
  private List<BestBuyItem> products;

  public List<BestBuyItem> getProducts() {
    return products;
  }
  public void setProducts(List<BestBuyItem> items) {
    this.products = items;
  }
}
