package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Оля on 01.10.2016.
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void gotoAddNewPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void goToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }
}
