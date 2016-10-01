package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.goToHomePage();
    app.selectContact();
    app.deleteSelectedContact();
    app.alertOk();
    app.goToHomePage();
  }

}
