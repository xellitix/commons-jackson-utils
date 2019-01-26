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
 * {@link DefaultJsonNodePropertyRetriever} long value retrieval test case.
 *
 * @author Grayson Kuhns
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JsonNode.class)
public class DefaultJsonNodePropertyRetrieverLongTest extends AbstractJsonNodePropertyRetrieverTest {

  // Constants
  private static final long VALUE = 42l;
  private static final String EX_MSG_PROP_INVALID =
      "Expected property \"foo\" to be a long";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // #getLong
  @Test
  public void getLongReturnsTheValue__WhenPropertyIsValid__Test() throws Exception {
    // Prepare the test
    doReturn(VALUE)
        .when(prop)
        .asLong();
    doReturn(true)
        .when(prop)
        .isLong();

    // Attempt to get the property value
    assertThat(propertyRetriever
        .getLong(root, PROPERTY, parser))
        .isEqualTo(VALUE);
  }

  @Test
  public void getLongThrowsException__WhenPropertyDoesNotExist__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_NULL);

    // Prepare the test
    doReturn(null)
        .when(root)
        .get(eq(PROPERTY));

    // Attempt to get the property value
    propertyRetriever.getLong(root, PROPERTY, parser);
  }

  @Test
  public void getLongThrowsException__WhenPropertyIsNotLong__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doReturn(false)
        .when(prop)
        .isLong();

    // Attempt to get the property value
    propertyRetriever.getLong(root, PROPERTY, parser);
  }

  // #getLongOrNull
  @Test
  public void getLongOrNullReturnsTheValue__WhenPropertyIsValid__Test() throws Exception {
    // Prepare the test
    doReturn(VALUE)
        .when(prop)
        .asLong();
    doReturn(true)
        .when(prop)
        .isLong();

    // Attempt to get the property value
    assertThat(propertyRetriever
        .getLongOrNull(root, PROPERTY, parser))
        .isEqualTo(VALUE);
  }

  @Test
  public void getLongOrNullReturnsNull__WhenPropertyDoesNotExist__Test() throws Exception {
    // Prepare the test
    doReturn(null)
        .when(root)
        .get(eq(PROPERTY));

    // Attempt to get the property value
    assertThat(propertyRetriever
        .getLongOrNull(root, PROPERTY, parser))
        .isNull();
  }

  @Test
  public void getLongOrNullThrowsException__WhenPropertyIsNotLong__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doReturn(false)
        .when(prop)
        .isLong();

    // Attempt to get the property value
    propertyRetriever.getLongOrNull(root, PROPERTY, parser);
  }
}
