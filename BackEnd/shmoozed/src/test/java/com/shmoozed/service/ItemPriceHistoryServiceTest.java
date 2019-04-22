package com.shmoozed.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.shmoozed.model.ItemPriceHistory;
import com.shmoozed.repository.ItemPriceHistoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ItemPriceHistoryServiceTest {

  @InjectMocks
  private ItemPriceHistoryService fixture;

  @Mock
  private ItemPriceHistoryRepository mockItemPriceHistoryRepository;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void getItemPriceHistories() {
    Instant now = Instant.now();
    Timestamp rightNow = Timestamp.from(now);

    ItemPriceHistory iph1 = new ItemPriceHistory(1, 1, BigDecimal.valueOf(1.0), rightNow, rightNow);
    ItemPriceHistory iph2 = new ItemPriceHistory(2, 1, BigDecimal.valueOf(2.0), rightNow, rightNow);

    when(mockItemPriceHistoryRepository.findItemPriceHistoriesByItemId(1)).thenReturn(Optional.of(asList(iph1, iph2)));

    Optional<List<ItemPriceHistory>> results = fixture.getItemPriceHistories(1);

    assertThat(results.isPresent(), is(true));
    assertThat(results.get().get(0), is(iph1));
    assertThat(results.get().get(1), is(iph2));
  }

  @Test
  public void getItemPriceHistories_notFound() {
    when(mockItemPriceHistoryRepository.findItemPriceHistoriesByItemId(1)).thenReturn(Optional.empty());

    Optional<List<ItemPriceHistory>> results = fixture.getItemPriceHistories(1);

    assertThat(results.isPresent(), is(false));
  }

  @Test
  public void insertNewItemHistory() {
    Instant now = Instant.now();
    Timestamp rightNow = Timestamp.from(now);

    ItemPriceHistory iph1 = new ItemPriceHistory(1, 1, BigDecimal.valueOf(1.0), rightNow, rightNow);

    when(mockItemPriceHistoryRepository.save(iph1)).thenReturn(iph1);

    ItemPriceHistory results = fixture.insertNewItemHistory(iph1);

    assertThat(results, samePropertyValuesAs(iph1));
  }

  @Test
  public void getAll() {
    Instant now = Instant.now();
    Timestamp rightNow = Timestamp.from(now);

    ItemPriceHistory iph1 = new ItemPriceHistory(1, 1, BigDecimal.valueOf(1.0), rightNow, rightNow);
    ItemPriceHistory iph2 = new ItemPriceHistory(2, 1, BigDecimal.valueOf(2.0), rightNow, rightNow);

    when(mockItemPriceHistoryRepository.findAll()).thenReturn(asList(iph1, iph2));

    List<ItemPriceHistory> results = fixture.getAll();

    assertThat(results.get(0), is(iph1));
    assertThat(results.get(1), is(iph2));
  }
}