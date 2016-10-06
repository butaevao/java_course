package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

/**
 * Created by Оля on 01.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Name", null, "Surname", null, null, null, "MyAddress1", "+7 111 11-11-11", null, null,"myemail@mail.com", null, null, null));
    app.getContactHelper().submitModification();
    app.getNavigationHelper().goToHomePage();
  }
}
