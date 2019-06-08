package com.xellitix.commons.jackson.objectmapper;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Set;

/**
 * Default {@link ObjectMapperProvider} implementation.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class AutoRegistrationObjectMapperProvider implements ObjectMapperProvider {

  // Properties
  private ObjectMapper mapper;

  // Dependencies
  private final Set<JsonDeserializer> deserializers;

  /**
   * Constructor.
   *
   * @param deserializers The {@link JsonDeserializer}s to register.
   */
  @Inject
  AutoRegistrationObjectMapperProvider(final Set<JsonDeserializer> deserializers) {
    this.deserializers = deserializers;
  }

  /**
   * Gets an {@link ObjectMapper}.
   *
   * @return The {@link ObjectMapper}.
   */
  @Override
  public ObjectMapper get() {
    // ObjectMapper lazy initialization
    if (mapper == null) {
      mapper = create();
    }

    return mapper;
  }

  private ObjectMapper create() {
    final ObjectMapper mapper = new ObjectMapper();
    final SimpleModule module = new SimpleModule();

    // Register the deserializers
    deserializers.forEach(des ->
        module.addDeserializer(des.handledType(), des));

    // Register the module
    mapper.registerModule(module);
    return mapper;
  }
}
