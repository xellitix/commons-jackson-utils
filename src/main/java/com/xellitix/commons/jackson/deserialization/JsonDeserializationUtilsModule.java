package com.xellitix.commons.jackson.deserialization;

import com.google.inject.AbstractModule;

/**
 * JSON deserialization utilities Google Guice module.
 *
 * @author Grayson Kuhns
 */
public class JsonDeserializationUtilsModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    bind(JsonNodePropertyRetriever.class).to(DefaultJsonNodePropertyRetriever.class);
  }
}
