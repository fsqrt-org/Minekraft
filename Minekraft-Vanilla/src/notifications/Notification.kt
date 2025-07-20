package org.fsqrt.minekraft.notifications


/**
 * Notifications are emitted for [Subscriber]s to listen for them, allowing modules to interact without much hassle
 *
 * If you want to create a custom Notification to emit, extend this (or [CancellableNotification] if needed)
 *
 * @since 0.0.1
 * @author Max Bossing
 * @see NotificationService
 * @see Subscriber
 */
open class Notification

/**
 * Root Class for [Notification]s which can be cancelled
 *
 * [Subscriber]s should **always** respect [isCancelled]
 *
 * @since 0.0.1
 * @author Max Bossing
 * @see NotificationService
 */
open class CancellableNotification(var isCancelled: Boolean = false) : Notification()