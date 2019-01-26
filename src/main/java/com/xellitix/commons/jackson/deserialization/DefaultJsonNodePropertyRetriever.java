package com.xellitix.commons.jackson.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.xellitix.commons.net.compat.java.uri.UriFactory;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Default {@link JsonNodePropertyRetriever} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class DefaultJsonNodePropertyRetriever implements JsonNodePropertyRetriever {

  // Messages
  private static final String MSG_TMPL_PROP_MISSING =
      "Expected property \"%s\" to be defined";

  private static final String MSG_TMPL_STRING_INVALID =
      "Expected property \"%s\" to be a string";
  private static final String MSG_TMPL_INT_INVALID =
      "Expected property \"%s\" to be an integer";
  private static final String MSG_TMPL_LONG_INVALID =
      "Expected property \"%s\" to be a long";
  private static final String MSG_TMPL_URI_INVALID =
      "Expected property \"%s\" to be a valid URI";

  // Dependencies
  private final UriFactory uriFactory;

  /**
   * Constructor.
   *
   * @param uriFactory The {@link UriFactory}.
   */
  @Inject
  DefaultJsonNodePropertyRetriever(final UriFactory uriFactory) {
    this.uriFactory = uriFactory;
  }

  /**
   * Retrieves the value of a {@link String} property if it exists.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link String} value or null if the property does not exist.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  @Override
  public String getStringOrNull(
      final JsonNode node,
      final String property,
      final JsonParser parser)
      throws JsonMappingException {

    final JsonNode prop = getPropertyOrNull(node, property);

    if (prop == null) {
      return null;
    }

    if (!prop.isTextual()) {
      throw new JsonMappingException(parser,
          String.format(MSG_TMPL_STRING_INVALID, property));
    }

    return prop.asText();
  }

  /**
   * Retrieves the value of a {@link String} property.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link String} value.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  @Override
  public String getString(
      final JsonNode node,
      final String property,
      final JsonParser parser)
      throws JsonMappingException {

    final String value = getStringOrNull(node, property, parser);
    throwIfNull(value, property, parser);
    return value;
  }

  /**
   * Retrieves the value of an {@link Integer} property.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link Integer} value.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  @Override
  public int getInt(
      final JsonNode node,
      final String property,
      final JsonParser parser)
      throws JsonMappingException {

    final JsonNode prop = getProperty(node, property, parser);

    if (!prop.isInt()) {
      throw new JsonMappingException(parser,
          String.format(MSG_TMPL_INT_INVALID, property));
    }

    return prop.asInt();
  }

  /**
   * Retrieves the value of a {@link Long} property.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link Long} value.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  @Override
  public long getLong(
      final JsonNode node,
      final String property,
      final JsonParser parser)
      throws JsonMappingException {

    final JsonNode prop = getProperty(node, property, parser);

    if (!prop.isLong()) {
      throw new JsonMappingException(parser,
          String.format(MSG_TMPL_LONG_INVALID, property));
    }

    return prop.asLong();
  }

  /**
   * Retrieves the value of a {@link URI} property.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link URI} value.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  @Override
  public URI getUri(
      final JsonNode node,
      final String property,
      final JsonParser parser)
      throws JsonMappingException {

    final JsonNode prop = getProperty(node, property, parser);

    if (!prop.isTextual()) {
      throw new JsonMappingException(parser,
          String.format(MSG_TMPL_URI_INVALID, property));
    }

    final String uri = prop.asText();

    try {
      return uriFactory.create(uri);
    } catch (URISyntaxException ex) {
      throw new JsonMappingException(
          parser,
          String.format(MSG_TMPL_URI_INVALID, property),
          ex);
    }
  }

  /**
   * Gets a property {@link JsonNode}.
   *
   * @param node The root object {@link JsonNode}.
   * @param property The property name.
   * @return The property {@link JsonNode} or null if the property does not exist.
   */
  @Override
  public JsonNode getPropertyOrNull(final JsonNode node, final String property) {
    return node.get(property);
  }

  /**
   * Gets a property {@link JsonNode}.
   *
   * @param node The root object {@link JsonNode}.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The property {@link JsonNode}.
   * @throws JsonMappingException If the property is not defined.
   */
  @Override
  public JsonNode getProperty(
      final JsonNode node,
      final String property,
      final JsonParser parser)
      throws JsonMappingException {

    final JsonNode prop = getPropertyOrNull(node, property);
    throwIfNull(prop, property, parser);
    return prop;
  }

  private void throwIfNull(
      final Object obj,
      final String property,
      final JsonParser parser)
      throws JsonMappingException {

    if (obj == null) {
      throw new JsonMappingException(parser,
          String.format(MSG_TMPL_PROP_MISSING, property));
    }
  }
}
