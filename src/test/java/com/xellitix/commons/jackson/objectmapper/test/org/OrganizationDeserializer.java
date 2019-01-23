package com.xellitix.commons.jackson.objectmapper.test.org;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdNodeBasedDeserializer;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.xellitix.commons.jackson.deserialization.JsonNodePropertyRetriever;
import com.xellitix.commons.jackson.objectmapper.test.person.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Organization} deserializer.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class OrganizationDeserializer extends StdNodeBasedDeserializer<Organization> {

  // Constants
  private static final String KEY_NAME = "name";
  private static final String KEY_MEMBERS = "members";

  // Dependencies
  private final OrganizationFactory factory;
  private final JsonNodePropertyRetriever propertyRetriever;
  private final Provider<ObjectMapper> objectMapperProvider;

  /**
   * Constructor.
   *
   * @param factory The {@link OrganizationFactory}.
   * @param propertyRetriever The {@link JsonNodePropertyRetriever}.
   * @param objectMapperProvider The {@link ObjectMapper} {@link Provider}.
   */
  @Inject
  OrganizationDeserializer(
      final OrganizationFactory factory,
      final JsonNodePropertyRetriever propertyRetriever,
      final Provider<ObjectMapper> objectMapperProvider) {

    super(Organization.class);

    this.factory = factory;
    this.propertyRetriever = propertyRetriever;
    this.objectMapperProvider = objectMapperProvider;
  }

  /**
   * Converts intermediate data into an {@link Organization}.
   *
   * @param root The intermediate data.
   * @param ctx The {@link DeserializationContext}.
   * @return The {@link Organization}.
   * @throws IOException If an exception occurs.
   */
  @Override
  public Organization convert(
      final JsonNode root,
      final DeserializationContext ctx)
      throws IOException {

    // Get the JsonParser
    final JsonParser parser = ctx.getParser();

    // Parse the name
    final String name = propertyRetriever.getString(root, KEY_NAME, parser);

    // Parse the members
    final JsonNode membersNode = propertyRetriever.getProperty(root, KEY_MEMBERS, parser);
    final List<Person> members = new ArrayList<>(membersNode.size());

    for (final JsonNode memberNode : membersNode) {
      final Person member = objectMapperProvider
          .get()
          .readValue(memberNode.traverse(), Person.class);
      members.add(member);
    }

    // Create the organization
    return factory.create(name, members);
  }
}
