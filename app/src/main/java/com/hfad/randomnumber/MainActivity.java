package com.hfad.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
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
                if(seekBar.getProgress() < 1) {
                    seekBar.setProgress(1);
                }
            }
        });
    }

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
        TextView errorText = findViewById(R.id.textinput_error);
        CheckBox checkBoxUniquie = findViewById(R.id.checkBoxUniquie);
        Spinner sortValues = findViewById(R.id.sortValue);
        SeekBar seekBar = findViewById(R.id.seekBar);

        String sortValuesType = String.valueOf(sortValues.getSelectedItemId());
        int outputValue;


        if (String.valueOf(fromNumber.getText()).isEmpty() || String.valueOf(toNumber.getText()).isEmpty()) {
            errorText.setText(getResources().getString(R.string.error_empty));
        } else if (Integer.parseInt(String.valueOf(toNumber.getText())) < Integer.parseInt(String.valueOf(fromNumber.getText()))) {
            errorText.setText(getResources().getString(R.string.error_toNumber));
        } else {
            errorText.setText("");

            List<Integer> numbers = new ArrayList<Integer>();

            while (numbers.size() < Integer.parseInt(String.valueOf(seekBar.getProgress()))) {
                outputValue = (int) (Math.random()*(Integer.parseInt(String.valueOf(toNumber.getText())) - Integer.parseInt(String.valueOf(fromNumber.getText())) + 1)
                        + Integer.parseInt(String.valueOf(fromNumber.getText())));

                if (checkBoxUniquie.isChecked()) {
                    if (Integer.parseInt(String.valueOf(toNumber.getText())) - Integer.parseInt(String.valueOf(fromNumber.getText())) + 1 < seekBar.getMax()) {
                        seekBar.setMax(Integer.parseInt(String.valueOf(toNumber.getText())) - Integer.parseInt(String.valueOf(fromNumber.getText())) + 1);
                    }
                    if (!numbers.contains(outputValue)) {
                        numbers.add(outputValue);
                    }
                } else {
                    numbers.add(outputValue);
                }
            }
            outputNumber.setText(String.valueOf(setSortBy(Integer.parseInt(sortValuesType), numbers)).replaceAll("[\\]\\[]", ""));
            Button sendValues = findViewById(R.id.sendValue);
            sendValues.setVisibility(View.VISIBLE);
        }
    }

    public void sendValues(View view) {
        TextView outputNumber = findViewById(R.id.outputNumbers);
        String outputValues = outputNumber.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, outputValues);
        String chooserTitle = getString(R.string.chooser);
        Intent chosenIntent = Intent.createChooser(intent, chooserTitle);
        startActivity(chosenIntent);
    }
}