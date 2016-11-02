package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Оля on 18.10.2016.
 */
public class ContactFullInfoTests extends TestBase {

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
    if (app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData()
              .withName("Name").withLastName("Surname").withAddress("MyAddress")
              .withHome("+7 111 11-11-11").withMobile("33-44-55").withWork("(789)433")
              .withEmail("myemail@mail.com").withEmail2("my_mail@@yandex.ru").withEmail3("my123mail.rambler.ru")
              .inGroup(groups.iterator().next()));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactEmails() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromFullInfoPage = app.contact().infoFromFullInfoPage(contact);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(cleaned(contactInfoFromFullInfoPage.getAllInfo()), equalTo((mergeFullInfo(contactInfoFromEditForm))));
  }

  private String mergeFullInfo(ContactData contact) {
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    return Arrays.asList(getNameandAddress(contactInfoFromEditForm), getPhones(contactInfoFromEditForm), getEmails(contactInfoFromEditForm))
            .stream().filter((e) -> ! e.equals(""))
            .map(ContactFullInfoTests::cleaned)
            .collect(Collectors.joining(" "));
  }

  private String getNameandAddress(ContactData contact) {
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    return Arrays.asList(contactInfoFromEditForm.getName(), contactInfoFromEditForm.getLastName(), contactInfoFromEditForm.getAddress())
            .stream().filter((n) -> ! n.equals(""))
            .map(ContactFullInfoTests::cleaned)
            .collect(Collectors.joining(" "));
  }

  private String getPhones(ContactData contact) {
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    return Arrays.asList("H: " + contactInfoFromEditForm.getHome(), "M: " + contactInfoFromEditForm.getMobile(), "W: " + contactInfoFromEditForm.getWork())
            .stream().filter((m) -> ! m.equals(""))
            .map(ContactFullInfoTests::cleaned)
            .collect(Collectors.joining(" "));
  }

  private String getEmails(ContactData contact) {
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    return Arrays.asList(contactInfoFromEditForm.getEmail() + " (www." + contactInfoFromEditForm.getEmail().substring(contactInfoFromEditForm.getEmail().indexOf("@")+1) + ")",
            contactInfoFromEditForm.getEmail2() + " (www." + contactInfoFromEditForm.getEmail2().substring(contactInfoFromEditForm.getEmail2().indexOf("@")+1) + ")",
            contactInfoFromEditForm.getEmail3() + " (www." + contactInfoFromEditForm.getEmail3().substring(contactInfoFromEditForm.getEmail3().indexOf("@")+1) + ")")
            .stream().filter((e) -> ! e.equals(""))
            .map(ContactFullInfoTests::cleaned)
            .collect(Collectors.joining(" "));
  }

  public static String cleaned(String allInfo) {
    return allInfo.replaceAll("^\\s+", "").replaceAll("\\s+$", "").replaceAll("\\s+", " ").replaceAll("(\\n)+", " ");
  }
}
