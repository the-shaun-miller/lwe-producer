package info.shaunmiller.lwe.producer.model

import info.shaunmiller.lwe.producer.util.VectorGeneration
import java.awt.Dimension
import java.math.BigInteger
import java.util.*
import kotlin.random.Random

class LWE(val a: Array<Array<Int>> = Array(3){intArrayOf(0,0,0).toTypedArray()},
          val s: Array<Int> = intArrayOf(0,0,0).toTypedArray(),
          val e: Array<Int> = intArrayOf(0,0,0).toTypedArray(),
          var q:Int = 17) {

    var b: Array<Int> = produceSample(this.a, this.s, this.e, q)
    val m: Int = this.a.size
    val n: Int = this.s.size

    /*
    a is an m (rows) by n (columns)
       m are the number of samples of this particular LWE instance
        n is the dimension of the secret

    s is the n dimensional secret vector of the lwe instance
        Treated as a column vector
        for simplicity, default entries are distributed uniformly at random
        In practice this may have entries drawn from the same discrete gaussian distribution as the noise vector entries

    e is the m dimensional noise vector applied to the instance
        values drawn from discrete gaussian distribution

    q is the characteristic of the LWE instance.
        Operations will always be done modulo q
        We of q being prime though this is not always the case
     */

    init {

        if(this.a[0].size != this.n)throw IllegalArgumentException("""
            s: $s
            A: $a
            e: $e
            Dimension of s (${this.n}) does not equal the number of columns in a (${this.a[0].size})
        """.trimIndent()
        )

        if(this.e.size != this.m)throw IllegalArgumentException("""
            s: $s
            A: $a
            e: $e
            Dimension of e (${this.e.size}) does not equal the number of rows in a (${this.m})
        """.trimIndent()
        )

        if(!BigInteger.valueOf(q.toLong()).isProbablePrime(Integer.MAX_VALUE)){
            throw IllegalArgumentException("q (${q}) must be prime.")
        }

        if(q<0){q = q*-1}

    }


    private fun produceSample(a: Array<Array<Int>>, s: Array<Int>, e: Array<Int>, q:Int = 17): Array<Int>{
        var b = Array(e.size){0}

        for(i in b.indices){
            for(j in a[i].indices){
                b[i] += a[i][j]*s[j]
            }
            b[i] += e[i]
            b[i] = b[i]%q
        }

        return b
    }

    init {

    }

    override fun toString(): String {


        return "{\"public\": {\"A\": ${a.contentDeepToString()},\"b\": ${b.toList()},\"q\": ${q} }, \"private\": {\"e\": ${e.toList()},\"s\": ${s.toList()}} }"
    }
}