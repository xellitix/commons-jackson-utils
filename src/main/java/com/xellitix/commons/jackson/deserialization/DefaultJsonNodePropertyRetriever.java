package com.xellitix.commons.jackson.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.xellitix.commons.net.compat.java.uri.UriFactory;
import com.xellitix.commons.net.compat.java.url.UrlFactory;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
  private static final String MSG_TMPL_URL_INVALID =
      "Expected property \"%s\" to be a valid URL";

  // Dependencies
  private final UriFactory uriFactory;
  private final UrlFactory urlFactory;

  /**
   * Constructor.
   *
   * @param uriFactory The {@link UriFactory}.
   * @param urlFactory The {@link UrlFactory}.
   */
  @Inject
  DefaultJsonNodePropertyRetriever(
      final UriFactory uriFactory,
      final UrlFactory urlFactory) {

    this.uriFactory = uriFactory;
    this.urlFactory = urlFactory;
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
   * Retrieves the value of an {@link Integer} property if it exists.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link Integer} value or null if the property does not exist.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  @Override
  public Integer getIntOrNull(
      final JsonNode node,
      final String property,
      final JsonParser parser)
      throws JsonMappingException {

    final JsonNode prop = getPropertyOrNull(node, property);

    if (prop == null) {
      return null;
    }

    if (!(prop.isInt() || prop.isShort() )) {
      throw new JsonMappingException(parser,
          String.format(MSG_TMPL_INT_INVALID, property));
    }

    return prop.asInt();
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

    final Integer prop = getIntOrNull(node, property, parser);
    throwIfNull(prop, property, parser);
    return prop;
  }

  /**
   * Retrieves the value of a {@link Long} property if it exists.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link Long} value or null if the property does not exist.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  @Override
  public Long getLongOrNull(
      final JsonNode node,
      final String property,
      final JsonParser parser)
      throws JsonMappingException {

    final JsonNode prop = getPropertyOrNull(node, property);

    if (prop == null) {
      return null;
    }

    if (!(prop.isLong() || prop.isInt() || prop.isShort())) {
      throw new JsonMappingException(parser,
          String.format(MSG_TMPL_LONG_INVALID, property));
    }

    return prop.asLong();
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

    final Long prop = getLongOrNull(node, property, parser);
    throwIfNull(prop, property, parser);
    return prop;
  }

  /**
   * Retrieves the value of a {@link URI} property if it exists.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link URI} value or null if the property does not exist.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  @Override
  public URI getUriOrNull(
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

    final URI prop = getUriOrNull(node, property, parser);
    throwIfNull(prop, property, parser);
    return prop;
  }

  /**
   * Retrieves the value of a {@link URL} property if it exists.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link URL} value or null if the property does not exist.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  @Override
  public URL getUrlOrNull(
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
          String.format(MSG_TMPL_URL_INVALID, property));
    }

    final String url = prop.asText();

    try {
      return urlFactory.create(url);
    } catch (MalformedURLException ex) {
      throw new JsonMappingException(
          parser,
          String.format(MSG_TMPL_URL_INVALID, property),
          ex);
    }
  }

  /**
   * Retrieves the value of a {@link URL} property.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link URL} value.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  @Override
  public URL getUrl(
      final JsonNode node,
      final String property,
      final JsonParser parser)
      throws JsonMappingException {

    final URL prop = getUrlOrNull(node, property, parser);
    throwIfNull(prop, property, parser);
    return prop;
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
    final JsonNode prop = node.get(property);

    // The property is not defined
    if (prop == null) {
      return null;
    }

    // The property is defined, but its value is null
    if (prop.isNull()) {
      return null;
    }

    return prop;
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
