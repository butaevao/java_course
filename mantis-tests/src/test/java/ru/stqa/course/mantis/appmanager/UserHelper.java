package ru.stqa.course.mantis.appmanager;

import org.openqa.selenium.By;


/**
 * Created by Оля on 11.11.2016.
 */
public class UserHelper extends HelperBase{

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void selectUserByName(String name) {
    wd.findElement(By.linkText(name)).click();
  }

}
