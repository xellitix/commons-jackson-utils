package com.xellitix.commons.jackson.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.xellitix.commons.net.compat.java.uri.UriFactory;
import org.junit.Before;

import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.doReturn;

/**
 * Abstract {@link JsonNodePropertyRetriever} test case.
 *
 * @author Grayson Kuhns
 */
public class AbstractJsonNodePropertyRetrieverTest {

  // Constants
  protected static final String PROPERTY = "foo";
  protected static final String EX_MSG_PROP_NULL =
      "Expected property \"foo\" to be defined";

  // Fixtures
  protected JsonNode root;
  protected JsonNode prop;

  protected JsonParser parser;

  protected UriFactory uriFactory;
  protected JsonNodePropertyRetriever propertyRetriever;

  @Before
  public void setUp() {
    // Mock the intermediate representation
    prop = mock(JsonNode.class);
    root = mock(JsonNode.class);
    doReturn(prop)
        .when(root)
        .get(eq(PROPERTY));

    // Mock the JsonParser
    parser = mock(JsonParser.class);

    // Mock the URI factory
    uriFactory = mock(UriFactory.class);

    // Create the test subject
    propertyRetriever = new DefaultJsonNodePropertyRetriever(uriFactory);
  }
}
