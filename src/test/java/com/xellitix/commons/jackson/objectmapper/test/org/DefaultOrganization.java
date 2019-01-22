package com.xellitix.commons.jackson.objectmapper.test.org;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.xellitix.commons.jackson.objectmapper.test.person.Person;
import java.util.List;

/**
 * Default {@link Organization} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultOrganization implements Organization {

  // Properties
  private final String name;
  private final List<Person> members;

  /**
   * Constructor.
   *
   * @param name The name.
   * @param members The members.
   */
  @Inject
  DefaultOrganization(
      @Assisted final String name,
      @Assisted final List<Person> members) {

    this.name = name;
    this.members = members;
  }

  /**
   * Gets the name.
   *
   * @return The name.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the members.
   *
   * @return The members.
   */
  @Override
  public List<Person> getMembers() {
    return members;
  }
}
