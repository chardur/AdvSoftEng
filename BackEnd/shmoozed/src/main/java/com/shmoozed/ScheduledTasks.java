package com.shmoozed;

import com.shmoozed.service.WalmartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

  private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

  private WalmartService walmartService;

  @Autowired
  public ScheduledTasks(WalmartService walmartService) {
    this.walmartService = walmartService;
  }

  @Scheduled(cron = "${refresh.walmartItems}")
  public void refreshWalmartItems() {
    logger.debug("Launching Walmart Items Refresh Task. task=walmartItemRefresh taskState=Start");

    walmartService.refreshAllItems();
    
    logger.debug("Walmart Items Refresh Task Complete. task=walmartItemRefresh taskState=Complete");
  }
}
