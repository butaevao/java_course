package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Оля on 18.10.2016.
 */
public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData()
              .withName("Name").withLastName("Surname").withAddress("MyAddress1-2(3) 45").withHome("+7 111 11-11-11").withMobile("33-44-55").withWork("(789)433").withEmail("myemail@mail.com").withGroup("test3"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactsAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
  }

  public static String cleaned(String address) {
    return address.replaceAll("^\\s+", "").replaceAll("\\s+$", "").replaceAll("\\s+", " ");
  }
}
