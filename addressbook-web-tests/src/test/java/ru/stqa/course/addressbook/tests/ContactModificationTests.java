package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Оля on 01.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.group().list().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData("Name", null, "Surname", null, null, null, "MyAddress", "+7111111111", null, null,"myemail@mail.com", null, null, null, "test3"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Name", null, "Surname", null, null, null, "MyAddress1", "+7111111111", null, null,"myemail@mail.com", null, null, null, null);
    app.contact().modify(index, contact, app.goTo());
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
