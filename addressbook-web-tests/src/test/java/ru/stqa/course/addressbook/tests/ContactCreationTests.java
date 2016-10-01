package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.gotoAddNewPage();
    app.fillContactForm(new ContactData("Name", "Surname", "MyAddress", "+7 111 11-11-11", "myemail@mail.com"));
    app.submitContactCreation();
    app.returnToAddNewPage();
  }

}
