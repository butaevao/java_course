package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Оля on 01.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().createContact(new ContactData("Name", null, "Surname", null, null, null, "MyAddress", "+7111111111", null, null,"myemail@mail.com", null, null, null, "test3"));
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Name", null, "Surname", null, null, null, "MyAddress1", "+7111111111", null, null,"myemail@mail.com", null, null, null, null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitModification();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
