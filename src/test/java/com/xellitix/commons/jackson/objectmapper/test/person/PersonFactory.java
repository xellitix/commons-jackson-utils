package com.xellitix.commons.jackson.objectmapper.test.person;

/**
 * {@link Person} factory.
 *
 * @author Grayson Kuhns
 */
public interface PersonFactory {

  /**
   * Creates a {@link Person}.
   *
   * @param name The name.
   * @param age The age.
   * @return The {@link Person}.
   */
  Person create(String name, int age);
}
