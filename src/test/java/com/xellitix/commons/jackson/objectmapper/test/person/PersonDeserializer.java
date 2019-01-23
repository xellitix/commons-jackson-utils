package com.xellitix.commons.jackson.objectmapper.test.person;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdNodeBasedDeserializer;
import com.google.inject.Inject;
import com.xellitix.commons.jackson.deserialization.JsonNodePropertyRetriever;
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
  private final JsonNodePropertyRetriever propertyRetriever;

  /**
   * Constructor.
   *
   * @param factory The {@link PersonFactory}.
   */
  @Inject
  PersonDeserializer(
      final PersonFactory factory,
      final JsonNodePropertyRetriever propertyRetriever) {

    super(Person.class);

    this.factory = factory;
    this.propertyRetriever = propertyRetriever;
  }

  /**
   * Converts intermediate data into a {@link Person}.
   *
   * @param root The intermediate data.
   * @param ctx The {@link DeserializationContext}.
   * @return The {@link Person}.
   * @throws IOException If an exception occurs.
   */
  @Override
  public Person convert(
      final JsonNode root,
      final DeserializationContext ctx)
      throws IOException {

    // Get the JsonParser
    final JsonParser parser = ctx.getParser();

    // Get the name
    final String name = propertyRetriever.getString(root, KEY_NAME, parser);

    // Get the age
    final int age = propertyRetriever.getInt(root, KEY_AGE, parser);

    // Create the person
    return factory.create(name, age);
  }
}
