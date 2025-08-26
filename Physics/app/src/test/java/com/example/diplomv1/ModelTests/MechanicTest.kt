package com.example.diplomv1.ModelTests

import com.example.diplomv1.Model.Mechanic.Companion.calculateA
import com.example.diplomv1.Model.Mechanic.Companion.calculateS
import com.example.diplomv1.Model.Mechanic.Companion.calculateT
import com.example.diplomv1.Model.Mechanic.Companion.calculateU0
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class MechanicTest {

    @Test
    fun calculateSTest(){
       val S = calculateS(5f,3f,7f)
        assertEquals(43.5f, S)
    }

    @Test
    fun calculateATest(){
        val a = calculateA(700f, 12.5f, 15f)
        assertEquals(6.56f,a)
    }

    @Test
    fun calculateU0Test(){
        val U0 = calculateU0(900f,3f,16f)
        assertEquals(276f, U0)
    }

    @Test
    fun calculateTWithA0Test(){
        val t = calculateT(560f,35f,0f)
        assertEquals(16f, t)
    }

    @Test
    fun calculateTWithANot0Test(){
        val t = calculateT(560f,21f,7f)
        assertEquals(10f,t)
    }
    @Test
    fun calculateTWithU0A0Test(){
        val exception = assertThrows<IllegalArgumentException> {
            calculateT(560f, 0f, 0f)
        }
        assertEquals("Бесконечность", exception.message)
    }
}
