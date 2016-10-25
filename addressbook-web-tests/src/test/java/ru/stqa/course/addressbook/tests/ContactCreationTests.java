package ru.stqa.course.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    File photo = new File("src/test/resources/icon1.jpg");
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withName("name1").withLastName("surname1").withAddress("my address1")
            .withHome("+111").withMobile("11-11").withWork("11 11")
            .withEmail("my_email1@rambler.ru").withEmail2("my_email1@mail.ru").withEmail3("my_email1@gmail.com")
            .withPhoto(photo).withGroup("test1")});
    list.add(new Object[] {new ContactData().withName("name2").withLastName("surname2").withAddress("my address2")
            .withHome("+222").withMobile("22-22").withWork("22 22")
            .withEmail("my_email2@rambler.ru").withEmail2("my_email2@mail.ru").withEmail3("my_email2@gmail.com")
            .withPhoto(photo).withGroup("test1")});
    list.add(new Object[] {new ContactData().withName("name3").withLastName("surname3").withAddress("my address3")
            .withHome("+333").withMobile("33-33").withWork("33 33")
            .withEmail("my_email3@rambler.ru").withEmail2("my_email3@mail.ru").withEmail3("my_email3@gmail.com")
            .withPhoto(photo).withGroup("test1")});

    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.goTo().addNewPage();
    app.contact().create(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
