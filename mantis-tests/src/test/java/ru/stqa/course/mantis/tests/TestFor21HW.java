package ru.stqa.course.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by ola on 16.11.2016.
 */
public class TestFor21HW extends TestBase{

  @Test
  public void testSum() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0000001);
    Assert.assertEquals(3 + 3, 6);
  }
}
