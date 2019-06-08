package com.xellitix.commons.jackson.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.inject.Guice;
import com.xellitix.commons.jackson.JacksonUtilsModule;
import com.xellitix.commons.jackson.objectmapper.test.org.Organization;
import com.xellitix.commons.jackson.objectmapper.test.org.OrganizationModule;
import com.xellitix.commons.jackson.objectmapper.test.person.Person;
import com.xellitix.commons.jackson.objectmapper.test.person.PersonModule;
import com.xellitix.commons.net.compat.java.JavaNetCompatibilityModule;
import java.net.URL;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link ObjectMapper} utilities acceptance test.
 *
 * @author Grayson Kuhns
 */
public class AcceptanceTest {

  // Constants
  private static String ORG_JSON;
  private static final String ORG_NAME = "Taco Club";

  private static final String MEMBER1_NAME = "Bob";
  private static final int MEMBER1_AGE = 20;

  private static final String MEMBER2_NAME = "Sally";
  private static final int MEMBER2_AGE = 32;

  // Fixtures
  private ObjectMapper mapper;

  @Test
  public void deserialization__Test() throws Exception {
    // Parse the org
    Organization org = mapper.readValue(ORG_JSON, Organization.class);

    // Validate the org name
    assertThat(org
        .getName())
        .isNotNull()
        .isEqualTo(ORG_NAME);

    // Validate the members
    List<Person> members = org.getMembers();

    assertThat(members)
        .isNotNull()
        .hasSize(2);

    Person member = members.get(0);
    assertThat(member).isNotNull();
    assertThat(member
        .getName())
        .isNotNull()
        .isEqualTo(MEMBER1_NAME);
    assertThat(member
        .getAge())
        .isEqualTo(MEMBER1_AGE);

    member = members.get(1);
    assertThat(member).isNotNull();
    assertThat(member
        .getName())
        .isNotNull()
        .isEqualTo(MEMBER2_NAME);
    assertThat(member
        .getAge())
        .isEqualTo(MEMBER2_AGE);
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
    final URL jsonFileUrl = Resources.getResource("com/xellitix/commons/jackson/objectmapper/test/org.json");
    ORG_JSON = Resources.toString(jsonFileUrl, Charsets.UTF_8);
  }

  @Before
  public void setUp() {
    mapper = Guice
        .createInjector(
            new JavaNetCompatibilityModule(),
            new JacksonUtilsModule(),
            new PersonModule(),
            new OrganizationModule())
        .getInstance(ObjectMapper.class);
  }
}
