package org.fsqrt.minekraft.services

import kotlinx.serialization.descriptors.SerialDescriptor
import org.fsqrt.minekraft.notifications.NotificationService
import java.util.concurrent.ConcurrentHashMap
import java.util.stream.Stream
import kotlin.reflect.KClass

/**
 * Implementation of a [ServiceRegistry] using a [ConcurrentHashMap]. Good enough for general-purpose use
 */
class ConcurrentHashMapServiceRegistryImpl : ServiceRegistry {
    private val services = ConcurrentHashMap<KClass<out Service>, Service>()

    override fun <ServiceT : Service> get(service: KClass<ServiceT>): ServiceT? {
        @Suppress("UNCHECKED_CAST")
        return services[service] as? ServiceT
    }

    override fun <ServiceT : Service> register(service: KClass<ServiceT>, provider: ServiceT): Boolean {
        return services.putIfAbsent(service, provider) == null
    }

    override fun close() {
        services.values.forEach(Service::closeReporting)
    }

    override fun getServices(): List<Service> = services.values.toList()
    override fun getServicesAsStream(): Stream<Service> = services.values.stream()
}