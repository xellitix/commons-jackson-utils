package com.xellitix.commons.jackson.deserialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
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
public class DefaultJsonNodePropertyRetrieverStringTest extends AbstractJsonNodePropertyRetrieverTest {

  // Constants
  private static final String VALUE = "bar";
  private static final String EX_MSG_PROP_INVALID =
      "Expected property \"foo\" to be a string";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // #getString
  @Test
  public void getStringReturnsTheValue__WhenPropertyIsValid__Test() throws Exception {
    // Prepare the test
    doReturn(VALUE)
        .when(prop)
        .asText();
    doReturn(true)
        .when(prop)
        .isTextual();

    // Attempt to get the property value
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

  // #getStringOrNull
  @Test
  public void getStringOrNullReturnsTheValue__WhenPropertyIsValid__Test() throws Exception {
    // Prepare the test
    doReturn(VALUE)
        .when(prop)
        .asText();
    doReturn(true)
        .when(prop)
        .isTextual();

    // Attempt to get the property value
    assertThat(propertyRetriever
        .getStringOrNull(root, PROPERTY, parser))
        .isNotNull()
        .isEqualTo(VALUE);
  }

  @Test
  public void getStringOrNullReturnsNull__WhenPropertyDoesNotExist__Test() throws Exception {
    // Prepare the test
    doReturn(null)
        .when(root)
        .get(eq(PROPERTY));

    // Attempt to get the property value
    assertThat(propertyRetriever
        .getStringOrNull(root, PROPERTY, parser))
        .isNull();
  }

  @Test
  public void getStringOrNullThrowsException__WhenPropertyIsNotString__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doReturn(false)
        .when(prop)
        .isTextual();

    // Attempt to get the property value
    propertyRetriever.getStringOrNull(root, PROPERTY, parser);
  }
}
