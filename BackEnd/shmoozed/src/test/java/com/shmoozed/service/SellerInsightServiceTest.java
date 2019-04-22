package com.shmoozed.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.shmoozed.model.BuyerItem;
import com.shmoozed.model.DemandPricevsRevenueDataPoint;
import com.shmoozed.repository.BuyerItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SellerInsightServiceTest {

  @InjectMocks
  private SellerInsightService fixture;

  @Mock
  private BuyerItemRepository mockBuyerItemRepository;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void getProfitByItemIdAndCost() {
    int ItemId = 1;
    BigDecimal cost = new BigDecimal(3.5);

    List<BuyerItem> buyerItemList = new ArrayList<>();

    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(1), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2.1), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(3), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2.4), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(5), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(6.04), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(3), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(3.12), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(3), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(11.9), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(12.64), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(0.11), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(3.55), 1,false));

    when(mockBuyerItemRepository.findAllByItemId(ItemId)).thenReturn(buyerItemList);

    DemandPricevsRevenueDataPoint dp001 = new DemandPricevsRevenueDataPoint(3.55, 0.25);
    DemandPricevsRevenueDataPoint dp002 = new DemandPricevsRevenueDataPoint(5, 6);
    DemandPricevsRevenueDataPoint dp003 = new DemandPricevsRevenueDataPoint(6.04, 7.62);
    DemandPricevsRevenueDataPoint dp004 = new DemandPricevsRevenueDataPoint(11.9, 16.8);
    DemandPricevsRevenueDataPoint dp005 = new DemandPricevsRevenueDataPoint(12.64, 9.14);

    List<DemandPricevsRevenueDataPoint> listOfAnswers = new ArrayList<>();

    listOfAnswers.add(dp001);
    listOfAnswers.add(dp002);
    listOfAnswers.add(dp003);
    listOfAnswers.add(dp004);
    listOfAnswers.add(dp005);

    List<DemandPricevsRevenueDataPoint> results = fixture.getProfitByItemIdAndCost(ItemId, cost);

    assertEquals(listOfAnswers, results);
  }

  @Test
  public void getAllRevenueByItemId() {
    int ItemId = 1;

    List<BuyerItem> buyerItemList = new ArrayList<>();

    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(1), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(1), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(1), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(1), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(1), 1,false));

    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(2), 1,false));

    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(3), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(3), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(3), 1,false));

    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(4), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(4), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(4), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(4), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(4), 1,false));

    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(5), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(5), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(5), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(5), 1,false));

    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(6), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(6), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(6), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(6), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(6), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(6), 1,false));

    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(7), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(7), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(7), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(7), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(7), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(7), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(7), 1,false));

    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(8), 1,false));

    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(9), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(9), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(9), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(9), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(9), 1,false));
    buyerItemList.add(new BuyerItem(1, ItemId, new BigDecimal(9), 1,false));

    when(mockBuyerItemRepository.findAllByItemId(ItemId)).thenReturn(buyerItemList);

    DemandPricevsRevenueDataPoint dp001 = new DemandPricevsRevenueDataPoint(1, 61);
    DemandPricevsRevenueDataPoint dp002 = new DemandPricevsRevenueDataPoint(2, 112);
    DemandPricevsRevenueDataPoint dp003 = new DemandPricevsRevenueDataPoint(3, 138);
    DemandPricevsRevenueDataPoint dp004 = new DemandPricevsRevenueDataPoint(4, 172);
    DemandPricevsRevenueDataPoint dp005 = new DemandPricevsRevenueDataPoint(5, 190);
    DemandPricevsRevenueDataPoint dp006 = new DemandPricevsRevenueDataPoint(6, 204);
    DemandPricevsRevenueDataPoint dp007 = new DemandPricevsRevenueDataPoint(7, 196);
    DemandPricevsRevenueDataPoint dp008 = new DemandPricevsRevenueDataPoint(8, 168);
    DemandPricevsRevenueDataPoint dp009 = new DemandPricevsRevenueDataPoint(9, 54);

    List<DemandPricevsRevenueDataPoint> listOfAnswers = new ArrayList<>();

    listOfAnswers.add(dp001);
    listOfAnswers.add(dp002);
    listOfAnswers.add(dp003);
    listOfAnswers.add(dp004);
    listOfAnswers.add(dp005);
    listOfAnswers.add(dp006);
    listOfAnswers.add(dp007);
    listOfAnswers.add(dp008);
    listOfAnswers.add(dp009);

    List<DemandPricevsRevenueDataPoint> results = fixture.getAllRevenueByItemId(ItemId);

    assertEquals(listOfAnswers, results);
  }
}