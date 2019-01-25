package com.xellitix.commons.jackson.deserialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.doThrow;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * {@link DefaultJsonNodePropertyRetriever} {@link URI} value retrieval test case.
 *
 * @author Grayson Kuhns
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JsonNode.class)
public class DefaultJsonNodePropertyRetrieverUriTest extends AbstractJsonNodePropertyRetrieverTest {

  // Constants
  private static final String VALUE = "https://foo.bar";
  private static final String EX_MSG_PROP_INVALID =
      "Expected property \"foo\" to be a valid URI";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private URI uri;

  @Test
  public void getUriReturnsTheValue__WhenPropertyIsValid__Test() throws Exception {
    // Prepare the test
    doReturn(VALUE)
        .when(prop)
        .asText();
    doReturn(true)
        .when(prop)
        .isTextual();

    // Attempt to get the property value
    assertThat(propertyRetriever
        .getUri(root, PROPERTY, parser))
        .isNotNull()
        .isEqualTo(uri);
  }

  @Test
  public void getUriThrowsException__WhenPropertyDoesNotExist__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_NULL);

    // Prepare the test
    doReturn(null)
        .when(root)
        .get(eq(PROPERTY));

    // Attempt to get the property value
    propertyRetriever.getUri(root, PROPERTY, parser);
  }

  @Test
  public void getUriThrowsException__WhenPropertyIsNotUriString__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doReturn(false)
        .when(prop)
        .isTextual();

    // Attempt to get the property value
    propertyRetriever.getUri(root, PROPERTY, parser);
  }

  @Test
  public void getUriThrowsException__WhenPropertyIsNotValidUri__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doThrow(new URISyntaxException(VALUE, "because"))
        .when(uriFactory)
        .create(eq(VALUE));

    // Attempt to get the property value
    propertyRetriever.getUri(root, PROPERTY, parser);
  }

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();

    // URI mocking
    uri = new URI(VALUE);
    doReturn(uri)
        .when(uriFactory)
        .create(eq(VALUE));
  }
}
