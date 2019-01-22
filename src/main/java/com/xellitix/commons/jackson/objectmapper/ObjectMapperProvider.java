package com.xellitix.commons.jackson.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Provider;

/**
 * {@link ObjectMapper} {@link Provider}.
 *
 * @author Grayson Kuhns
 */
public interface ObjectMapperProvider extends Provider<ObjectMapper> {
}
