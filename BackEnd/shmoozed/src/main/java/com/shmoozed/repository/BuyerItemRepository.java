package com.shmoozed.repository;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.BuyerItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BuyerItemRepository extends CrudRepository<BuyerItem, Integer> {

  Iterable<BuyerItem> findBuyerItemsByUserId(int userId);
  Iterable<BuyerItem> findAllByItemId(int itemId);

  List<BuyerItem> findBuyerItemsByUserIdEqualsAndNotifyUserIsTrue(int userId);

  @Query(value = "SELECT Item_Id, COUNT(Item_Id) from Buyer_Items group by Item_Id Order by Count(Item_Id) desc limit 5", nativeQuery = true)
  List<Object[]> getTopItemsByBuyerItemCount();
  Optional<BuyerItem> findByItemIdEqualsAndUserIdEquals(int itemId, int userId);
}
