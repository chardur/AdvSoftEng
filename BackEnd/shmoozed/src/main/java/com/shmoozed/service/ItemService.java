package com.shmoozed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.shmoozed.model.DetailedItem;
import com.shmoozed.model.Item;
import com.shmoozed.repository.BuyerItemRepository;
import com.shmoozed.repository.ItemRepository;
import com.shmoozed.repository.WalmartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

/**
 * A Service to interact with {@link Item}
 */
@Service
public class ItemService {

  private Logger logger = LoggerFactory.getLogger(ItemService.class);

  private ItemRepository itemRepository;
  private WalmartRepository walmartRepository;
  private BuyerItemRepository buyerItemRepository;

  @Autowired
  public ItemService(ItemRepository itemRepository, WalmartRepository walmartRepository, BuyerItemRepository buyerItemRepository) {
    this.itemRepository = itemRepository;
    this.walmartRepository = walmartRepository;
    this.buyerItemRepository = buyerItemRepository;
  }

  /**
   * Answers with a list of all {@link Item}.
   *
   * @return The list of all {@link Item}
   */
  public List<Item> getAllItems() {
    return (List<Item>) itemRepository.findAll();
  }

  /**
   * Answers with a single {@link Item} based on the provided item ID.
   *
   * @param itemId The ID of the item to return
   * @return An {@link Optional} of the {@link Item} matching the provided ID.
   */
  public Optional<Item> getItem(int itemId) {
    return itemRepository.findById(itemId);
  }

  public Optional<DetailedItem> getDetailedItem(int itemId) {
    DetailedItem detailedItem = new DetailedItem();

    itemRepository.findById(itemId).ifPresent(detailedItem::setItem);
    walmartRepository.findAllByLinkedItemId(itemId).ifPresent(detailedItem::setWalmartItem);

    // TODO: What do we do about Detailed Items which only have and Item? Wouldn't we still want to return them?
    if (detailedItem.getItem() == null || detailedItem.getWalmartItem() == null) {
      return Optional.empty();
    }

    logger.debug("Found detailed item for itemId={} detailedItem={}", itemId, detailedItem);

    return Optional.of(detailedItem);
  }

  /**
   * Returns a list of item names which aren't "Secret".
   *
   * This is an EXAMPLE of applying business logic after the database retrieves records.
   *
   * Yes, this is a silly example to expect items to be filtered out by simply having the
   * text "secret" in it somewhere.
   *
   * Yes, you could absolutely have done this at the query level and excluded those items
   * there and not brought them up to this layer.
   *
   * Despite the above, this is a good example of querying for results, transforming their
   * form, filtering and applying logic on the results, then returning it to the caller.
   *
   * @param partialName A string representing the partial item to search the database for,
   * ensuring that no "secret" items are returned.
   * @return A list of Non-secret item names.
   */
  public List<String> getNonSecretItemNamesWithNameLike(String partialName) {
    List<Item> items = (List<Item>) itemRepository.findItemsByNameContaining(partialName);

    return items.stream()
      .map(Item::getName)
      .filter(itemName -> !itemName.toLowerCase().contains("secret"))
      .collect(toList());
  }

  /**
   * Inserts a new {@link Item}
   *
   * @param item The item to be inserted
   * @return The newly inserted item
   */
  public Item insertNewItem(Item item) {
    Item newItem = itemRepository.save(item);
    logger.debug("New item inserted: item={}", newItem);
    return newItem;
  }

  public List<Item> getTopRequestedItems() {
    List<String> ids = new ArrayList<>();

    List<Object[]> results = buyerItemRepository.getTopItemsByBuyerItemCount();
    for (Object[] result : results) {
      Integer id = ((Number) result[0]).intValue();
      ids.add(id.toString());
    }

    List<Item> items = itemRepository.getTopItemsByBuyerItemCount2(ids);
    return items;
  }
}
