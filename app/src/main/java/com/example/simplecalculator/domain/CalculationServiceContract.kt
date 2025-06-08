package com.example.simplecalculator.domain

interface CalculationServiceContract {

    fun calculate(formulaInput: List<String>): String

}