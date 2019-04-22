package com.shmoozed.controller;

import com.shmoozed.model.WalmartItem;
import com.shmoozed.service.WalmartService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WalmartControllerTest {

  private static final String AUTH_TOKEN = "1234abc_SomeMagicToken";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WalmartService mockWalmartService;

  @Test
  public void getItemById() throws Exception {
    WalmartItem walmartItem1 = new WalmartItem(1, 1, "Walmart1", "TestIsle1", "0000000000001", 11.99, 1.99, "thumb1", "Image1", "A1", "url", "");

    when(mockWalmartService.getItemById(1)).thenReturn(walmartItem1);

    mockMvc.perform(get("/walmart/itemid/1"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json("{\"itemId\":1,\"linkedItemId\":1,\"name\":\"Walmart1\",\"categoryPath\":\"TestIsle1\",\"upc\":\"TestIsle1\",\"upc\":\"0000000000001\",\"msrp\":11.99,\"salePrice\":1.99," +
                                  "\"thumbnailImage\":\"thumb1\",\"largeImage\":\"Image1\",\"modelNumber\":\"A1\",\"addToCartUrl\":\"url\"}"));
  }

  @Test
  public void insertNewWalmartItemWithBuyerInfo() throws Exception {
    WalmartItem walmartItem1 = new WalmartItem(202194529, 1, "Walmart1", "TestIsle1", "0000000000001", 11.99, 1.99, "thumb1", "Image1", "A1", "url", "");
    when(mockWalmartService.insertNewWalmartItemWithBuyerInfo(walmartItem1, 1, BigDecimal.valueOf(1.99), 2)).thenReturn(walmartItem1);

    mockMvc.perform(post("/walmart/urlbuyerdetails?quantity=2&price=19.99&userId=2")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content("{\"https://www.walmart.com/ip/MSI-GV62-Performance-Gaming-Laptop-15-6-Intel-Core-Gen-i5-8300-NVIDIA-GeForce-GTX-1060-3G-256GB-NVMe-SSD-8GB-RAM-Windows-10-Black-GV62061-Fortnite-Bu/202194529\"}")
                      .header("Authorization", AUTH_TOKEN))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string("")); //this test should actually return data
  }
}
