package com.example.dvmcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var strNumber = java.lang.StringBuilder()
    private lateinit var displayArea: TextView
    private lateinit var numberButtons: Array<Button>
    private lateinit var operatorButtons : Array<Button>
    private var operator : Operator = Operator.NONE
    private var isOperatorClicked: Boolean = false
    private var operand1: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        initializeComponents()
    }

    private fun initializeComponents() {
        displayArea = findViewById(R.id.displayArea)
        val buttonEquals: Button = findViewById(R.id.equalto)
        val buttonClear: Button = findViewById(R.id.allclear)
        val buttonDelete: Button = findViewById(R.id.delete)
        val buttonDecimal: Button = findViewById(R.id.decimal)
        val button9: Button = findViewById(R.id.nine)
        val button8: Button = findViewById(R.id.eight)
        val button7: Button = findViewById(R.id.seven)
        val button6: Button = findViewById(R.id.six)
        val button5: Button = findViewById(R.id.five)
        val button4: Button = findViewById(R.id.four)
        val button3: Button = findViewById(R.id.three)
        val button2: Button = findViewById(R.id.two)
        val button1: Button = findViewById(R.id.one)
        val button0: Button = findViewById(R.id.zero)
        val buttonAdd: Button = findViewById(R.id.add)
        val buttonSubtract: Button = findViewById(R.id.subtract)
        val buttonMultiply: Button = findViewById(R.id.multiply)
        val buttonDivide: Button = findViewById(R.id.divide)
        numberButtons = arrayOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        for (i in numberButtons) {
            i.setOnClickListener { numberButtonClicked(i) }
        }
        operatorButtons = arrayOf(buttonAdd, buttonSubtract, buttonMultiply, buttonDivide)
        for (i in operatorButtons) {
            i.setOnClickListener { operatorButtonClicked(i) }
        }

        buttonEquals.setOnClickListener { buttonEqualClick() }
        buttonClear.setOnClickListener { buttonClearClick() }
        buttonDelete.setOnClickListener { buttonDeleteClick() }
        buttonDecimal.setOnClickListener { buttonDecimalClick() }

    }

    private fun buttonDecimalClick() {
        strNumber.append(".")
        updateDisplay()
    }

    private fun buttonDeleteClick() {
        strNumber.dropLast(1)
        updateDisplay()
    }

    private fun buttonClearClick() {
        strNumber.clear()
    }

    private fun buttonEqualClick() {
        val operand2 = strNumber.toString().toDouble()
        val result: Double = when(operator) {
            Operator.ADD -> operand1 + operand2
            Operator.SUB -> operand1 - operand2
            Operator.MUL -> operand1 * operand2
            Operator.DIV -> operand1 / operand2
            else -> 0.0
        }
        strNumber.clear()
        strNumber.append(result.toString())
        updateDisplay()
        isOperatorClicked = true
    }

    @SuppressLint("SetTextI18n")
    private fun updateDisplay() {
        try {
            val textValue = strNumber.toString().toDouble()
            displayArea.text = textValue.toString()
        }
        catch (e:java.lang.IllegalArgumentException) {
            strNumber.clear()
            displayArea.text = "MAX INTEGER LIMIT REACHED"
        }

    }

    private fun operatorButtonClicked(btn: Button) {
        operator = when (btn.text) {
            "+" -> Operator.ADD
            "-" -> Operator.SUB
            "X" -> Operator.MUL
            "/" -> Operator.DIV
            else -> Operator.NONE
        }
        isOperatorClicked = true
    }

    private fun numberButtonClicked(btn: Button) {
        if (isOperatorClicked) {
            operand1 = strNumber.toString().toDouble()
            strNumber.clear()
            isOperatorClicked = false
        }
        strNumber.append(btn.text)
        updateDisplay()
    }
}

enum class Operator {ADD, SUB, MUL, DIV, NONE}