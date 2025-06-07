package com.example.simplecalculator.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.simplecalculator.domain.CalculationService

class CalculatorViewModel : ViewModel() {

    // State of formula shown in UI
    private val _formula = mutableStateOf("")
    val formula get() = _formula

    // State of calculated result shown in UI
    private val _result = mutableStateOf("")
    val result get() = _result

    // List of token of button input for formula
    private val formulaInput = mutableListOf<Char>()

    // Handle functions after button clicked
    fun handleClickEvent(char: Char) {
        Log.d("VM", "handleClickEvent $char")
        when(char) {
            '=' -> calculateResult()
            'C' -> deleteAllLetter()
            '<' -> deleteLastLetter()
            '_', '.' -> {}
            else -> addLetterToLast(char)
        }
    }

    // Add letter to formula
    private fun addLetterToLast(char: Char) {
        formulaInput.add(char)
        _formula.value = formulaInput.joinToString(" ")
    }

    // Delete last letter of formula
    private fun deleteLastLetter() {
        formulaInput.removeAt(formulaInput.size - 1)
        _formula.value = formulaInput.joinToString(" ")
    }

    // Delete all letter in formula
    private fun deleteAllLetter() {
        formulaInput.clear()
        _formula.value = ""
        _result.value = ""
    }

    // Calculate result of formula
    private fun calculateResult() {
        val calculatedResult = CalculationService.calculate(formulaInput.toList())
        _result.value = calculatedResult
    }

}