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

  public void resetPassword() {
    wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
  }

  public void changePassword(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

}
