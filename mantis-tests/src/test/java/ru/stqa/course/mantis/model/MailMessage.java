package ru.stqa.course.mantis.model;

/**
 * Created by Оля on 11.11.2016.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }
}
