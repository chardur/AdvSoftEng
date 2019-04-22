package com.shmoozed.repository;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.ItemPriceHistory;
import org.springframework.data.repository.CrudRepository;

public interface ItemPriceHistoryRepository extends CrudRepository<ItemPriceHistory, Integer> {

  Optional<List<ItemPriceHistory>> findItemPriceHistoriesByItemId(int itemId);

}
