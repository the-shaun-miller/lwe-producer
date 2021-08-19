package info.shaunmiller.lwe.producer.controller

import com.fasterxml.jackson.databind.json.JsonMapper
import info.shaunmiller.lwe.producer.model.LWE
import info.shaunmiller.lwe.producer.util.VectorGeneration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random


@RestController
class Controller {


    @GetMapping("/lwe/samples/{m}/dim/{n}/q/{q}/sigma/{sigma}/dist/{dist}")
    fun getRandomInstanceOfLWE(
        @PathVariable("m") m:Int = 5,
        @PathVariable("n") n:Int = 3,
        @PathVariable("q") q:Int = 17,
        @PathVariable("sigma") sigma: String = "1,0",
        @PathVariable("dist") dist: String = "uniform"
    ): String{


        val generator: VectorGeneration = VectorGeneration(
            dimension = n,
            distribution = dist,
            sigma = sigma.replace(",", ".").toDouble(),
            q = q,
            seed = Random.nextInt()
        )

        val gaussianGenerator: VectorGeneration = VectorGeneration(
            dimension = m,
            distribution = dist,
            sigma = sigma.replace(",", ".").toDouble(),
            q = q,
            seed = Random.nextInt()
        )

        val a: Array<Array<Int>> = Array(m){ generator.createArray()}
        val s: Array<Int> = generator.createArray()
        val e: Array<Int> = gaussianGenerator.createArray()


        return LWE(a, s, e).toString()
    }
}