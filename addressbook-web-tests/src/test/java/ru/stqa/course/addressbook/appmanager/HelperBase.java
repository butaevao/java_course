package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;


/**
 * Created by Оля on 01.10.2016.
 */
public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    WebElement element = wd.findElement(locator);
    if (text != null) {
      String existingText = element.getAttribute("value");
      if (! text.equals(existingText)) {
        element.clear();
        element.sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) {
    WebElement element = wd.findElement(locator);
    if (file != null) {
        element.sendKeys(file.getAbsolutePath());
    }
  }

  public void alertOk() {
    wd.switchTo().alert().accept();
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }

  }
}
