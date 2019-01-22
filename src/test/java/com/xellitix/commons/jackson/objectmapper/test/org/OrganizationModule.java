package com.xellitix.commons.jackson.objectmapper.test.org;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;

/**
 * {@link Organization} Google Guice module.
 *
 * @author Grayson Kuhns
 */
public class OrganizationModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    install(new FactoryModuleBuilder()
      .implement(Organization.class, DefaultOrganization.class)
      .build(OrganizationFactory.class));

    Multibinder<JsonDeserializer> deserializerMultibinder =
        Multibinder.newSetBinder(binder(), JsonDeserializer.class);

    deserializerMultibinder.addBinding().to(OrganizationDeserializer.class);
  }
}
