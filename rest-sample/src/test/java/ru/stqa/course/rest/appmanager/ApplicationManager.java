package ru.stqa.course.rest.appmanager;

import com.jayway.restassured.RestAssured;


/**
 * Created by Оля on 01.10.2016.
 */
public class ApplicationManager {

  public void init() {
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

}
