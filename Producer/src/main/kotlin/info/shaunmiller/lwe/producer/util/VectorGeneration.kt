package info.shaunmiller.lwe.producer.util

import kotlin.random.Random


class VectorGeneration(val distribution: String = "uniform",
                       val dimension: Int = 4,
                       val q: Int = 17,
                       val seed: Int = 10,
                       val sigma: Double = 1.0) {
    private val random = Random(this.seed)
    private val javaRandom = java.util.Random(this.seed.toLong())

    init {

    }

    fun createArray(): Array<Int>{
        return when(this.distribution){
            "uniform" -> Array<Int>(this.dimension){ this.random.nextInt(0, this.q)}
            "gaussian" -> Array<Int>(this.dimension){(this.javaRandom.nextGaussian()*this.sigma).toInt()}
            else -> throw IllegalArgumentException("distribution must be uniform or gaussian")
        }
    }
}