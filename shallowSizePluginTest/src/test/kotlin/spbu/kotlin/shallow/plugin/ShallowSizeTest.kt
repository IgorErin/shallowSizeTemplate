package spbu.kotlin.shallow.plugin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

const val DEFAULT_SIZE = 8
const val BOOLEAN_SIZE = 1

@Suppress("MagicNumber")
class AddShallowSizeMethodTest {
    @ParameterizedTest
    @MethodSource("sizeTest")
    fun `test with custom arguments provider`(expectedSize: Int, actualSize: Int, errorMessage: String) {
        assertEquals(expectedSize, actualSize, errorMessage)
    }

    companion object {
        @JvmStatic
        fun sizeTest() = listOf(
                Arguments.of(DEFAULT_SIZE, BaseClass("Hello").shallowSize(),
                    "test for base class failed"),
                Arguments.of(BOOLEAN_SIZE, InternalClass(true).shallowSize(),
                    "test for internal class failed"),
                Arguments.of(Int.SIZE_BYTES, InheritInterfaces(3).shallowSize(),
                    "test for inherit interfaces failed"),
                Arguments.of(Int.SIZE_BYTES, InheritClass(3).shallowSize(),
                    "test for inherit class failed"),
                Arguments.of(Char.SIZE_BYTES, NoBackField('c').shallowSize(),
                    "test for class with no back field failed"),
                Arguments.of(Long.SIZE_BYTES + Int.SIZE_BYTES, PrivateFields(3).shallowSize(),
                    "test for class with private fields failed"),
                Arguments.of(Byte.SIZE_BYTES + Short.SIZE_BYTES + Int.SIZE_BYTES + Long.SIZE_BYTES,
                    MultipleFieldsInConstructor(1, 2, 3, 4).shallowSize(),
                    "test for class with multiple fields in constructor failed"),
                Arguments.of(4 * DEFAULT_SIZE,
                    NullablePrimitives(
                        1f,
                        1.0,
                        'c',
                        true).shallowSize(),
                    "test for class with nullable primitives failed"),
                Arguments.of(DEFAULT_SIZE, JavaCharacter(Character('3')).shallowSize(),
                    "test for class with java character field failed"),
                Arguments.of(Int.SIZE_BYTES + Long.SIZE_BYTES, NoExplicitType(3).shallowSize(),
                    "test for class with no explicit failed"),
                Arguments.of(Int.SIZE_BYTES, OverrideFieldFromClass(4).shallowSize(),
                    "test for override field from class failed"),
                Arguments.of(Int.SIZE_BYTES, OverrideFieldFromInterface(4).shallowSize(),
                    "test for override field from interface failed"),
            )

    }
}
