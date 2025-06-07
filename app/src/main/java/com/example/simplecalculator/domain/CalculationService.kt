package com.example.simplecalculator.domain

import androidx.core.text.isDigitsOnly

object CalculationService {

    fun calculate(formulaInput: List<String>): String {

        val stack = ArrayDeque<String>()

        // Calculate multiplication and division first
        for (token in formulaInput) {
            if (token.toFloatOrNull() != null && stack.isNotEmpty() && (stack.last() == "×" || stack.last() == "÷")) {
                val operator = stack.removeLast()
                val firstNumber = stack.removeLast().toFloat()
                val secondNumber = token.toFloat()
                when (operator) {
                    "×" -> stack.add((firstNumber * secondNumber).toString())
                    "÷" -> {
                        if (secondNumber == 0f) {
                            throw Throwable("Invalid formula")
                        } else {
                            stack.add((firstNumber / secondNumber).toString())
                        }
                    }
                }
            } else {
                stack.add(token)
            }
        }

        // Then calculate addition and subtraction
        while (stack.size >= 3) {
            val secondNumber = stack.removeLast().toFloat()
            val operator = stack.removeLast()
            val firstNumber = stack.removeLast().toFloat()
            when (operator)  {
                "+" -> stack.add((firstNumber + secondNumber).toString())
                "-" -> stack.add((firstNumber - secondNumber).toString())
            }
        }

        return if (stack.isNotEmpty()) stack.first() else ""
    }

}