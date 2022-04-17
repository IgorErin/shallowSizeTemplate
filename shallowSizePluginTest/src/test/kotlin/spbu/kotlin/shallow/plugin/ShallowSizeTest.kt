package spbu.kotlin.shallow.plugin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

const val DEFAULT_SIZE = 8
@Suppress("MagicNumber")
class AddShallowSizeMethodTest {
    @ParameterizedTest
    @MethodSource("sizeTest")
    fun `test with custom arguments provider`(size: Int, shallowSize: Int, nameOfTest: String) {
        assertEquals(size, shallowSize, nameOfTest)
    }

    companion object {
        private val baseClass = BaseClass("Hello")
        private val internalClass = InternalClass(true)
        private val inheritInterfaces = InheritInterfaces(3)
        private val inheritClass = InheritClass(3)
        private val noBackField = NoBackField('c')
        private val privateFields = PrivateFields(3)
        private val multipleFieldsInConstructor = MultipleFieldsInConstructor(1,
            2, 3, 4)
        private val nullablePrimitives = NullablePrimitives(1f,
            1.0, 'c', true)
        private val javaCharacter = JavaCharacter(Character('3'))
        private val noExplicitType = NoExplicitType(3)
        private val overrideFieldFromClass = OverrideFieldFromClass(4)
        private val overrideFieldFromInterface = OverrideFieldFromInterface(4)

        @JvmStatic
        fun sizeTest(): List<Arguments> {
            return listOf(
                Arguments.of(DEFAULT_SIZE, baseClass.shallowSize(),
                    "test for base class failed"),
                Arguments.of(1, internalClass.shallowSize(),
                    "test for internal class failed"),
                Arguments.of(Int.SIZE_BYTES, inheritInterfaces.shallowSize(),
                    "test for inherit interfaces failed"),
                Arguments.of(Int.SIZE_BYTES, inheritClass.shallowSize(),
                    "test for inherit class failed"),
                Arguments.of(2, noBackField.shallowSize(),
                    "test for class with no back field failed"),
                Arguments.of(Long.SIZE_BYTES + Int.SIZE_BYTES, privateFields.shallowSize(),
                    "test for class with private fields failed"),
                Arguments.of(Byte.SIZE_BYTES + Short.SIZE_BYTES + Int.SIZE_BYTES + Long.SIZE_BYTES,
                    multipleFieldsInConstructor.shallowSize(),
                    "test for class with multiple fields in constructor failed"),
                Arguments.of(4 * DEFAULT_SIZE, nullablePrimitives.shallowSize(),
                    "test for class with nullable primitives failed"),
                Arguments.of(DEFAULT_SIZE, javaCharacter.shallowSize(),
                    "test for class with java character field failed"),
                Arguments.of(Int.SIZE_BYTES + Long.SIZE_BYTES, noExplicitType.shallowSize(),
                    "test for class with no explicit failed"),
                Arguments.of(Int.SIZE_BYTES, overrideFieldFromClass.shallowSize(),
                    "test for override field from class failed"),
                Arguments.of(Int.SIZE_BYTES, overrideFieldFromInterface.shallowSize(),
                    "test for override field from interface failed"),
            )
        }
    }
}
