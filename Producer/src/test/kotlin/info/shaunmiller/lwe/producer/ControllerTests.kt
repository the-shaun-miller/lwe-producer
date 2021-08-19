package info.shaunmiller.lwe.producer
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonValue
import info.shaunmiller.lwe.producer.model.LWE
import info.shaunmiller.lwe.producer.util.VectorGeneration
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.test.assertEquals

class ControllerTests {

//    @Autowired
//    lateinit var mockMvc: MockMvc


    @Test
    fun `initializing lwe with no params should return random instance`(){

        val all1s = Array(4){i -> i+1}

        val lwe = mockk<LWE>()
        every {lwe.s} returns all1s
        every { lwe.a } returns Array(4){all1s}
        every {lwe.e} returns intArrayOf(0,0,0,1).toTypedArray()
        //every {lwe.b} returns




        //assertEquals(Array(4){i -> i+1}.asList(), lwe.s.asList())


//        mockMvc.perform(MockMvcRequestBuilders.post("/lwe/random").content(LWE().toString()))
//            .andExpect(MockMvcResultMatchers.status().isAccepted)
//            .andExpect(MockMvcResultMatchers.jsonPath("b").isArray)
    }

}