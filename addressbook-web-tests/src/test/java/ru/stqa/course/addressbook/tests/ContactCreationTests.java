package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().fillContactForm(new ContactData("Name", null, "Surname", null, null, null, "MyAddress", "+7 111 11-11-11", null, null,"myemail@mail.com", null, null, null));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToAddNewPage();
  }

}
