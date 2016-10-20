package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    File photo = new File("src/test/resources/icon1.jpg");
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withName("Name").withLastName("Surname")
            .withAddress("MyAddress").withAllPhones("+711111").withAllEmails("myemail@mail.com")
            .withPhoto(photo).withGroup("test3");
    app.goTo().addNewPage();
    app.contact().create(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
