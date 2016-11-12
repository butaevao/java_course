package ru.stqa.course.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.course.mantis.appmanager.HttpSession;
import ru.stqa.course.mantis.model.MailMessage;
import ru.stqa.course.mantis.model.UserData;
import ru.stqa.course.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


/**
 * Created by Оля on 11.11.2016.
 */
public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    String newPassword = "password123";
    app.goTo().login("administrator", "root");
    app.goTo().manageUsersPage();
    Users usersList = app.db().users();
    UserData selectedUser = usersList.iterator().next();
    String username = selectedUser.getUsername();
    app.user().selectUserByName(username);
    app.user().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.registration().findConfirmationLink(mailMessages, selectedUser.getEmail());
    app.user().changePassword(confirmationLink, newPassword);
    assertTrue(app.newSession().login(username, newPassword));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
