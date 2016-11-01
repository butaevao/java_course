package ru.stqa.course.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Оля on 01.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withName("name1").withLastName("surname1").withAddress("my address1")
              .withHome("+111").withMobile("11-11").withWork("11 11")
              .withEmail("my_email1@rambler.ru").withEmail2("my_email1@mail.ru").withEmail3("my_email1@gmail.com")
              .withGroup("test1"));
    }
  }


  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
             .withId(modifiedContact.getId()).withName("Name").withLastName("Surname111").withAddress("MyAddress1").withHome("+111").withMobile("11-11").withWork("11 11")
             .withEmail("my_email1@rambler.ru").withEmail2("my_email1@mail.ru").withEmail3("my_email1@gmail.com");
    app.contact().modify(contact, app.goTo());
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.without(modifiedContact).withAdded(contact)));
    verifyContactsListInUI();
  }

}
