package ru.stqa.course.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.course.mantis.model.UserData;
import ru.stqa.course.mantis.model.Users;

import java.io.IOException;


/**
 * Created by Оля on 11.11.2016.
 */
public class ChangePasswordTests extends TestBase {

  @Test
  public void testChangePassword() throws IOException {
    app.goTo().login("administrator", "root");
    app.goTo().manageUsersPage();
    Users usersList = app.db().users();
    UserData selectedUser = usersList.iterator().next();
    String username = selectedUser.getUsername();
    app.user().selectUserByName(username);
  }
}
