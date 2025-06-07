package com.example.simplecalculator.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculatorScreen(
    vm: CalculatorViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Name
        Text(
            text = "Calculator",
            fontSize = 30.sp
        )
        // Current Formula
        Text(
            text = "Formula:\n${vm.formula.value}"
        )
        // Result
        Text(
            text = "Result:\n${vm.result.value}"
        )
        // Buttons
        Buttons(
            onClickButton = { char ->
                Log.d("UI", "onClick $char")
                vm.handleClickEvent(char)
            }
        )
    }
}

@Composable
fun Buttons(
    onClickButton: (Char) -> Unit
) {
    /**
     * | C | _ | < | + |
     * | 7 | 8 | 9 | - |
     * | 4 | 5 | 6 | × |
     * | 1 | 2 | 3 | ÷ |
     * | _ | 0 | . | = |
     * _: add later
     * */
    val buttonTexts: List<List<Char>> = listOf(
        listOf('C', '_', '<', '+'),
        listOf('7', '8', '9', '-'),
        listOf('4', '5', '6', '×'),
        listOf('1', '2', '3', '÷'),
        listOf('_', '0', '.', '=')
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttonTexts.forEach { rows ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                rows.forEach { char ->
                    Button(
                        onClick = {
                            Log.d("UI", "Click $char")
                            onClickButton(char)
                        }
                    ) {
                        Text(text = char.toString())
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun CalculatorScreenPreView() {
    CalculatorScreen(vm = viewModel())
}