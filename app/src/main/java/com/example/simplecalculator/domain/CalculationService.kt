package com.example.simplecalculator.domain

import androidx.core.text.isDigitsOnly

object CalculationService {

    fun calculate(formulaInput: List<Char>): String {

        val stack = ArrayDeque<String>()

        for (token in formulaInput) {
            if (token.isDigit() && stack.isNotEmpty() && !stack.last().isDigitsOnly()) {
                val curNumber = token.toString().toFloat()
                val operator = stack.removeLast()
                val preNumber = stack.removeLast().toFloat()
                when (operator) {
                    "+" -> stack.add(preNumber.plus(curNumber).toString())
                    "-" -> stack.add(preNumber.minus(curNumber).toString())
                    "×" -> stack.add(preNumber.times(curNumber).toString())
                    "÷" -> stack.add(preNumber.div(curNumber).toString())
                    else -> {}
                }
            } else {
                stack.add(token.toString())
        }
    }

    return if (stack.isNotEmpty()) stack.first() else ""
}

}