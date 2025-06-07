package com.example.simplecalculator.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.simplecalculator.domain.CalculationService

class CalculatorViewModel : ViewModel() {

    // State of formula shown in UI
    private val _formula = mutableStateOf(StringBuilder())
    val formula get() = _formula

    // State of calculated result shown in UI
    private val _result = mutableStateOf("")
    val result get() = _result

    // Handle functions after button clicked
    fun handleClickEvent(text: String) {
        Log.d("VM", "handleClickEvent $text")
        when(text) {
            "=" -> calculateResult()
            "C" -> deleteAllLetter()
            "<" -> deleteLastLetter()
            "_", "." -> {}
            else -> addLetterToLast(text)
        }
    }

    // Add letter to formula
    private fun addLetterToLast(letter: String) {
        Log.d("VM", "addLetterToLast $letter before ${_formula.value}")
        _formula.value.append(letter)
        Log.d("VM", "addLetterToLast $letter after ${_formula.value}")
    }

    // Delete last letter of formula
    private fun deleteLastLetter() {
        _formula.value.deleteAt(_formula.value.length - 1)
    }

    // Delete all letter in formula
    private fun deleteAllLetter() {
        _formula.value.deleteRange(0, _formula.value.length - 1)
        _result.value = ""
    }

    // Calculate result of formula
    private fun calculateResult() {
        val calculatedResult = CalculationService.calculate(formula.value.toString())
        _result.value = calculatedResult
    }

}