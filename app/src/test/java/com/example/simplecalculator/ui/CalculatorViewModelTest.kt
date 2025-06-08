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

    @Test
    fun pressEqualsUpdatesResult() {
        viewModel.handleClickEvent("1")
        viewModel.handleClickEvent("+")
        viewModel.handleClickEvent("2")
        viewModel.handleClickEvent("=")
        assertEquals("3.0", viewModel.result.value)
    }

    @Test
    fun pressClearResetsState() {
        viewModel.handleClickEvent("1")
        viewModel.handleClickEvent("+")
        viewModel.handleClickEvent("2")
        viewModel.handleClickEvent("=")
        viewModel.handleClickEvent("C")
        assertEquals("", viewModel.formula.value)
        assertEquals("", viewModel.result.value)
    }

    @Test
    fun pressBackspaceDeletesLastInput() {
        viewModel.handleClickEvent("1")
        viewModel.handleClickEvent("2")
        viewModel.handleClickEvent("<")
        assertEquals("1", viewModel.formula.value)
    }

    @Test
    fun divisionByZeroShowsError() {
        viewModel.handleClickEvent("5")
        viewModel.handleClickEvent("÷")
        viewModel.handleClickEvent("0")
        viewModel.handleClickEvent("=")
        assertEquals("Invalid formula", viewModel.result.value)
    }

}