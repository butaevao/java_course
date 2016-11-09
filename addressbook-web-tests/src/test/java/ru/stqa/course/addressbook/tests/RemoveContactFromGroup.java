package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;


/**
 * Created by Оля on 07.11.2016.
 */
public class RemoveContactFromGroup extends TestBase  {

 @BeforeMethod
  public void ensurePreconditionsContacts() {

   if (app.db().groups().size() == 0) {
       app.goTo().GroupPage();
       app.group().create(new GroupData().withName("test3"));
     }

   Groups groups = app.db().groups();
   app.goTo().homePage();
   if (app.db().contacts().size() == 0) {
     app.goTo().addNewPage();
     app.contact().create(new ContactData().withName("name1").withLastName("surname1").withAddress("my address1")
              .withHome("+111").withMobile("11-11").withWork("11 11")
              .withEmail("my_email1@rambler.ru").withEmail2("my_email1@mail.ru").withEmail3("my_email1@gmail.com")
              .inGroup(groups.iterator().next()));
   }

   for (ContactData contact : app.db().contacts()) {
     if (contact.getGroups().size() != 0) {
       return;
     }
   }
   ContactData contactWithoutGroup = app.db().contacts().iterator().next();
   app.goTo().homePage();
   app.contact().addGroup(contactWithoutGroup);
 }

 @Test
 public void testRemoveContactFromGroup() {
   app.goTo().homePage();
   int contactId = 0;
   Groups groupsList = app.db().groups();
   GroupData selectedGroup = groupsList.iterator().next();
   app.group().selectGroupFromList(selectedGroup);

   for (ContactData contact : app.db().contacts()) {
     if (contact.getGroups().contains(selectedGroup) ) {
       app.contact().removeContactFromGroup(contact);
       contactId = contact.getId();
       break;
     }
   }

   for (ContactData contact : app.db().contacts()) {
     if (contact.getId() == contactId && !(contact.getGroups().contains(selectedGroup))) {
       System.out.println("Контакт " + contact.getId() + " удален из группы " + selectedGroup.getName());
       break;
     }
   }
 }
}
