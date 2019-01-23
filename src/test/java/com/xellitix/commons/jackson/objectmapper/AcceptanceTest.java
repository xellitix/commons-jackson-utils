package com.xellitix.commons.jackson.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.xellitix.commons.jackson.JacksonUtilsModule;
import com.xellitix.commons.jackson.objectmapper.test.org.Organization;
import com.xellitix.commons.jackson.objectmapper.test.org.OrganizationModule;
import com.xellitix.commons.jackson.objectmapper.test.person.Person;
import com.xellitix.commons.jackson.objectmapper.test.person.PersonModule;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link ObjectMapper} utilities acceptance test.
 *
 * @author Grayson Kuhns
 */
public class AcceptanceTest {

  // Constants
  private static final String ORG_JSON =
      "{" +
      "  \"name\": \"Taco Club\"," +
      "  \"members\": [" +
      "    {" +
      "      \"name\": \"Bob\"," +
      "      \"age\": 20" +
      "    }," +
      "    {" +
      "      \"name\": \"Sally\"," +
      "      \"age\": 32" +
      "    }" +
      "  ]" +
      "}";
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

  @Before
  public void setUp() {
    mapper = Guice
        .createInjector(
            new JacksonUtilsModule(),
            new PersonModule(),
            new OrganizationModule())
        .getInstance(ObjectMapper.class);
  }
}
