package org.fsqrt.minekraft.annotation

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.TYPEALIAS, AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
@RequiresOptIn(
    message = "This is an internal API that should not be used outside of Minekraft." +
            "No compatibility guarantees are provided." +
            "If there is a reason you are using an Internal API (eg. no other way is provided)" +
            "you should report this to the Minekraft team.",
    level = RequiresOptIn.Level.ERROR
)
annotation class MinekraftInternal

@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
@RequiresOptIn(
    message = "This is an experimental API. No guarantee that this API stays compatible" +
            "and should not be considered stable for production use. Minor and mayor changes" +
            "can occur at any time",
    level = RequiresOptIn.Level.WARNING
)
annotation class MinekraftExperimental