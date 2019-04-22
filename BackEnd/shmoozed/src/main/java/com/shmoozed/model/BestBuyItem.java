package com.shmoozed.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "BestBuy_Items")
public class BestBuyItem {

  private int id;
  private String sku;
  private String name;
  private double regularPrice;
  private double salePrice;
  private String thumbnailImage;
  private String largeImage;
  private String modelNumber;
  private String addToCartUrl;
  private String upc;

  public BestBuyItem(){
    // Empty default constructor. This is needed in order for JPA to work properly.
  }

  public int getId() { return id; }

  public void setId(int id) { this.id = id; }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(double salePrice) {
    this.salePrice = salePrice;
  }


  public double getRegularPrice() {
    return regularPrice;
  }

  public void setRegularPrice(double regularPrice) {
    this.regularPrice = regularPrice;
  }

  public String getModelNumber() {
    return modelNumber;
  }

  public void setModelNumber(String modelNumber) {
    this.modelNumber = modelNumber;
  }

  public String getAddToCartUrl() {
    return addToCartUrl;
  }

  public void setAddToCartUrl(String addToCartUrl) {
    this.addToCartUrl = addToCartUrl;
  }

  public String getUpc() {
    return upc;
  }

  public void setUpc(String upc) {
    this.upc = upc;
  }

  public String getThumbnailImage() {
    return thumbnailImage;
  }

  public void setThumbnailImage(String thumbnailImage) {
    this.thumbnailImage = thumbnailImage;
  }

  public String getLargeImage() {
    return largeImage;
  }

  public void setLargeImage(String largeImage) {
    this.largeImage = largeImage;
  }
}
