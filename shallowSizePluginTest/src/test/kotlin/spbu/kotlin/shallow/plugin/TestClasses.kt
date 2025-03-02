package spbu.kotlin.shallow.plugin

import java.io.Serializable

data class BaseClass(val stringVal: String)

internal data class InternalClass(var booleanVal: Boolean)

@Suppress("SerialVersionUIDInSerializableClass")
data class InheritInterfaces(val x: Int) : Serializable, Runnable {
    override fun run() = TODO()
}

open class Base(open val openIntVal: Int)

data class InheritClass(val intVal: Int) : Base(intVal)

data class NoBackField(val charVal: Char = 'a') {
    val intValWithGet: Int
        get() = 0
}

@Suppress("UnusedPrivateMember")
data class PrivateFields(val longVal: Long) {
    private val intValWithoutGet: Int = 0
}

data class MultipleFieldsInConstructor(
    val byteVal: Byte,
    val shortVal: Short,
    val intVal: Int,
    val longVal: Long
)

data class NullablePrimitives(
    val floatNullableVal: Float?,
    val doubleNullableVal: Double?,
    val charNullableVal: Char?,
    val booleanNullableVal: Boolean?
)

data class JavaCharacter(val characterVal: Character)

@Suppress("MagicNumber")
data class NoExplicitType(val intVal: Int) {
    val longValWithoutGet = 10L
}
@Suppress("SerialVersionUIDInSerializableClass")
interface BaseInterface {
    val intVal: Int
}

@Suppress("MagicNumber")
data class OverrideFieldFromClass(override val openIntVal: Int) : Base(3)

data class OverrideFieldFromInterface(override val intVal: Int) : BaseInterface
