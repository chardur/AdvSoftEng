package com.shmoozed.controller;

import java.util.List;
import java.util.Optional;

import com.shmoozed.model.Item;
import com.shmoozed.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin // Allow All CORS Requests. See https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
@RestController
@RequestMapping(path = "/example")
public class ExampleController {

  private Logger logger = LoggerFactory.getLogger(ExampleController.class);

  private ItemService itemService;

  @Autowired
  public ExampleController(ItemService itemService) {
    this.itemService = itemService;
  }

  /**
   * Gets all {@link Item} stored in the application
   *
   * @return The list of {@link Item}
   */
  @GetMapping(
    path="/item",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<List<Item>> getAllItems() {
    logger.debug("Request to get all items.");
    return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
  }

  /**
   * Gets a single {@link Item} based on the provided ID
   *
   * @param itemId The ID of the item to retrieve
   * @return The Item with the matching ID. Returns a Not Found response if no item with that
   * ID is found.
   */
  @GetMapping(
    path="/item/{item_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<Item> getItems(@PathVariable("item_id") int itemId) {
    logger.debug("Request for item. itemId={}", itemId);

    return itemService.getItem(itemId)
      .map(i -> new ResponseEntity<>(i, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Inserts a new {@link Item}.
   *
   * @param item The {@link Item} to be inserted
   * @return The newly inserted {@link Item}
   */
  @PostMapping(
    path = "/item",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<Item> insertNewItem(@RequestBody Item item) {
    logger.debug("Request to insert new item. item={}", item);

    Item newItem = itemService.insertNewItem(item);
    return new ResponseEntity<>(newItem, HttpStatus.OK);
  }

  /**
   * Returns a list of Item Names which match the partial name provided.
   *
   * @param partial The partial name to match against when identifying Item Names
   * @return The list of Item Names containing the partial name provided
   */
  @GetMapping(
    value = "/item/names",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<List<String>> getItemNamesWithPartialName(
    @RequestParam("partial") String partial) {
    logger.debug("Request to list all item names matching partial name. partialName='{}'", partial);
    return new ResponseEntity<>(itemService.getNonSecretItemNamesWithNameLike(partial), HttpStatus.OK);
  }

}
