package com.example.simplecalculator.ui

import com.example.simplecalculator.domain.FakeCalculationService
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorViewModelTest {

    private val fakeService = FakeCalculationService()
    private val viewModel = CalculatorViewModel(fakeService)

    @Test
    fun pressNumberUpdatesFormula() {
        viewModel.handleClickEvent("1")
        assertEquals("1", viewModel.formula.value)
    }

}