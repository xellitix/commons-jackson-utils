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
 * {@link DefaultJsonNodePropertyRetriever} integer value retrieval test case.
 *
 * @author Grayson Kuhns
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JsonNode.class)
public class DefaultJsonNodePropertyRetrieverIntegerTest extends AbstractJsonNodePropertyRetrieverTest {

  // Constants
  private static final int VALUE = 42;
  private static final String EX_MSG_PROP_INVALID =
      "Expected property \"foo\" to be an integer";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void getIntReturnsTheValue__WhenPropertyIsValid__Test() throws Exception {
    // Prepare the test
    doReturn(VALUE)
        .when(prop)
        .asInt();
    doReturn(true)
        .when(prop)
        .isInt();

    // Attempt to get the property value
    assertThat(propertyRetriever
        .getInt(root, PROPERTY, parser))
        .isEqualTo(VALUE);
  }

  @Test
  public void getIntThrowsException__WhenPropertyDoesNotExist__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_NULL);

    // Prepare the test
    doReturn(null)
        .when(root)
        .get(eq(PROPERTY));

    // Attempt to get the property value
    propertyRetriever.getInt(root, PROPERTY, parser);
  }

  @Test
  public void getIntThrowsException__WhenPropertyIsNotInteger__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doReturn(false)
        .when(prop)
        .isInt();

    // Attempt to get the property value
    propertyRetriever.getInt(root, PROPERTY, parser);
  }
}
