package com.xellitix.commons.jackson.objectmapper.test.person;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;

/**
 * {@link Person} Google Guice module.
 *
 * @author Grayson Kuhns
 */
public class PersonModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    install(new FactoryModuleBuilder()
      .implement(Person.class, DefaultPerson.class)
      .build(PersonFactory.class));

    Multibinder<JsonDeserializer> deserializerMultibinder =
        Multibinder.newSetBinder(binder(), JsonDeserializer.class);

    deserializerMultibinder.addBinding().to(PersonDeserializer.class);
  }
}
