package ru.stqa.course.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by ola on 17.11.2016.
 */
public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("7354b83e8d1342189ad936e900f28ec2c830e28a");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("butaevao", "java_course")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
