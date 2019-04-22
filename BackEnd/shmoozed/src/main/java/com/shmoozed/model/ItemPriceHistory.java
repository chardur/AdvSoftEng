package com.shmoozed.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Represents an Item's price at a specific point in time.
 */
@Entity
@Table(name = "Item_Price_History")
public class ItemPriceHistory {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "Item_Price_Id")
  private int id;

  @Column(name = "Item_Id")
  private int itemId;

  @Column(name = "Price")
  private BigDecimal price;

  @Column(name = "Date")
  private Timestamp date;

  @Column(name = "Last_update_Date")
  private Timestamp lastUpdateDate;

  public ItemPriceHistory() {
    // Empty default constructor. This is needed in order for JPA to work properly.
  }

  public ItemPriceHistory(int id, int itemId, BigDecimal price, Timestamp date, Timestamp lastUpdateDate) {
    this.id = id;
    this.itemId = itemId;
    this.price = price;
    this.date = date;
    this.lastUpdateDate = lastUpdateDate;
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

  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }

  public Timestamp getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(Timestamp lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  @Override
  public String toString() {
    return "ItemPriceHistory{" +
      "id=" + id +
      ", itemId=" + itemId +
      ", price=" + price +
      ", date=" + date +
      ", lastUpdateDate=" + lastUpdateDate +
      '}';
  }
}
