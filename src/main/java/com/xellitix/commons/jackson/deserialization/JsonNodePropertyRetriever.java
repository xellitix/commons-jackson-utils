package com.xellitix.commons.jackson.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * {@link JsonNode} property retriever.
 *
 * @author Grayson Kuhns
 */
public interface JsonNodePropertyRetriever {

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
