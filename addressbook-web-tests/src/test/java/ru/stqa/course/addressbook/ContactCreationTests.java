package ru.stqa.course.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    gotoAddNewPage();
    fillContactForm(new ContactData("Name", "Surname", "MyAddress", "+7 111 11-11-11", "myemail@mail.com"));
    submitContactCreation();
    returnToAddNewPage();
  }

}
