package com.example.simplecalculator.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.simplecalculator.domain.CalculationServiceContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculationService: CalculationServiceContract
) : ViewModel() {

    // State of formula shown in UI
    private val _formula = mutableStateOf("")
    val formula get() = _formula

    // State of calculated result shown in UI
    private val _result = mutableStateOf("")
    val result get() = _result

    // List of token of button input for formula
    private val formulaInput = mutableListOf<String>()

    // Handle functions after button clicked
    fun handleClickEvent(str: String) {
        Log.d("VM", "handleClickEvent $str")
        when (str) {
            "=" -> calculateResult()
            "C" -> deleteAllLetter()
            "<" -> deleteLastLetter()
            "_", "." -> {}
            else -> addLetterToLast(str)
        }
    }

    // Add letter to formula
    private fun addLetterToLast(str: String) {

        var input = str

        // Prevent two operators in a row
        if (formulaInput.isNotEmpty()
            && input.toFloatOrNull() == null
            && formulaInput.last().toFloatOrNull() == null
        ) {
            formulaInput[formulaInput.size - 1] = input
            return
        }

        // Combine numbers if two continuous number input
        if (formulaInput.isNotEmpty()
            && input.toFloatOrNull() != null
            && formulaInput.last().toFloatOrNull() != null
        ) {
            val pre = formulaInput.removeAt(formulaInput.size - 1)
            input = pre + str
        }

        formulaInput.add(input)
        _formula.value = formulaInput.joinToString(" ")
    }

    // Delete last letter of formula
    private fun deleteLastLetter() {
        if (formulaInput.isEmpty()) return
        val lastToken = formulaInput.removeAt(formulaInput.size - 1)
        if (lastToken.length > 1) {
            formulaInput.add(lastToken.dropLast(1))
        }
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
        val calculatedResult = try {
            calculationService.calculate(formulaInput.toList())
        } catch (e: Throwable) {
            e.message ?: "Wrong"
        }
        _result.value = calculatedResult
    }

}