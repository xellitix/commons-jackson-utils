package com.xellitix.commons.jackson.deserialization;

import com.google.inject.PrivateModule;
import com.xellitix.commons.net.compat.java.uri.UriModule;

/**
 * JSON deserialization utilities Google Guice module.
 *
 * @author Grayson Kuhns
 */
public class JsonDeserializationUtilsModule extends PrivateModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    bind(JsonNodePropertyRetriever.class).to(DefaultJsonNodePropertyRetriever.class);
    expose(JsonNodePropertyRetriever.class);
  }
}
