package com.example.dvmcalculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private var strNumber = java.lang.StringBuilder()
    private lateinit var displayArea: TextView
    private lateinit var numberButtons: Array<Button>
    private lateinit var button9: Button
    private lateinit var button8: Button
    private lateinit var button7: Button
    private lateinit var button6: Button
    private lateinit var button5: Button
    private lateinit var button4: Button
    private lateinit var button3: Button
    private lateinit var button2: Button
    private lateinit var button1: Button
    private lateinit var button0: Button
    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonMul: Button
    private lateinit var buttonDiv: Button
    private lateinit var operatorButtons: Array<Button>
    private lateinit var buttonEquals: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonDel: Button
    private lateinit var buttonDecimal: Button
    private var operator : Operator = Operator.NONE
    private var isOperatorClicked: Boolean = false
    private var operand1: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeComponents()
    }
    private fun initializeComponents() {
        displayArea = findViewById(R.id.displayArea)
        button9 = findViewById(R.id.nine)
        button8 = findViewById(R.id.eight)
        button7 = findViewById(R.id.seven)
        button6 = findViewById(R.id.six)
        button5 = findViewById(R.id.five)
        button4 = findViewById(R.id.four)
        button3 = findViewById(R.id.three)

        button2 = findViewById(R.id.two)
        button1 = findViewById(R.id.one)
        button0 = findViewById(R.id.zero)
        buttonAdd = findViewById(R.id.add)
        buttonSub = findViewById(R.id.subtract)
        buttonMul = findViewById(R.id.multiply)
        buttonDiv = findViewById(R.id.divide)
        numberButtons = arrayOf(button0, button1, button2, button3,

            button4, button5, button6, button7, button8, button9)

        for (i in numberButtons) {
            i.setOnClickListener { numberButtonClicked(i) }

        }
        operatorButtons = arrayOf(buttonAdd, buttonSub, buttonMul,

            buttonDiv)

        for (i in operatorButtons) {
            i.setOnClickListener { operatorButtonClicked(i) }

        }
        buttonEquals = findViewById(R.id.equalto)
        buttonEquals.setOnClickListener { buttonEqual() }
        buttonClear = findViewById(R.id.allclear)
        buttonClear.setOnClickListener { buttonClearClicked() }
        buttonDel = findViewById(R.id.delete)
        buttonDel.setOnClickListener { buttonDelClicked() }

    }

    private fun buttonDelClicked() {
        lateinit var delstrNumber: CharSequence
        if (strNumber.length > 0) {
            delstrNumber = strNumber.dropLast(1)
        }
        strNumber.clear()
        strNumber.append(delstrNumber)
        displayArea.text = strNumber
    }
    private fun buttonClearClicked() {
        strNumber.clear()
        displayArea.text = strNumber
        isOperatorClicked = false
        operator = Operator.NONE

    }
    private fun buttonEqual() {
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
        displayArea.text = strNumber
        isOperatorClicked = true
    }
    private fun operatorButtonClicked(i: Button) {
        operator = when(i.text) {
            "+" -> Operator.ADD
            "-" -> Operator.SUB
            "X" -> Operator.MUL
            "/" -> Operator.DIV
            else -> Operator.NONE
        }
        isOperatorClicked = true
    }
    enum class Operator {ADD, SUB, MUL, DIV, NONE}
    private fun numberButtonClicked(i: Button) {
        if (isOperatorClicked) {
            operand1 = strNumber.toString().toDouble()
            strNumber.clear()
            isOperatorClicked = false
        }
        strNumber.append(i.text)
        displayArea.text = strNumber
    }

}