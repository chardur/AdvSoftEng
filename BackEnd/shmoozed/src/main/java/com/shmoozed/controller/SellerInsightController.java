package com.shmoozed.controller;

import java.math.BigDecimal;
import java.util.List;

import com.shmoozed.model.DemandPricevsRevenueDataPoint;
import com.shmoozed.service.SellerInsightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin // Allow All CORS Requests. See https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
@RestController
@RequestMapping(path = "/sellerinsight")
public class SellerInsightController {

  private Logger logger = LoggerFactory.getLogger(SellerInsightController.class);

  private SellerInsightService sellerInsightService;

  @Autowired
  public SellerInsightController(SellerInsightService sellerInsightService) {
    this.sellerInsightService = sellerInsightService;
  }

  @GetMapping(
    path="/{item_id}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<List<DemandPricevsRevenueDataPoint>> getPricevsRevenue(
    @PathVariable("item_id") int itemId) {
    logger.debug("Request for Price vs Revenue for itemId={}", itemId);

    return new ResponseEntity<>(sellerInsightService.getAllRevenueByItemId(itemId), HttpStatus.OK);
  }

  @GetMapping(
    path="/profit/{item_id}/{cost}",
    produces = APPLICATION_JSON_VALUE
  )
  public @ResponseBody ResponseEntity<List<DemandPricevsRevenueDataPoint>> getPricevsProfit(
    @PathVariable("item_id") int itemId, @PathVariable("cost") double cost) {
    logger.debug("Request for Price vs Profit for itemId={} and cost={}", itemId, cost);

    return new ResponseEntity<>(sellerInsightService.getProfitByItemIdAndCost(
      itemId, new BigDecimal(cost)), HttpStatus.OK);
  }

}

