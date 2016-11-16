package ru.stqa.course.rest.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import ru.stqa.course.rest.appmanager.ApplicationManager;
import java.io.IOException;


/**
 * Created by Оля on 28.09.2016.
 */
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();

  @BeforeClass
  public void setUp() throws Exception {
    app.init();
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    String json = RestAssured.get("http://demo.bugify.com/api/issues/" + issueId + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    JsonElement issueWithId = issues.getAsJsonArray().get(0);
    String state_name = issueWithId.getAsJsonObject().get("state_name").getAsString();
    if (state_name.equals("Closed") || (state_name.equals("Resolved"))) {
      return false;
    } else {
      return true;
    }
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
