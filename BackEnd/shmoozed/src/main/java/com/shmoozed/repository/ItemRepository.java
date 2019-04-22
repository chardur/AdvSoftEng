package com.shmoozed.repository;

import java.util.List;

import com.shmoozed.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends CrudRepository<Item, Integer> {

  /**
   * Answers with all items which match the partial name provided.
   *
   * Note that there is no implementation for this method. By extending from {@link CrudRepository} we enter
   * the "JPA" and "Spring Data" realm where it is implementing it behind the scenes for us automatically.
   *
   * @param partialName The part of the name to use when identifying matching items
   * @return An Iterable of {@link Item} which matched the provided partial name
   */
  Iterable<Item> findItemsByNameContaining(String partialName);

  @Query(value = "SELECT * from Item WHERE Item_Id in :ids order by Item_Quantity desc", nativeQuery = true)
  List<Item> getTopItemsByBuyerItemCount2(@Param("ids") List<String> ids);
}
