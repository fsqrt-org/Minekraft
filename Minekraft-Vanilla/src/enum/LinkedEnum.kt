package org.fsqrt.minekraft.enum

/**
 * An [Enum] That links to the next entry. Useful for situations in which enums need to rotate
 */
interface LinkedEnum<T: Enum<T>> {
    val next: T?
}