package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import java.util.Set;

/**
 * Created by Оля on 01.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData()
              .withName("Name").withLastName("Surname").withAddress("MyAddress").withHome("+7111111111").withEmail("myemail@mail.com").withGroup("test3"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
             .withId(modifiedContact.getId()).withName("Name").withLastName("Surname").withAddress("MyAddress1").withHome("+7111111111").withEmail("myemail@mail.com");
    app.contact().modify(contact, app.goTo());
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
