package org.fsqrt.minekraft.notifications

import org.fsqrt.minekraft.services.Service
import kotlin.reflect.KClass


/**
 * Service responsible for [Notification]s
 *
 * This Service manages Notifications sent from and subscribed by Modules.
 *
 *
 * @see Notification
 * @see Subscriber
 * @since 0.0.1
 * @author Max Bossing
 */
abstract class NotificationService : Service {
    /**
     * Subscribe to a [Notification]
     * @param T The [Notification] to subscribe to
     * @param notification [T] Class
     * @param subscriber [Subscriber] to attach
     * @since 0.0.1
     * @author Max Bossing
     */
    abstract fun <T : Notification> subscribe(
        notification: KClass<T>, subscriber: Subscriber<T>
    )

    /**
     * Subscribe to a [Notification]
     * @param T The [Notification] to subscribe to
     * @param notification [T] class
     * @param priority [SubscriberPriority] of this [Subscriber]
     * @param block Handler logic of this [Subscriber]
     * @author Max Bossing
     * @since 0.0.1
     */
    abstract fun <T : Notification> subscribe(
        notification: KClass<T>, priority: SubscriberPriority, block: (T) -> Unit
    )

    /**
     * Subscribe to a [Notification]
     * @param T The [Notification] to subscribe to
     * @param subscriber The [Subscriber] to attach
     * @since 0.0.1
     * @author Max Bossing
     */
    inline fun <reified T : Notification> subscribe(subscriber: Subscriber<T>) = subscribe(T::class, subscriber)

    /**
     * Subscribe to a [Notification]
     * @param T The [Notification] to subscribe to
     * @param priority [SubscriberPriority] of this [Subscriber]
     * @param block Handler logic of this [Subscriber]
     * @author Max Bossing
     * @since 0.0.1
     */
    inline fun <reified T : Notification> subscribe(
        priority: SubscriberPriority = SubscriberPriority.NORMAL, noinline block: (T) -> Unit
    ) = subscribe(T::class, priority, block)

    /**
     * Send a [Notification] to all [Subscriber]s
     *
     * @param T The [Notification] to send
     * @param notification The [Notification] object that will be sent to and modified by [Subscriber]s
     * @return The [Notification] object, modified by the [Subscriber]s
     * @since 0.0.1
     * @author Max Bossing
     */
    abstract fun <T : Notification> notify(notification: T): T
}