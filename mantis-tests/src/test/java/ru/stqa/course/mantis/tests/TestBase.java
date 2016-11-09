package ru.stqa.course.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import ru.stqa.course.mantis.appmanager.ApplicationManager;


/**
 * Created by Оля on 28.09.2016.
 */
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeClass
  public void setUp() throws Exception {
    app.init();
    System.out.println(app);
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() {
    app.stop();
  }


}
