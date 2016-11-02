package ru.stqa.course.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String name;

  @Transient
  private String middlename;

  @Expose
  @Column(name = "lastname")
  private String lastName;

  @Transient
  private String nickname;

  @Transient
  private String title;

  @Transient
  private String company;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String home;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String work;

  @Transient
  private String fax;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @XStreamOmitField
  @Expose
  @Transient
  private String allEmails;

  @Transient
  private String homepage;

  @XStreamOmitField
  @Expose
  @Transient
  private String allPhones;

  @Transient
  private String allInfo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @XStreamOmitField
  //@Column(name = "photo")
  //@Type(type = "text")
  @Transient
  private File photo;

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public String getAllInfo() {
    return allInfo;
  }

  public ContactData withAllInfo(String allInfo) {
    this.allInfo = allInfo;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    if (home != null) {
      return home;
    }
    else {
      return "";
    }
  }

  public String getEmail() {
    if (email != null) {
      return email;
    }
    else {
      return "";
    }
  }

  public String getMidName() {
    return middlename;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getMobile() {
    if (mobile != null) {
      return mobile;
    }
    else {
      return "";
    }
  }

  public String getWork() {
    if (work != null) {
      return work;
    }
    else {
      return "";
    }
  }

  public String getFax() {
    return fax;
  }

  public String getEmail2() {
    if (email2 != null) {
      return email2;
    }
    else {
      return "";
    }
  }

  public String getEmail3() {
    if (email3 != null) {
      return email3;
    }
    else {
      return "";
    }
  }

  public String getHomepage() {
    return homepage;
  }

  public int getId() {
    return id;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHome(String home) {
    this.home = home;
    this.allPhones = getMergedPhones();
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    this.allPhones = getMergedPhones();
    return this;
  }

  public ContactData withWork(String work) {
    this.work = work;
    this.allPhones = getMergedPhones();
    return this;

  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    this.allEmails = this.getMergedEmails();
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    this.allEmails = this.getMergedEmails();
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    this.allEmails = this.getMergedEmails();
    return this;
  }

  public ContactData withHomepage(String homepage) {
    this.homepage = homepage;
    return this;
  }

  public String getMergedPhones() {
    return Stream.of(this.getHome(), this.getMobile(), this.getWork()).filter((s) -> ! s.equals(""))
            .map(ContactData::cleanedPhone)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedPhone(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public String getMergedEmails() {
    return Stream.of(this.getEmail(), this.getEmail2(), this.getEmail3()).filter((e) -> ! e.equals(""))
            .map(ContactData::cleanedMail)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedMail(String email) {
    return email.replaceAll("^\\s+", "").replaceAll("\\s+$", "").replaceAll("\\s+", " ");
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastName='" + lastName + '\'' +
            ", nickname='" + nickname + '\'' +
            ", title='" + title + '\'' +
            ", company='" + company + '\'' +
            ", address='" + address + '\'' +
            //", home='" + home + '\'' +
            //", mobile='" + mobile + '\'' +
            //", work='" + work + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", fax='" + fax + '\'' +
            //", email='" + email + '\'' +
            //", email2='" + email2 + '\'' +
            //", email3='" + email3 + '\'' +
            ", allEmails='" + allEmails + '\'' +
            ", homepage='" + homepage + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    //if (home != null ? !home.equals(that.home) : that.home != null) return false;
    //if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    //if (work != null ? !work.equals(that.work) : that.work != null) return false;
    if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
    if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
    //if (email != null ? !email.equals(that.email) : that.email != null) return false;
    //if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    //if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
    if (allEmails != null ? !allEmails.equals(that.allEmails) : that.allEmails != null) return false;
    if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
    return homepage != null ? homepage.equals(that.homepage) : that.homepage == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    //result = 31 * result + (home != null ? home.hashCode() : 0);
    //result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    //result = 31 * result + (work != null ? work.hashCode() : 0);
    result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
    result = 31 * result + (fax != null ? fax.hashCode() : 0);
    //result = 31 * result + (email != null ? email.hashCode() : 0);
    //result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    //result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
    result = 31 * result + (photo != null ? photo.hashCode() : 0);
    result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
    return result;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}