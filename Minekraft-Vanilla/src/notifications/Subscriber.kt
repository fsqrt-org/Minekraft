package org.fsqrt.minekraft.notifications


/**
 * Priority in which subscribers are called
 *
 * **NOTE:** Going against spigots (weird) decision to make lowest the highest priority, this will execute highest -> lowest -> monitor
 *
 * @see Subscriber
 * @since 0.0.1
 * @author Max Bossing
 */
enum class SubscriberPriority(val priority: Int) {
    MONITOR(5),
    LOWEST(4),
    LOW(3),
    NORMAL(2),
    HIGH(1),
    HIGHEST(0),
}

/**
 * A Subscriber listening for [Notification]s
 *
 * Subscribers are not normally created by instancing this class but rather by using [NotificationService.subscribe]
 *
 * If not using the inline functions, you may create a Subscriber by extending with an anonymous object
 *
 * @param T The [Notification] which this subscriber will subscribe to
 * @param priority The [SubscriberPriority] of this Subscriber
 * @param handler The [Notification] handler
 * @since 0.0.1
 * @author Max Bossing
 * @see Notification
 * @see NotificationService
 */
class Subscriber <T : Notification>(
    val priority: SubscriberPriority = SubscriberPriority.NORMAL,
    val handler: (T) -> Unit
) {
    fun handle(notification: T) = handler(notification)
}