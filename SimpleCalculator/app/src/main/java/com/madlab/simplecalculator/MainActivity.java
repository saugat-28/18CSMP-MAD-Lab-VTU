package com.madlab.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.madlab.simplecalculator.databinding.ActivityMainBinding;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding mB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mB = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mB.getRoot());

        mB.button0.setOnClickListener(this);
        mB.button1.setOnClickListener(this);
        mB.button2.setOnClickListener(this);
        mB.button3.setOnClickListener(this);
        mB.button4.setOnClickListener(this);
        mB.button5.setOnClickListener(this);
        mB.button6.setOnClickListener(this);
        mB.button7.setOnClickListener(this);
        mB.button8.setOnClickListener(this);
        mB.button9.setOnClickListener(this);
        mB.buttonPlus.setOnClickListener(this);
        mB.buttonMultiply.setOnClickListener(this);
        mB.buttonDivide.setOnClickListener(this);
        mB.buttonMinus.setOnClickListener(this);
        mB.buttonClear.setOnClickListener(this);
        mB.buttonDot.setOnClickListener(this);
        mB.buttonEquals.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mB.button0)) {
            mB.resultEditText.append("0");
        }
        if (v.equals(mB.button1)) {
            mB.resultEditText.append("1");
        }
        if (v.equals(mB.button2)) {
            mB.resultEditText.append("2");
        }
        if (v.equals(mB.button3)) {
            mB.resultEditText.append("3");
        }
        if (v.equals(mB.button4)) {
            mB.resultEditText.append("4");
        }
        if (v.equals(mB.button5)) {
            mB.resultEditText.append("5");
        }
        if (v.equals(mB.button6)) {
            mB.resultEditText.append("6");
        }
        if (v.equals(mB.button7)) {
            mB.resultEditText.append("7");
        }
        if (v.equals(mB.button8)) {
            mB.resultEditText.append("8");
        }
        if (v.equals(mB.button9)) {
            mB.resultEditText.append("9");
        }
        if (v.equals(mB.buttonPlus)) {
            mB.resultEditText.append("+");
        }
        if (v.equals(mB.buttonMultiply)) {
            mB.resultEditText.append("*");
        }
        if (v.equals(mB.buttonDivide)) {
            mB.resultEditText.append("/");
        }
        if (v.equals(mB.buttonMinus)) {
            mB.resultEditText.append("-");
        }
        if (v.equals(mB.buttonDot)) {
            mB.resultEditText.append(".");
        }
        if (v.equals(mB.buttonClear)) {
            mB.resultEditText.setText("");
        }
        if (v.equals(mB.buttonEquals)) {
            calculate();
        }
    }

    private void calculate() {
        String expression = mB.resultEditText.getText().toString();
        if (expression.contains("/")) {
            String[] operands = expression.split("/");
            if (operands.length == 2) {
                double operand1 = Double.parseDouble(operands[0]);
                double operand2 = Double.parseDouble(operands[1]);
                double result = operand1 / operand2;
                mB.resultEditText.setText(String.valueOf(result));
            } else {
                Toast.makeText(getBaseContext(), "Invalid Expression", Toast.LENGTH_LONG).show();
            }
        } else if (expression.contains("*")) {
            String[] operands = expression.split(Pattern.quote("*"));
            if (operands.length == 2) {
                double operand1 = Double.parseDouble(operands[0]);
                double operand2 = Double.parseDouble(operands[1]);
                double result = operand1 * operand2;
                mB.resultEditText.setText(String.valueOf(result));
            } else {
                Toast.makeText(getBaseContext(), "Invalid Expression", Toast.LENGTH_LONG).show();
            }
        } else if (expression.contains("+")) {
            String[] operands = expression.split(Pattern.quote("+"));
            if (operands.length == 2) {
                double operand1 = Double.parseDouble(operands[0]);
                double operand2 = Double.parseDouble(operands[1]);
                double result = operand1 + operand2;
                mB.resultEditText.setText(String.valueOf(result));
            } else {
                Toast.makeText(getBaseContext(), "Invalid Expression", Toast.LENGTH_LONG).show();
            }
        } else if (expression.contains("-")) {
            String[] operands = expression.split("-");
            if (operands.length == 2) {
                double operand1 = Double.parseDouble(operands[0]);
                double operand2 = Double.parseDouble(operands[1]);
                double result = operand1 - operand2;
                mB.resultEditText.setText(String.valueOf(result));
            } else {
                Toast.makeText(getBaseContext(), "Invalid Expression", Toast.LENGTH_LONG).show();
            }
        }
    }
}