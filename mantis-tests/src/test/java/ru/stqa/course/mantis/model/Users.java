package ru.stqa.course.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Оля on 11.11.2016.
 */
public class Users  extends ForwardingSet<UserData> {

  private Set<UserData> delegate;

  public Users(Users users) {
    this.delegate = new HashSet<UserData>(users.delegate);
  }

  public Users() {
    this.delegate = new HashSet<UserData>();
  }

  public Users(Collection<UserData> groups) {
    this.delegate = new HashSet<UserData>(groups);
  }

  @Override
  protected Set<UserData> delegate() {
    return delegate;
  }
}
