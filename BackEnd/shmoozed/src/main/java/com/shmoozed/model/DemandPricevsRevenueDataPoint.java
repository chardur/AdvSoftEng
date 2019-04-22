package com.shmoozed.model;

public class DemandPricevsRevenueDataPoint {
  private double demandPrice;
  private double revenue;

  public double getDemandPrice() {
    return demandPrice;
  }

  public void setDemandPrice(double demandPrice) {
    this.demandPrice = demandPrice;
  }

  public double getRevenue() {
    return revenue;
  }

  public void setRevenue(double revenue) {
    this.revenue = revenue;
  }

  public DemandPricevsRevenueDataPoint(double demandPrice, double revenue) {
    this.demandPrice = demandPrice;
    this.revenue = revenue;
  }

  @Override
  public String toString() {
    return "DemandPricevsRevenueDataPoint{" +
      "demandPrice=" + demandPrice +
      ", revenue=" + revenue +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DemandPricevsRevenueDataPoint that = (DemandPricevsRevenueDataPoint) o;
    return Double.compare(that.demandPrice, demandPrice) == 0 &&
      Double.compare(that.revenue, revenue) == 0;
  }
}
