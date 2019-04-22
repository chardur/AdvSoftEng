package com.shmoozed.controller;

import java.math.BigDecimal;

import com.shmoozed.model.DemandPricevsRevenueDataPoint;
import com.shmoozed.service.SellerInsightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SellerInsightControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SellerInsightService mockSellerInsightService;

  @Test
  public void getPricevsRevenue() throws Exception {
    DemandPricevsRevenueDataPoint dp001 = new DemandPricevsRevenueDataPoint(1,61);
    DemandPricevsRevenueDataPoint dp002 = new DemandPricevsRevenueDataPoint(2,112);

    when(mockSellerInsightService.getAllRevenueByItemId(1)).thenReturn(asList(dp001, dp002));

    mockMvc.perform(get("/sellerinsight/1"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json("[{\"demandPrice\":1.0,\"revenue\":61.0}," +
                                  "{\"demandPrice\":2.0,\"revenue\":112.0}]"));
  }

  @Test
  public void getPricevsProfit() throws Exception {
    DemandPricevsRevenueDataPoint dp001 = new DemandPricevsRevenueDataPoint(1,61);
    DemandPricevsRevenueDataPoint dp002 = new DemandPricevsRevenueDataPoint(2,112);

    when(mockSellerInsightService.getProfitByItemIdAndCost(
      1,new BigDecimal(3.50))).thenReturn(asList(dp001, dp002));

    mockMvc.perform(get("/sellerinsight/profit/1/3.50"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json("[{\"demandPrice\":1.0,\"revenue\":61.0}," +
                                  "{\"demandPrice\":2.0,\"revenue\":112.0}]"));
  }
}