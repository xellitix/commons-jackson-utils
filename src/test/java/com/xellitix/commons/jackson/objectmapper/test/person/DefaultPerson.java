package com.xellitix.commons.jackson.objectmapper.test.person;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Default {@link Person} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultPerson implements Person {

  // Properties
  private final String name;
  private final int age;

  /**
   * Constructor.
   *
   * @param name The name.
   * @param age The age.
   */
  @Inject
  DefaultPerson(
      @Assisted final String name,
      @Assisted final int age) {

    this.name = name;
    this.age = age;
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
   * Gets the age.
   *
   * @return The age.
   */
  @Override
  public int getAge() {
    return age;
  }
}
