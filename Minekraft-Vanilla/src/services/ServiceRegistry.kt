package org.fsqrt.minekraft.services

import java.util.stream.Stream
import kotlin.reflect.KClass

/**
 * A registry of [Service]s, to allow Components/Modules to make their API accessible to others
 *
 * @since 0.0.1
 * @author Max Bossing
 * @see KaemonService
 */
interface ServiceRegistry : Service {
    /**
     * Get all [Service]s in this Registry
     * @since 0.0.1
     * @author Max Bossing
     */
    fun getServices(): List<Service>

    /**
     * Get all [Service]s in this Registry as a [Stream]
     * @since 0.0.1
     * @author Max Bossing
     */
    fun getServicesAsStream(): Stream<Service>

    /**
     * Get a [Service] from the Registry
     * @param ServiceT Type of the Service Interface for this Service
     * @param service The [Service] interface of this Service
     * @return The provider registered for this [Service], null if none found
     * @since 0.0.1
     * @author Max Bossing
     */
    fun <ServiceT : Service> get(service: KClass<ServiceT>): ServiceT?

    /**
     * Register a [Service] to this Registry
     * @param ServiceT Type of the Service Interface for this Service
     * @param service The [Service] Interface of this Service
     * @param provider The object providing the implementation of [service]
     * @since 0.0.1
     * @author Max Bossing
     */
    fun <ServiceT : Service> register(service: KClass<ServiceT>, provider: ServiceT): Boolean
}