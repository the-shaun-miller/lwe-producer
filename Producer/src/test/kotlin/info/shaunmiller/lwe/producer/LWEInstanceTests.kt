package info.shaunmiller.lwe.producer

import info.shaunmiller.lwe.producer.model.LWE
import info.shaunmiller.lwe.producer.util.VectorGeneration
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.*

class LWEInstanceTests {


    @Autowired
    lateinit var mock: Mockito

    @Mock
    private val generator = VectorGeneration()


    @Test
    fun `Throw Appropriate Error if dimension of e doesn't match the # of row of a`(){

        val a = Array(3){Array(3){0}}
        val s = Array(3){0}
        val eFail = Array(2){0}

        assertFailsWith<IllegalArgumentException> { LWE(a, s, eFail) }

    }



    }

    @Test
    fun `create m dimensional b = As+e correctly for the sample`(){



        val lwe = LWE()

        val expectedArray = intArrayOf(4, 5, 6).toTypedArray()

        assertTrue(expectedArray contentEquals lwe.b,
            "Expected: ${expectedArray.asList()} \n" +
                    "Actual: ${lwe.b.asList()}")

    }

    @Test
    fun `create m dimensional b = As+e mod q correctly for the sample`(){

        val a = Array(3){Array(3){1}}//((1,1,1),(1,1,1),(1,1,1))
        val s = intArrayOf(1, 17, 1).toTypedArray()
        val e = Array(3){ i -> i+1}//(1,2,3)

        val lwe = LWE(a, s, e, q = 17)

        val expectedArray = intArrayOf(3, 4, 5).toTypedArray()

        assertTrue(expectedArray contentEquals lwe.b,
            "Expected: ${expectedArray.asList()} \n" +
                    "Actual: ${lwe.b.asList()}")

    }

//    @Test
//    fun `LWE toString will pretty print accordingly`(){
//
//        Mockito.`when`(generator.createArray()).thenReturn(Array(3){ i -> i+1})
//
//
//    }


