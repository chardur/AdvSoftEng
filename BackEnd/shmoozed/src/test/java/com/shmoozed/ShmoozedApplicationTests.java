package com.shmoozed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShmoozedApplicationTests {

  /**
   * This test is designed to ensure that all Autowiring and Bean Configuration is set up properly. It does not
   * test any functionality, but it will fail a test if an exception launching the application occurs.
   */
  @Test
  public void contextLoads() {
  }

}
