package com.xellitix.commons.jackson.objectmapper.test.org;

import com.xellitix.commons.jackson.objectmapper.test.person.Person;
import java.util.List;

/**
 * Organization model.
 *
 * @author Grayson Kuhns
 */
public interface Organization {

  /**
   * Gets the name.
   *
   * @return The name.
   */
  String getName();

  /**
   * Gets the members.
   *
   * @return The members.
   */
  List<Person> getMembers();
}
