package com.shmoozed.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.shmoozed.model.BuyerItem;
import com.shmoozed.model.Item;
import com.shmoozed.model.ItemPriceHistory;
import com.shmoozed.model.WalmartItem;
import com.shmoozed.remote.WalmartClient;
import com.shmoozed.repository.WalmartRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class WalmartServiceTest {

  @InjectMocks
  private WalmartService fixture;

  @Mock
  private WalmartClient mockWalmartClient;

  @Mock
  private WalmartRepository mockWalmartRepository;

  @Mock
  private ItemService mockItemService;

  @Mock
  private ItemPriceHistoryService mockItemPriceHistoryService;

  @Mock
  private BuyerSellerItemsService mockBuyerSellerItemsService;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void getItemById() {
    WalmartItem walmartItem = new WalmartItem(1, 1, "item1", "some-path", "11111", 1.00, 0.50, "http://image", "http://image", "11111", "http://ref", null);

    when(mockWalmartClient.getItemById(1)).thenReturn(walmartItem);

    WalmartItem result = fixture.getItemById(1);

    assertThat(result, samePropertyValuesAs(walmartItem));
  }

  @Test
  public void insertNewWalmartItem() {
    WalmartItem walmartItem = new WalmartItem(1, 1, "item1", "some-path", "11111", 1.00, 0.50, "http://image", "http://image", "11111", "http://ref", null);
    when(mockWalmartRepository.save(walmartItem)).thenReturn(walmartItem);

    Item item = new Item(1, "1", 1);
    when(mockItemService.insertNewItem(any(Item.class))).thenReturn(item);

    WalmartItem result = fixture.insertNewWalmartItem(walmartItem);

    assertThat(result, samePropertyValuesAs(walmartItem));

    // Verify the Item was inserted properly
    ArgumentCaptor<Item> itemArg = ArgumentCaptor.forClass(Item.class);
    verify(mockItemService).insertNewItem(itemArg.capture());
    assertThat(itemArg.getValue().getName(), is("item1"));
    assertThat(itemArg.getValue().getQuantity(), is(1));
  }

  @Test
  public void insertNewWalmartItemWithBuyerInfo() {
    WalmartItem walmartItem = new WalmartItem(1, 1, "item1", "some-path", "11111", 1.00, 0.50, "http://image", "http://image", "11111", "http://ref", null);
    when(mockWalmartRepository.save(walmartItem)).thenReturn(walmartItem);

    Item item = new Item(1, "1", 1);
    when(mockItemService.insertNewItem(any(Item.class))).thenReturn(item);

    WalmartItem result = fixture.insertNewWalmartItemWithBuyerInfo(walmartItem, 10, new BigDecimal(15.00), 1);

    assertThat(result, samePropertyValuesAs(walmartItem));

    // Verify the Item was inserted properly
    ArgumentCaptor<Item> itemArg = ArgumentCaptor.forClass(Item.class);
    verify(mockItemService).insertNewItem(itemArg.capture());
    assertThat(itemArg.getValue().getName(), is("item1"));
    assertThat(itemArg.getValue().getQuantity(), is(10));

    // Verify the ItemPriceHistory was inserted properly
    ArgumentCaptor<ItemPriceHistory> itemPriceHistoryArg = ArgumentCaptor.forClass(ItemPriceHistory.class);
    verify(mockItemPriceHistoryService).insertNewItemHistory(itemPriceHistoryArg.capture());
    assertThat(itemPriceHistoryArg.getValue().getItemId(), is(1));
    assertThat(itemPriceHistoryArg.getValue().getPrice().doubleValue(), is(0.5));

    // Verify the BuyerItem was inserted properly
    ArgumentCaptor<BuyerItem> buyerItemArg = ArgumentCaptor.forClass(BuyerItem.class);
    verify(mockBuyerSellerItemsService).insertNewBuyerItemIfNotAlreadyPresent(buyerItemArg.capture());
    assertThat(buyerItemArg.getValue().getItemId(), is(1));
    assertThat(buyerItemArg.getValue().getPrice().doubleValue(), is(15.0));
    assertThat(buyerItemArg.getValue().getUserId(), is(1));
  }

  @Test
  public void findWalmartItemByUrl() {
    fixture.findWalmartItemByUrl("https://www.walmart.com/ip/PAW-Patrol-Paw-Patrol-Ultimate-Rescue-Fire-Truck-with-Extendable-2-ft-Tall-Ladder/814913483");

    verify(mockWalmartClient).getItemById(814913483);
  }

  @Test
  public void refreshAllItems() {
    WalmartItem walmartItem1 = new WalmartItem(1, 1, "item1", "some-path", "11111", 1.00, 0.50, "http://image1", "http://image1", "11111", "http://ref1", null);
    WalmartItem walmartItem2 = new WalmartItem(2, 2, "item2", "some-path", "22222", 2.00, 0.75, "http://image2", "http://image2", "22222", "http://ref2", null);

    when(mockWalmartRepository.findAllByOrderByItemId()).thenReturn(asList(walmartItem1, walmartItem2));

    when(mockWalmartClient.getItemById(1)).thenReturn(walmartItem1);
    when(mockWalmartClient.getItemById(2)).thenReturn(walmartItem2);

    when(mockWalmartRepository.findById(1)).thenReturn(Optional.of(walmartItem1));
    when(mockWalmartRepository.findById(2)).thenReturn(Optional.of(walmartItem2));

    fixture.refreshAllItems();

    verify(mockWalmartRepository).save(walmartItem1);
    verify(mockWalmartRepository).save(walmartItem2);

    // Verify the ItemPriceHistory were inserted properly
    ArgumentCaptor<ItemPriceHistory> itemPriceHistoryArg = ArgumentCaptor.forClass(ItemPriceHistory.class);
    verify(mockItemPriceHistoryService, atLeastOnce()).insertNewItemHistory(itemPriceHistoryArg.capture());

    List<ItemPriceHistory> allArgValues = itemPriceHistoryArg.getAllValues();

    assertThat(allArgValues.get(0).getItemId(), is(1));
    assertThat(allArgValues.get(0).getPrice().doubleValue(), is(0.5));

    assertThat(allArgValues.get(1).getItemId(), is(2));
    assertThat(allArgValues.get(1).getPrice().doubleValue(), is(0.75));
  }

  @Test
  public void refreshAllItems_FiltersOutNotFound_0Ids() {
    // Simulate an item which was "Not Found (400)" in Walmart's API
    WalmartItem notFoundItem = new WalmartItem(0, 0, null, null, null, 0, 0, null, null, null, null, null);

    when(mockWalmartRepository.findAllByOrderByItemId()).thenReturn(singletonList(notFoundItem));
    when(mockWalmartClient.getItemById(0)).thenReturn(notFoundItem);
    when(mockWalmartRepository.findById(0)).thenReturn(Optional.of(notFoundItem));

    fixture.refreshAllItems();

    // Verify it was filtered out and wasn't updated
    verify(mockWalmartRepository, times(0)).save(notFoundItem);
    verifyZeroInteractions(mockItemPriceHistoryService);
  }
}