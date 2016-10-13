package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Name", null, "Surname", null, null, null, "MyAddress", "+7111111111", null, null,"myemail@mail.com", null, null, null, "test3");
    app.goTo().addNewPage();
    app.contact().create(contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
