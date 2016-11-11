package ru.stqa.course.mantis.appmanager;

import org.openqa.selenium.By;


/**
 * Created by Оля on 01.10.2016.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUsersPage() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login"));
  }
}
