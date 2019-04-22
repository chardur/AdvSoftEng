package com.shmoozed.repository;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.WalmartItem;
import org.springframework.data.repository.CrudRepository;

public interface WalmartRepository extends CrudRepository<WalmartItem, Integer> {

  Optional<WalmartItem> findAllByLinkedItemId(int linedItemId);

  List<WalmartItem> findAllByOrderByItemId();

}