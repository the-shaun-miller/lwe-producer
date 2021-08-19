package info.shaunmiller.lwe.producer

import info.shaunmiller.lwe.producer.util.VectorGeneration
import org.junit.jupiter.api.Test
import kotlin.math.absoluteValue
import kotlin.test.*

class VectorGenerationTests {

    @Test
    fun `pull from random number generator for uniform distribution`(){

        val dimension = 4
        val q = 17

        val generator = VectorGeneration(dimension = dimension, q = q, seed = 10)
        val actual = generator.createArray()
        val actual2 = generator.createArray()

        assertTrue(actual::class == Array<Int>::class,
        "Expected: ${Array<Int>::class}" +
                "\n" +
                "Actual: ${actual::class}" +
                "\n" +
                "Be sure entries are integers!")

        val zeros = Array<Int>(dimension){0}

        assertTrue(!(zeros contentEquals actual), """
            First Vector = ${actual.toList()}
            Second Vector = ${actual2.toList()}
        """.trimIndent())
        for(entry in actual) assertTrue(entry in 0 until q, "Entry: $entry")
        for(entry in actual2) assertTrue(entry in 0 until q, "Entry: $entry")
        assertFalse(actual contentEquals actual2, "Matching vectors. Try changing seed value.")

    }

    @Test
    fun `generate vector with entries from gaussian distribution`(){
        val dimension = 30
        val q = 17

        val generator = VectorGeneration(distribution = "gaussian", dimension = dimension, q = q, seed = 10)
        val actual = generator.createArray()
        val actual2 = generator.createArray()

        assertTrue(actual::class == Array<Int>::class,
            "Expected: ${Array<Int>::class}" +
                    "\n" +
                    "Actual: ${actual::class}" +
                    "\n" +
                    "Be sure entries are integers!")

        val zeros = Array<Int>(dimension){0}

        assertTrue(!(zeros contentEquals actual), """
            Could be zero... Try another seed?
            First Vector = ${actual.toList()}
            Second Vector = ${actual2.toList()}
        """.trimIndent())
        for(entry in actual) assertTrue(entry.absoluteValue in 0 until 4, "Entry: $entry")
        for(entry in actual2) assertTrue(entry.absoluteValue in 0 until 4, "Entry: $entry")
        assertFalse(actual contentEquals actual2, "Matching vectors. Try changing seed value.")
    }

}