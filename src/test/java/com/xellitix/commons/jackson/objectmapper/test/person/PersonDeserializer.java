package com.xellitix.commons.jackson.objectmapper.test.person;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdNodeBasedDeserializer;
import com.google.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * {@link Person} deserializer.
 *
 * @author Grayson Kuhns
 */
public class PersonDeserializer extends StdNodeBasedDeserializer<Person> {

  // Constants
  private static final String KEY_NAME = "name";
  private static final String KEY_AGE = "age";

  // Dependencies
  private final PersonFactory factory;

  /**
   * Constructor.
   *
   * @param factory The {@link PersonFactory}.
   */
  @Inject
  PersonDeserializer(final PersonFactory factory) {
    super(Person.class);
    this.factory = factory;
  }

  /**
   * Converts intermediate data into a {@link Person}.
   *
   * @param jsonNode The intermediate data.
   * @param ctx The {@link DeserializationContext}.
   * @return The {@link Person}.
   * @throws IOException If an exception occurs.
   */
  @Override
  public Person convert(
      final JsonNode jsonNode,
      final DeserializationContext ctx)
      throws IOException {

    // Get the name
    final String name = jsonNode.get(KEY_NAME).asText();

    // Get the age
    final int age = jsonNode.get(KEY_AGE).asInt();

    // Create the person
    return factory.create(name, age);
  }
}
