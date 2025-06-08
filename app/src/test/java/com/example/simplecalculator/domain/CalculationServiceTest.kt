package com.example.simplecalculator.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class CalculationServiceTest {

    private val service = CalculationService()

    @Test
    fun multiplyAndDivideEvaluateCorrectly() {
        val input = listOf("6", "÷", "3", "×", "2")
        val result = service.calculate(input)
        assertEquals("4.0", result)
    }

    @Test
    fun additionAndSubtractionEvaluateCorrectly() {
        val input = listOf("1", "+", "2", "-", "3")
        val result = service.calculate(input)
        assertEquals("0.0", result)
    }

    @Test
    fun divisionByZeroThrows() {
        val input = listOf("5", "÷", "0")
        assertThrows(Throwable::class.java) {
            service.calculate(input)
        }
    }

}