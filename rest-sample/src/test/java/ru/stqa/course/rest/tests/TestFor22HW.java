package ru.stqa.course.rest.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by ola on 16.11.2016.
 */
public class TestFor22HW extends TestBase{

  @Test
  public void testSum() throws IOException {
    skipIfNotFixed(6);
    Assert.assertEquals(3 + 3, 6);
  }
}
