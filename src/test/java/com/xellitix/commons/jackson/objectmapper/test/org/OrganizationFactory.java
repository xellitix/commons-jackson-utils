package com.xellitix.commons.jackson.objectmapper.test.org;

import com.xellitix.commons.jackson.objectmapper.test.person.Person;
import java.util.List;

/**
 * {@link Organization} factory.
 *
 * @author Grayson Kuhns
 */
public interface OrganizationFactory {

  /**
   * Creates an {@link Organization}.
   *
   * @param name The name.
   * @param members The members.
   * @return The {@link Organization}.
   */
  Organization create(String name, List<Person> members);
}
