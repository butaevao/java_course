package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsGroup() {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test3"));
    }
  }

  @BeforeMethod
  public void ensurePreconditionsContacts() {
    Groups groups = app.db().groups();
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withName("name1").withLastName("surname1").withAddress("my address1")
              .withHome("+111").withMobile("11-11").withWork("11 11")
              .withEmail("my_email1@rambler.ru").withEmail2("my_email1@mail.ru").withEmail3("my_email1@gmail.com")
              .inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testContactDeletion() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactsListInUI();
  }
}
