package org.fsqrt.minekraft.notifications

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * Implementation of a [NotificationService] using a [ConcurrentHashMap]. Good enough for general-purpose use
 */
class ConcurrentHashMapNotificationServiceImpl : NotificationService() {

    private val subscribers: ConcurrentHashMap<KClass<*>, MutableList<Subscriber<out Notification>>> = ConcurrentHashMap()

    override fun <T : Notification> subscribe(notification: KClass<T>, subscriber: Subscriber<T>) {
        val notificationSubscribers: MutableList<Subscriber<out Notification>> = subscribers.getOrPut(notification) { mutableListOf() }
        notificationSubscribers.add(subscriber)
    }

    override fun <T : Notification> subscribe(
        notification: KClass<T>,
        priority: SubscriberPriority,
        block: (T) -> Unit
    ) = subscribe(notification, Subscriber(priority, block))


    override fun <T : Notification> notify(notification: T): T {
        subscribers[notification::class]?.asSequence()
            ?.filterIsInstance<Subscriber<T>>() // or cast each item in a map() call
            ?.sortedByDescending { it.priority }
            ?.forEach { it.handle(notification) }
        return notification
    }

    override fun close() {
        subscribers.clear()
    }
}