package com.xellitix.commons.jackson;

import com.google.inject.AbstractModule;
import com.xellitix.commons.jackson.deserialization.JsonDeserializationUtilsModule;
import com.xellitix.commons.jackson.objectmapper.ObjectMapperModule;

/**
 * Jackson Utilities Google Guice module.
 *
 * @author Grayson Kuhns
 */
public class JacksonUtilsModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    install(new JsonDeserializationUtilsModule());
    install(new ObjectMapperModule());
  }
}
