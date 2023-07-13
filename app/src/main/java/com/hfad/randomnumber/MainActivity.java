package com.hfad.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView countNumberText = findViewById(R.id.countNumber);
        EditText fromNumber = findViewById(R.id.fromNumber);
        EditText toNumber = findViewById(R.id.toNumber);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countNumberText.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (String.valueOf(fromNumber.getText()).isEmpty() || String.valueOf(toNumber.getText()).isEmpty()) {
                    seekBar.setMax(1);
                } else {
                    seekBar.setMax(Integer.parseInt(String.valueOf(toNumber.getText())) - Integer.parseInt(String.valueOf(fromNumber.getText())) + 1);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekBar.getProgress() < 1)
                    seekBar.setProgress(1);
            }
        });
    }

//    class CheckMaxValue implements TextWatcher {
//        TextView errorText = findViewById(R.id.textinput_error);
//        EditText fromNumber = findViewById(R.id.fromNumber);
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            int num;
//            while (true) {
//                try {
//                    num = Integer.parseInt(String.valueOf(s));
//                    break;
//                } catch (Exception ex) {
//                    fromNumber.setText(0);
//                  //  errorText.setText(getResources().getString(R.string.error_max));
//                    errorText.setText(String.valueOf(ex));
//                    //
//                }
//            }
//            errorText.setText(String.valueOf(num));
//        }
//    };

    public List<Integer> setSortBy(int sort, List<Integer> values) {
        List<Integer> numbers = values;
        switch (sort) {
            case (0):
            {
                break;
            }
            case (1):
            {
                Collections.sort(numbers);
                break;
            }
            case (2):
            {
                Collections.sort(numbers, Collections.reverseOrder());
                break;
            }
        }
        return numbers;
    }
    public void chooseRangeNumbers(View view) {
        TextView fromNumber = findViewById(R.id.fromNumber);
        TextView toNumber = findViewById(R.id.toNumber);
        TextView outputNumber = findViewById(R.id.outputNumbers);
        TextView countNumberText = findViewById(R.id.countNumber);
        TextView errorText = findViewById(R.id.textinput_error);
        CheckBox checkBoxUniquie = findViewById(R.id.checkBoxUniquie);
        Spinner sortValues = findViewById(R.id.sortValue);

        String sortValuesType = String.valueOf(sortValues.getSelectedItemId());
        int outputValue;

        if (String.valueOf(fromNumber.getText()).isEmpty() || String.valueOf(toNumber.getText()).isEmpty()) {
            errorText.setText(getResources().getString(R.string.error_empty));
        } else if (Integer.parseInt(String.valueOf(toNumber.getText())) < Integer.parseInt(String.valueOf(fromNumber.getText()))) {
            errorText.setText(getResources().getString(R.string.error_toNumber));
        } else {
            errorText.setText("");
            List<Integer> numbers = new ArrayList<Integer>();

            while (numbers.size() < Integer.parseInt(String.valueOf(countNumberText.getText()))) {
                outputValue = (int) (Math.random()*(Integer.parseInt(String.valueOf(toNumber.getText())) - Integer.parseInt(String.valueOf(fromNumber.getText())) + 1)
                        + Integer.parseInt(String.valueOf(fromNumber.getText())));
                
                if (checkBoxUniquie.isChecked()) {
                    if (!numbers.contains(outputValue)) {
                        numbers.add(outputValue);
                    }
                } else {
                    numbers.add(outputValue);
                }


            }

            outputNumber.setText(String.valueOf(setSortBy(Integer.parseInt(sortValuesType), numbers)));
        }



    }
}