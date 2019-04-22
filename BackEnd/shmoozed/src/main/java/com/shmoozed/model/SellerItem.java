package com.shmoozed.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Represents a set of items which a Seller is looking to sell.
 */
@Entity
@Table(name = "Seller_Items")
public class SellerItem {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "Seller_Item_Id")
  private int id;

  @Column(name = "Item_Id")
  private int itemId;

  @Column(name = "Price")
  private BigDecimal price;

  @Column(name = "User_Id")
  private int userId;

  @Column(name = "Seller_Cost")
  private BigDecimal sellerCost;

  public SellerItem() {
    // Empty default constructor. This is needed in order for JPA to work properly.
  }

  public SellerItem(int id, int itemId, BigDecimal price, int userId, BigDecimal sellerCost) {
    this.id = id;
    this.itemId = itemId;
    this.price = price;
    this.userId = userId;
    this.sellerCost = sellerCost;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public BigDecimal getSellerCost() { return sellerCost; }

  public void setSellerCost(BigDecimal sellerCost) { this.sellerCost = sellerCost; }

  @Override
  public String toString() {
    return "SellerItem{" +
      "id=" + id +
      ", itemId=" + itemId +
      ", price=" + price +
      ", userId=" + userId +
      ", sellerCost=" + sellerCost +
      '}';
  }
}
