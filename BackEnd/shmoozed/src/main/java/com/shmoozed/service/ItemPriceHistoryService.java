package com.shmoozed.service;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.ItemPriceHistory;
import com.shmoozed.repository.ItemPriceHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A Service to interact with {@link ItemPriceHistory}
 */
@Service
public class ItemPriceHistoryService {

  private Logger logger = LoggerFactory.getLogger(ItemService.class);

  private ItemPriceHistoryRepository itemPriceHistoryRepository;

  @Autowired
  public ItemPriceHistoryService(ItemPriceHistoryRepository itemPriceHistoryRepository) {this.itemPriceHistoryRepository = itemPriceHistoryRepository;
  }

  /**
   * Answers with a list of all {@link ItemPriceHistory}.
   *
   * @return The list of all {@link ItemPriceHistory}
   */
  public Optional<List<ItemPriceHistory>> getItemPriceHistories(int itemId) {
    logger.debug("Fetching itemPriceHistory for itemId={}", itemId);
    return itemPriceHistoryRepository.findItemPriceHistoriesByItemId(itemId);
  }

  public ItemPriceHistory insertNewItemHistory(ItemPriceHistory itemPriceHistory) {
    logger.debug("Attempting to insert itemPriceHistory={}", itemPriceHistory);
    ItemPriceHistory newItemPriceHistory = itemPriceHistoryRepository.save(itemPriceHistory);
    logger.debug("New itemPriceHistory inserted into database. newItemPriceHistory={}", newItemPriceHistory);
    return newItemPriceHistory;
  }

  /**
 * Answers with a list of all {@link ItemPriceHistory}.
 *
 * @return The list of all {@link ItemPriceHistory}
 */
  public List<ItemPriceHistory> getAll() {
    logger.debug("Fetching all itemPriceHistory");
    return ((List<ItemPriceHistory>) itemPriceHistoryRepository.findAll());
  }
}
