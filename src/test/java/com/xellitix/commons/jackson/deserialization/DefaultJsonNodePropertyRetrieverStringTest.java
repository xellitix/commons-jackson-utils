package com.xellitix.commons.jackson.deserialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.xellitix.commons.net.compat.java.uri.UriFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * {@link DefaultJsonNodePropertyRetriever} String value retrieval test case.
 *
 * @author Grayson Kuhns
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JsonNode.class)
public class DefaultJsonNodePropertyRetrieverStringTest {

  // Constants
  private static final String PROPERTY = "foo";
  private static final String VALUE = "bar";

  private static final String EX_MSG_PROP_NULL =
      "Expected property \"foo\" to be defined";
  private static final String EX_MSG_PROP_INVALID =
      "Expected property \"foo\" to be a string";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private JsonNode root;
  private JsonNode prop;

  private JsonParser parser;
  private JsonNodePropertyRetriever propertyRetriever;

  @Test
  public void getStringReturnsTheValue__WhenPropertyIsValid__Test() throws Exception {
    assertThat(propertyRetriever
        .getString(root, PROPERTY, parser))
        .isNotNull()
        .isEqualTo(VALUE);
  }

  @Test
  public void getStringThrowsException__WhenPropertyDoesNotExist__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_NULL);

    // Prepare the test
    doReturn(null)
        .when(root)
        .get(eq(PROPERTY));

    // Attempt to get the property value
    propertyRetriever.getString(root, PROPERTY, parser);
  }

  @Test
  public void getStringThrowsException__WhenPropertyIsNotString__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doReturn(false)
        .when(prop)
        .isTextual();

    // Attempt to get the property value
    propertyRetriever.getString(root, PROPERTY, parser);
  }

  @Before
  public void setUp() {
    // Mock the intermediate representation
    prop = mock(JsonNode.class);
    doReturn(VALUE)
        .when(prop)
        .asText();
    doReturn(true)
        .when(prop)
        .isTextual();

    root = mock(JsonNode.class);
    doReturn(prop)
        .when(root)
        .get(eq(PROPERTY));

    // Mock the JsonParser
    parser = mock(JsonParser.class);

    // Create the test subject
    propertyRetriever = new DefaultJsonNodePropertyRetriever(mock(UriFactory.class));
  }
}
