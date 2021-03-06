/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.clustering.infinispan;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.Properties;

import org.infinispan.configuration.cache.CacheMode;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.network.OutboundSocketBinding;
import org.jboss.logging.annotations.Cause;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;
import org.jboss.logging.Messages;
import org.jboss.msc.inject.InjectionException;
import org.jboss.msc.service.StartException;

/**
 * InfinispanMessages
 *
 * logging id range: 10290 - 10299,10380 - 10389
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
@MessageBundle(projectCode = "JBAS")
public interface InfinispanMessages {

    /**
     * A logger with the category of the default clustering package.
     */
    InfinispanMessages MESSAGES = Messages.getBundle(InfinispanMessages.class);

    /**
     * Creates an exception indicating a failure to resolve the outbound socket binding represented by the
     * {@code binding} parameter.
     *
     * @param cause the cause of the error.
     * @param binding the outbound socket binding.
     *
     * @return a {@link org.jboss.as.controller.persistence.ConfigurationPersistenceException} for the error.
     */
    @Message(id = 10290, value = "Could not resolve destination address for outbound socket binding named '%s'")
    InjectionException failedToInjectSocketBinding(@Cause UnknownHostException cause, OutboundSocketBinding binding);

    @Message(id = 10291, value = "Failed to add %s %s cache to non-clustered %s cache container.")
    StartException transportRequired(CacheMode mode, String cache, String cacheContainer);

    /**
     * Creates an exception indicating an invalid cache store.
     *
     * @param cause          the cause of the error.
     * @param cacheStoreName the name of the cache store.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 10292, value = "%s is not a valid cache store")
    OperationFailedException invalidCacheStore(@Cause Throwable cause, String cacheStoreName);

    /**
     * Creates an exception indicating an invalid cache store.
     *
     * @param cacheName     the name of the cache store.
     * @param cacheContainerName the container name.
     *
     * @return an {@link IllegalArgumentException} for the error.
     */
    @Message(id = 10293, value = "%s is not a valid default cache. The %s cache container does not contain a cache with that name")
    IllegalArgumentException invalidDefaultCache(String cacheName, String cacheContainerName);

    /**
     * Creates an exception indicating the an executor property is invalid.
     *
     * @param id         the id of the property.
     * @param properties the properties that were searched.
     *
     * @return an {@link IllegalStateException} for the error.
     */
    @Message(id = 10294, value = "No %s property was specified within the executor properties: %s")
    IllegalStateException invalidExecutorProperty(String id, Properties properties);

    /**
     * Creates an exception indicating the an transport property is invalid.
     *
     * @param id         the id of the property.
     * @param properties the properties that were searched.
     *
     * @return an {@link IllegalStateException} for the error.
     */
    @Message(id = 10295, value = "No %s property was specified within the transport properties: %s")
    IllegalStateException invalidTransportProperty(String id, Properties properties);

    /**
     * Creates an exception indicating that the cache is aborting after the specified number of retries.
     *
     * @param cause           the cause of the error.
     * @param numberOfRetries the number of retries.
     *
     * @return a {@link RuntimeException}
     */
    @Message(id = 10296, value = "Aborting cache operation after %d retries.")
    RuntimeException abortingCacheOperation(@Cause Throwable cause, int numberOfRetries);

    /**
     * Creates an exception indicating the an operation parameter is invalid.
     *
     * @param id         the id of the parameter.
     * @param allowableValues the allowable values for the parameter
     *
     * @return the String.
     */
    @Message(id = 10297, value = "Invalid value for parameter %s. Allowable values: %s")
    String invalidParameterValue(String id, String allowableValues);

    /**
     * Creates an exception indicating the a cache store cannot be added as one already exists.
     *
     * @param existingStoreName the store which already exists.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 10298, value = "Cache store cannot be created: cache store %s is already defined")
    OperationFailedException cacheStoreAlreadyDefined(String existingStoreName);

    /**
     * Creates an exception indicating the a cache store cannot be added as one already exists.
     *
     * @param propertyKey the name of the property.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 10299, value = "Value for property with key %s is not defined")
    OperationFailedException propertyValueNotDefined(String propertyKey);

    /**
     * A message indicating that the resource could not be located.
     *
     * @param resourceName the name of the resource.
     *
     * @return the String message.
     */
    @Message(id = 10380, value = "Failed to locate %s")
    String notFound(String resourceName);

    /**
     * A message indicating that the resource could not be parsed.
     *
     * @param resourceName the name of the resource.
     *
     * @return IllegalStateException instance.
     */
    @Message(id = 10381, value = "Failed to parse %s")
    IllegalStateException failedToParse(@Cause Throwable cause, URL url);

    /**
     * Creates an exception indicating a singleton resource already exists.
     *
     * @param resourceName the name of the resource.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 10382, value = "Add operation failed: singleton %s already exists.")
    OperationFailedException singletonResourceAlreadyExists(String resourceName);

    /**
     * Creates an exception indicating unable to remove an alias from an empty list of aliases.
     *
     * @param aliasName the name of the alias.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 10383, value = "cannot remove alias % from empty list.")
    OperationFailedException cannotRemoveAliasFromEmptyList(String aliasName);

    /**
     * Creates an exception indicating that an attribute has been deprecated.
     *
     * @param attributeName the name of the deprecated attribute
     * @return an {@link OperationFailedException} for the error
     */
    @Message(id = 10384, value = "Attribute '%s' has been deprecated.")
    OperationFailedException attributeDeprecated(String attributeName);

    @Message(id = 10385, value = "Attribute 'segments' is an expression and therefore cannot be translated to legacy attribute 'virtual-nodes'. This resource will need to be ignored on that host.")
    String virtualNodesDoesNotSupportExpressions();

    @Message(id = 10386, value = "Unknown metric %s")
    String unknownMetric(String metricName);

    /**
     * Creates an exception indicating failure to invoke an operation.
     *
     * @param operation the operation name.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 10387, value = "Failed to invoke operation: %s")
    OperationFailedException failedToInvokeOperation(@Cause Throwable cause, String operation);
}
