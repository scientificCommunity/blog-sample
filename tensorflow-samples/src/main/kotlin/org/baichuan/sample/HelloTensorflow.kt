package org.baichuan.sample

import org.tensorflow.ConcreteFunction
import org.tensorflow.Signature
import org.tensorflow.TensorFlow
import org.tensorflow.op.Ops
import org.tensorflow.types.TInt32


class HelloTensorflow {

    companion object {
        @JvmStatic
        fun dbl(tf: Ops): Signature? {
            val x = tf.placeholder(TInt32::class.java)
            val dblX = tf.math.add(x, x)
            return Signature.builder().input("x", x).output("dbl", dblX).build()
        }
    }
}

fun main(args: Array<String>) {
    println("Hello TensorFlow " + TensorFlow.version())
    ConcreteFunction.create(HelloTensorflow::dbl).use { dbl ->
        TInt32.scalarOf(10).use { x ->
            dbl.call(x).use { dblX ->
                println(
                    x.getInt().toString() + " doubled is " + (dblX as TInt32).getInt()
                )
            }
        }
    }
}