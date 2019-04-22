package com.shmoozed.repository;

import java.util.Optional;

import com.shmoozed.model.SellerItem;
import org.springframework.data.repository.CrudRepository;

public interface SellerItemRepository extends CrudRepository<SellerItem, Integer> {

  Iterable<SellerItem> findSellerItemsByUserId(int userId);

  Optional<SellerItem> findByItemIdEqualsAndUserIdEquals(int itemId, int userId);

}
