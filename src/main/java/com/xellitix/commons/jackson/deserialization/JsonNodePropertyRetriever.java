package com.xellitix.commons.jackson.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;

/**
 * {@link JsonNode} property retriever.
 *
 * @author Grayson Kuhns
 */
public interface JsonNodePropertyRetriever {

  /**
   * Retrieves the value of a {@link String} property if it exists.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link String} value or null if the property does not exist.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  String getStringOrNull(JsonNode node, String property, JsonParser parser) throws JsonMappingException;

  /**
   * Retrieves the value of a {@link String} property.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link String} value.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  String getString(JsonNode node, String property, JsonParser parser) throws JsonMappingException;

  /**
   * Retrieves the value of an {@link Integer} property if it exists.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link Integer} value or null if the property does not exist.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  Integer getIntOrNull(JsonNode node, String property, JsonParser parser) throws JsonMappingException;

  /**
   * Retrieves the value of an {@link Integer} property.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link Integer} value.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  int getInt(JsonNode node, String property, JsonParser parser) throws JsonMappingException;

  /**
   * Retrieves the value of a {@link Long} property.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link Long} value.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  long getLong(JsonNode node, String property, JsonParser parser) throws JsonMappingException;

  /**
   * Retrieves the value of a {@link URI} property.
   *
   * @param node The {@link JsonNode} containing the property.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The {@link URI} value.
   * @throws JsonMappingException If an error occurs while retrieving the property value.
   */
  URI getUri(JsonNode node, String property, JsonParser parser) throws JsonMappingException;

  /**
   * Gets a property {@link JsonNode} if it exists.
   *
   * @param node The root object {@link JsonNode}.
   * @param property The property name.
   * @return The property {@link JsonNode} or null if the property does not exist.
   */
  JsonNode getPropertyOrNull(JsonNode node, String property);

  /**
   * Gets a property {@link JsonNode}.
   *
   * @param node The root object {@link JsonNode}.
   * @param property The property name.
   * @param parser The {@link JsonParser}.
   * @return The property {@link JsonNode}.
   * @throws JsonMappingException If the property is not defined.
   */
  JsonNode getProperty(JsonNode node, String property, JsonParser parser) throws JsonMappingException;
}
