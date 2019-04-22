package com.shmoozed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.shmoozed.model.Item;
import com.shmoozed.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemServiceTest {

  @InjectMocks
  private ItemService fixture;

  @Mock
  private ItemRepository mockItemRepository;

  @Mock
  private Item mockItem;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void getAllItems() {
    List<Item> items = new ArrayList<>();
    items.add(mockItem);
    items.add(mockItem);
    when(mockItemRepository.findAll()).thenReturn(items);

    List<Item> results = fixture.getAllItems();

    assertThat(results.size(), is(2));
    assertThat(results.contains(mockItem), is(true));
  }

  @Test
  public void getItem_Found() {
    when(mockItemRepository.findById(anyInt())).thenReturn(java.util.Optional.of(mockItem));

    Optional<Item> results = fixture.getItem(12345);

    assertThat(results.isPresent(), is(true));
    assertThat(results.get(), is(mockItem));
  }

  @Test
  public void getItem_NotFound() {
    when(mockItemRepository.findById(anyInt())).thenReturn(Optional.empty());

    Optional<Item> results = fixture.getItem(12345);

    assertThat(results.isPresent(), is(false));
  }

  @Test
  public void getNonSecretItemNamesWithNameLike() {
    List<Item> items = new ArrayList<>();
    items.add(new Item(1, "First Item", 5));
    items.add(new Item(2, "First Secret Item", 5)); // Should be filtered out because its "secret"
    items.add(new Item(3, "Second Item", 5));
    when(mockItemRepository.findItemsByNameContaining(anyString())).thenReturn(items);

    List<String> results = fixture.getNonSecretItemNamesWithNameLike("Item");

    assertThat(results.size(), is(2));
    assertThat(results.contains("First Item"), is(true));
    assertThat(results.contains("First Secret Item"), is(false));
    assertThat(results.contains("Second Item"), is(true));
  }

  @Test
  public void insertNewItem() {
    Item newItem = new Item(0, "New Item", 10);

    when(mockItemRepository.save(newItem)).thenReturn(new Item(0, "New Item", 10));

    Item results = fixture.insertNewItem(newItem);

    verify(mockItemRepository).save(newItem);
    assertThat(results, samePropertyValuesAs(newItem));
  }

}