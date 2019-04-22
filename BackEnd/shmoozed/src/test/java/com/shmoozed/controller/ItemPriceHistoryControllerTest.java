package com.shmoozed.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import com.shmoozed.model.ItemPriceHistory;
import com.shmoozed.service.ItemPriceHistoryService;
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

public class ItemPriceHistoryControllerTest {

  private static final String AUTH_TOKEN = "1234abc_SomeMagicToken";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ItemPriceHistoryService mockItemPriceHistoryService;

  @Test
  public void getItemHistory_ItemFound() throws Exception {
    Instant now = Instant.now();
    Timestamp rightNow = Timestamp.from(now);
    String formattedNowString = now.toString().replace("Z", "+0000");

    ItemPriceHistory iph1 = new ItemPriceHistory(1, 1, BigDecimal.valueOf(1.0), rightNow, rightNow);
    ItemPriceHistory iph2 = new ItemPriceHistory(2, 1, BigDecimal.valueOf(2.0), rightNow, rightNow);

    when(mockItemPriceHistoryService.getItemPriceHistories(1)).thenReturn(Optional.of(asList(iph1, iph2)));

    mockMvc.perform(get("/itemhistory/1"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json("[{\"id\":1,\"itemId\":1,\"price\":1.0,\"date\":\"" + formattedNowString + "\",\"lastUpdateDate\":\"" + formattedNowString + "\"}," +
                                  "{\"id\":2,\"itemId\":1,\"price\":2.0,\"date\":\"" + formattedNowString + "\",\"lastUpdateDate\":\"" + formattedNowString + "\"}]"));
  }

  @Test
  public void getItemHistory_ItemNotFound() throws Exception {
    when(mockItemPriceHistoryService.getItemPriceHistories(1)).thenReturn(Optional.empty());

    mockMvc.perform(get("/itemhistory/1"))
      .andDo(print())
      .andExpect(status().isNotFound())
      .andExpect(content().string(""));
  }

  @Test
  public void insertNewItemHistory() throws Exception {
    Instant now = Instant.now();
    Timestamp rightNow = Timestamp.from(now);
    String formattedNowString = now.toString().replace("Z", "+0000");

    ItemPriceHistory iph1 = new ItemPriceHistory(1, 1, BigDecimal.valueOf(1.0), rightNow, rightNow);

    when(mockItemPriceHistoryService.insertNewItemHistory(any(ItemPriceHistory.class))).thenReturn(iph1);

    mockMvc.perform(post("/itemhistory")
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("Authorization", AUTH_TOKEN)
                      .content("{\"itemId\":1,\"price\":1.0,\"date\":\"" + formattedNowString + "\",\"lastUpdateDate\":\"" + formattedNowString + "\"}"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json("{\"id\":1,\"itemId\":1,\"price\":1.0,\"date\":\"" + formattedNowString + "\",\"lastUpdateDate\":\"" + formattedNowString + "\"}"));
  }

  @Test
  public void insertNewItemHistory_BadAuth() throws Exception {
    Instant now = Instant.now();
    String formattedNowString = now.toString().replace("Z", "+0000");

    mockMvc.perform(post("/itemhistory")
                      .contentType(MediaType.APPLICATION_JSON)
                      // Intentionally don't include an Auth token
                      .content("{\"itemId\":1,\"price\":1.0,\"date\":\"" + formattedNowString + "\",\"lastUpdateDate\":\"" + formattedNowString + "\"}"))
      .andDo(print())
      .andExpect(status().isBadRequest())
      .andExpect(content().string(""));
  }

  @Test
  public void insertNewItemHistory_InvalidJson() throws Exception {
    mockMvc.perform(post("/itemhistory")
                      .contentType(MediaType.APPLICATION_JSON)
                      .header("Authorization", AUTH_TOKEN)
                      .content("Not Valid JSON"))
      .andDo(print())
      .andExpect(status().isBadRequest())
      .andExpect(content().string(""));
  }

  @Test
  public void getAll() throws Exception {
    Instant now = Instant.now();
    Timestamp rightNow = Timestamp.from(now);
    String formattedNowString = now.toString().replace("Z", "+0000");

    ItemPriceHistory iph1 = new ItemPriceHistory(1, 1, BigDecimal.valueOf(1.0), rightNow, rightNow);
    ItemPriceHistory iph2 = new ItemPriceHistory(2, 2, BigDecimal.valueOf(2.0), rightNow, rightNow);

    when(mockItemPriceHistoryService.getAll()).thenReturn(asList(iph1, iph2));

    mockMvc.perform(get("/itemhistory")
                      .header("Authorization", AUTH_TOKEN))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json("[{\"id\":1,\"itemId\":1,\"price\":1.0,\"date\":\"" + formattedNowString + "\",\"lastUpdateDate\":\"" + formattedNowString + "\"}," +
                                  "{\"id\":2,\"itemId\":2,\"price\":2.0,\"date\":\"" + formattedNowString + "\",\"lastUpdateDate\":\"" + formattedNowString + "\"}]"));
  }

  @Test
  public void getAll_BadAuth() throws Exception {
    mockMvc.perform(get("/itemhistory"))
      // Intentionally don't set an "Authorization" header
      .andDo(print())
      .andExpect(status().isBadRequest())
      .andExpect(content().string(""));
  }

}