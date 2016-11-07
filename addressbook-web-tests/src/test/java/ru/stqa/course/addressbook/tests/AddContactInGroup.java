package ru.stqa.course.addressbook.tests;

import org.hibernate.Session;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;

import java.util.List;


/**
 * Created by Оля on 06.11.2016.
 */
public class AddContactInGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsContacts() {
    Groups groups = app.db().groups();
    app.goTo().homePage();
    if (groups.size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test3"));
    }

    Session session = app.db().sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();

    for (ContactData contact : result) {
      if (contact.getGroups().size() == 0) {
        return;
      }
    }
    app.goTo().addNewPage();
    app.contact().create(new ContactData().withName("name1").withLastName("surname1").withAddress("my address1")
            .withHome("+111").withMobile("11-11").withWork("11 11")
            .withEmail("my_email1@rambler.ru").withEmail2("my_email1@mail.ru").withEmail3("my_email1@gmail.com"));
  }

  @Test
  public void testAddContactInGroup() {
    app.goTo().homePage();
    int contactId = 0;

    Session session = app.db().sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00' order by id desc").list();
    session.getTransaction().commit();
    for (ContactData contact : result) {
      if (contact.getGroups().size() == 0) {
        app.contact().addGroup(contact, app.goTo());
        contactId = contact.getId();
        break;
      }
    }
    session.close();

    session = app.db().sessionFactory.openSession();
    session.beginTransaction();
    result = session.createQuery("from ContactData where deprecated = '0000-00-00' order by id desc").list();
    session.getTransaction().commit();
    for (ContactData contact : result) {
      if (contact.getId() == contactId && contact.getGroups().size() > 0) {
        System.out.println("Для контакта " + contact.getId() + " группа добавлена");
        break;
      }
    }
    session.close();
    }
  }
