package com.shmoozed.controller;

import java.util.Optional;

import com.shmoozed.model.Item;
import com.shmoozed.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ItemService mockItemService;

  @Test
  public void getAllItems() throws Exception {
    Item item1 = new Item(1, "item1", 1);
    Item item2 = new Item(2, "item2", 2);

    when(mockItemService.getAllItems()).thenReturn(asList(item1, item2));

    mockMvc.perform(get("/item"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json("[{\"id\":1,\"name\":\"item1\",\"quantity\":1},{\"id\":2,\"name\":\"item2\",\"quantity\":2}]"));
  }

  @Test
  public void getItem_ItemFound() throws Exception {
    Item item1 = new Item(1, "item1", 1);

    when(mockItemService.getItem(1)).thenReturn(Optional.of(item1));

    mockMvc.perform(get("/item/1"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json("{\"id\":1,\"name\":\"item1\",\"quantity\":1}"));
  }

  @Test
  public void getItem_ItemNotFound() throws Exception {
    when(mockItemService.getItem(1)).thenReturn(Optional.empty());

    mockMvc.perform(get("/item/1"))
      .andDo(print())
      .andExpect(status().isNotFound())
      .andExpect(content().string(""));
  }

  @Test
  public void insertNewItem() throws Exception {
    Item item1 = new Item(1, "item1", 1);

    when(mockItemService.insertNewItem(any(Item.class))).thenReturn(item1);

    mockMvc.perform(post("/item")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content("{\"name\":\"item1\",\"quantity\":1}"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json("{\"id\":1,\"name\":\"item1\",\"quantity\":1}"));
  }

  @Test
  public void insertNewItem_InvalidJson() throws Exception {
    when(mockItemService.insertNewItem(any(Item.class))).thenReturn(null);

    mockMvc.perform(post("/item")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content("Not Valid Json"))
      .andDo(print())
      .andExpect(status().isBadRequest())
      .andExpect(content().string(""));
  }

  @Test
  public void getItemNamesWithPartialName() throws Exception {
    when(mockItemService.getNonSecretItemNamesWithNameLike("thing"))
      .thenReturn(asList("One Thing", "Two Thing", "Red Thing", "Blue Thing"));

    mockMvc.perform(get("/item/names?partial=thing"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("One")))
      .andExpect(content().string(containsString("Two")))
      .andExpect(content().string(containsString("Red")))
      .andExpect(content().string(containsString("Blue")));
  }
}