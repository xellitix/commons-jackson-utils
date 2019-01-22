package com.xellitix.commons.jackson.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;

/**
 * {@link ObjectMapper} Google Guice module.
 *
 * @author Grayson Kuhns
 */
public class ObjectMapperModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    bind(ObjectMapperProvider.class).to(AutoRegistrationObjectMapperProvider.class);
    bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class);
  }
}
