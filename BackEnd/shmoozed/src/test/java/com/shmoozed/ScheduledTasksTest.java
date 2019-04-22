package com.shmoozed;

import com.shmoozed.service.WalmartService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ScheduledTasksTest {

  @InjectMocks
  private ScheduledTasks fixture;

  @Mock
  private WalmartService mockWalmartService;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void refreshWalmartItems() {
    fixture.refreshWalmartItems();

    verify(mockWalmartService).refreshAllItems();
  }

  // TODO: Develop a test which makes sure that the scheduled task gets launched on a schedule
}