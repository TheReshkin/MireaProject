package ru.mirea.tereshkin.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.math.BigDecimal;
import java.math.RoundingMode;

enum Operation {
    PLUS, MINUS, DIVIDE, MULTI
}
public class CalculateFragment extends Fragment {
    private TextView result;
    private String prevNumber = "";
    private String currentNumber = "";
    private Operation prevOperation;
    private boolean operationFinished = false;

    private void performOperation(){
        if (currentNumber.equals("")) currentNumber = "0";
        double resultNumber = 0.0;
        if (!prevNumber.equals("")) {
            if (prevOperation == Operation.PLUS) {
                resultNumber = Double.parseDouble(prevNumber)
                        + Double.parseDouble(currentNumber);

            } else if (prevOperation == Operation.DIVIDE) {
                resultNumber = Double.parseDouble(prevNumber)
                        / Double.parseDouble(currentNumber);

            } else if (prevOperation == Operation.MULTI) {
                resultNumber = Double.parseDouble(prevNumber)
                        * Double.parseDouble(currentNumber);

            } else if (prevOperation == Operation.MINUS) {
                resultNumber = Double.parseDouble(prevNumber)
                        - Double.parseDouble(currentNumber);
            }
            resultNumber = new BigDecimal(resultNumber)
                    .setScale(2, RoundingMode.HALF_EVEN). doubleValue();
            prevNumber = String.valueOf(resultNumber);
            result.setText(prevNumber);
            currentNumber = "";
            operationFinished = true;
        }
    }

    private void onNumberButtonClick(View view){
        if(operationFinished){
            currentNumber = "";
            operationFinished = false;
        }
        Button button = (Button) view;
        if(currentNumber.equals("") && button.getText().toString().equals(".")){
            currentNumber += "0";
        }
        currentNumber += button.getText().toString();
        result.setText(currentNumber);
    }

    private void performAction(Operation operation){
        if(prevNumber.equals("")){
            prevOperation = operation;
            prevNumber = currentNumber;
            currentNumber = "";
            result.setText("");
        } else {
            performOperation();
            prevOperation = operation;
        }
    }

    private void onEqualsButtonClick(View view){
        performOperation();
        prevNumber = "";
        currentNumber = result.getText().toString();
    }

    private void onDeleteButtonClick(View view){
        if (currentNumber.length() > 0){
            currentNumber = currentNumber.substring(0, currentNumber.length()-1);
            result.setText(currentNumber);
        }
    }

    private void onAddButtonClick(View view){
        performAction(Operation.PLUS);
    }

    private void onSubtractButtonClick(View view){
        performAction(Operation.MINUS);
    }

    private void onDivideButtonClick(View view){
        performAction(Operation.DIVIDE);
    }

    private void onMultiplicativeButtonClick(View view){
        performAction(Operation.MULTI);
    }

    private void onClearButtonClick(View view){
        prevNumber = "";
        currentNumber = "";
        result.setText(currentNumber);
        prevOperation = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.l_calculator, container, false);
        result = (TextView) view.findViewById(R.id.textView);
        view.findViewById(R.id.button).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button2).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button3).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button4).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button5).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button6).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button7).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button8).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button10).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button11).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button12).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button9).setOnClickListener(this::onEqualsButtonClick);
        view.findViewById(R.id.button17).setOnClickListener(this::onDeleteButtonClick);
        view.findViewById(R.id.button16).setOnClickListener(this::onDivideButtonClick);
        view.findViewById(R.id.button15).setOnClickListener(this::onMultiplicativeButtonClick);
        view.findViewById(R.id.button14).setOnClickListener(this::onSubtractButtonClick);
        view.findViewById(R.id.button13).setOnClickListener(this::onAddButtonClick);
        view.findViewById(R.id.button18).setOnClickListener(this::onClearButtonClick);

        return view;
    }
}
