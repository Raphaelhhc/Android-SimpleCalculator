# Simple Calculator App

## App design

### Requirements

#### Functional requirements

- **Basic scope**
    - Allow user input of a simple math formula
    - Compute the result based on the provided formula
    - Supported mathematical operations:
        - Addition
        - Subtraction
        - Multiplication
        - Division
    - Follow standard operator precedence: multiplication/division first, addition/subtraction second
    - Properly handle edge cases (e.g., division by zero by showing an error message)
- **Out of scope or later**
    - Parentheses to explicitly set operation precedence
    - Negative number handling as inputs
    - Advanced mathematical operations e.g. modulo, square, root, etc.

#### Non-functional requirements

- Correctness: Mathematical calculations must yield accurate results
- Low latency: The calculated results must appear rapidly, without noticeable delay

### App architecture

#### Diagram
```UI <---> ViewModel <---> Calculation Service```

#### Task of each module

- **UI**
    - Renders the current formula and result
    - Exposes buttons
        - Number: 0~9
        - Operator: "+", "-", "×", "÷", "="
        - Backspace: "<-"
        - Clear: "C"
- **ViewModel**
    - Hold current state
        - Current formula
        - Current displayed result
        - Any error state (e.g., “Division by zero”)
    - Responds to button clicks
        - Updates the formula string in state
        - On “=”, invokes the Calculation Service
    - Exposes state to UI
- **Calculation Service**
    - Parse input of formula into tokens (numbers and operators)
    - Evaluates respecting operator precedence (×/÷ before +/–)
    - Returns either a result or throws/returns an error

#### Data schema

- Formula: String(ViewModel)
- Calculated result: Float(Calculation Service)/String(ViewModel)

### Main framework/libraries

- **Jetpack Compose (UI)**
    - Text composable for the formula
    - Text composable for the result
    - A Grid or Column/Row arrangement of Button for digits/operators
- **ViewModel**
    - Contains the state
        - formulaState: MutableState<String>
        - resultState: MutableState<String>
    - Exposes formulaState and resultState

### Edge-Cases to Consider

- **Error Handling**
    - The Calculation Service should detect division by zero and return an error
    - ViewModel can capture that error and set resultState = "Error: Division by zero"
- **Intermediate Input Validation**
    - Prevent two operators in a row (e.g. "12++3")
    - If last character of formula is already an operator replace it
- **Formatting**
    - Trim trailing “.0” (e.g. display 5 instead of 5.0)
    - Limit decimal places (e.g. show only 2 decimals)