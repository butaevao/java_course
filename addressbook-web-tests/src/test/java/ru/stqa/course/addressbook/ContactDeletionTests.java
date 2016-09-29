package ru.stqa.course.addressbook;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    goToHomePage();
    selectContact();
    deleteSelectedContact();
    wd.switchTo().alert().accept();
    goToHomePage();
  }

}
