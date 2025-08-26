package com.example.diplomv1.Model

import kotlin.math.sqrt

class Mechanic {
    // Расчет времени при ускоренном движении
    companion object {
        fun calculateT(S: Float, U0: Float, a: Float): Float {
            return if (a > 0) {
                // Движение с ускорением: S = v₀t + at²/2
                val discriminant = U0 * U0 + 2 * a * S
                (-U0 + sqrt(discriminant)) / a
            } else {
                // Равномерное движение: S = vt
                require(U0 != 0f) { "Бесконечность" }
                S / U0
            }
        }

        // Расчет скорости при ускоренном движении
        fun calculateU0(S: Float, t: Float, a: Float): Float {
            return (2 * S - a * t * t) / (2 * t)
        }

        //Расчет ускорения
        fun calculateA(S: Float, t: Float, U0: Float): Float {
            return (2 * S - 2 * U0 * t) / (t * t)
        }

        //Расчет пути при ускоренном движении
        fun calculateS(a: Float, t: Float, U0: Float): Float {
            return U0 * t + a * t * t / 2
        }
    }
}