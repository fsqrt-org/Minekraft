package org.fsqrt.minekraft.enum

/**
 * An [Enum] That links to the next and the previous entry. Useful for situations in which enums need to rotate
 */
interface BiLinkedEnum <T: Enum<T>> : LinkedEnum<T> {
    val previous: T
}