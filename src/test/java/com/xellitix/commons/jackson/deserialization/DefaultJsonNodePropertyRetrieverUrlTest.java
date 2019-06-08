package com.xellitix.commons.jackson.deserialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.doThrow;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JsonNode.class)
public class DefaultJsonNodePropertyRetrieverUrlTest extends AbstractJsonNodePropertyRetrieverTest {

  // Constants
  private static final String VALUE = "https://foo.bar";
  private static final String EX_MSG_PROP_INVALID =
      "Expected property \"foo\" to be a valid URL";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private URL url;

  // #getUrl
  @Test
  public void getUrlReturnsTheValue__WhenPropertyIsValid__Test() throws Exception {
    // Prepare the test
    doReturn(VALUE)
        .when(prop)
        .asText();
    doReturn(true)
        .when(prop)
        .isTextual();

    // Attempt to get the property value
    assertThat(propertyRetriever
        .getUrl(root, PROPERTY, parser))
        .isNotNull()
        .isEqualTo(url);
  }

  @Test
  public void getUrlThrowsException__WhenPropertyDoesNotExist__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_NULL);

    // Prepare the test
    doReturn(null)
        .when(root)
        .get(eq(PROPERTY));

    // Attempt to get the property value
    propertyRetriever.getUrl(root, PROPERTY, parser);
  }

  @Test
  public void getUrlThrowsException__WhenPropertyIsNotUrlString__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doReturn(false)
        .when(prop)
        .isTextual();

    // Attempt to get the property value
    propertyRetriever.getUrl(root, PROPERTY, parser);
  }

  @Test
  public void getUrlThrowsException__WhenPropertyIsNotValidUrl__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doThrow(new MalformedURLException("because"))
        .when(urlFactory)
        .create(eq(VALUE));

    // Attempt to get the property value
    propertyRetriever.getUrl(root, PROPERTY, parser);
  }

  // #getUrlOrNull
  @Test
  public void getUrlOrNullReturnsTheValue__WhenPropertyIsValid__Test() throws Exception {
    // Prepare the test
    doReturn(VALUE)
        .when(prop)
        .asText();
    doReturn(true)
        .when(prop)
        .isTextual();

    // Attempt to get the property value
    assertThat(propertyRetriever
        .getUrlOrNull(root, PROPERTY, parser))
        .isNotNull()
        .isEqualTo(url);
  }

  @Test
  public void getUrlOrNullReturnsNull__WhenPropertyDoesNotExist__Test() throws Exception {
    // Prepare the test
    doReturn(null)
        .when(root)
        .get(eq(PROPERTY));

    // Attempt to get the property value
    assertThat(propertyRetriever
        .getUrlOrNull(root, PROPERTY, parser))
        .isNull();
  }

  @Test
  public void getUrlOrNullThrowsException__WhenPropertyIsNotUrlString__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doReturn(false)
        .when(prop)
        .isTextual();

    // Attempt to get the property value
    propertyRetriever.getUrlOrNull(root, PROPERTY, parser);
  }

  @Test
  public void getUrlOrNullThrowsException__WhenPropertyIsNotValidUrl__Test() throws Exception {
    // Describe the exception to expect
    thrown.expect(JsonMappingException.class);
    thrown.expectMessage(EX_MSG_PROP_INVALID);

    // Prepare the test
    doThrow(new MalformedURLException("because"))
        .when(urlFactory)
        .create(eq(VALUE));

    // Attempt to get the property value
    propertyRetriever.getUrlOrNull(root, PROPERTY, parser);
  }

  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();

    // URL mocking
    url = new URL(VALUE);
    doReturn(url)
        .when(urlFactory)
        .create(eq(VALUE));
  }
}
