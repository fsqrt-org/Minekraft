package org.fsqrt.minekraft.services

import org.fsqrt.minekraft.utils.SilentCloseable

/**
 * Root Class for Services registered in the [ServiceRegistry]
 *
 * To create a service, extend this class and call [ServiceRegistry.register]
 *
 * To then get the service, call [ServiceRegistry.get]
 *
 * @see ServiceRegistry
 * @since 0.0.1
 * @author Max Bossing
 */
interface Service : SilentCloseable