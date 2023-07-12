package com.hfad.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView countNumberText = findViewById(R.id.countNumber);
        TextView fromNumber = findViewById(R.id.fromNumber);
        TextView toNumber = findViewById(R.id.toNumber);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                countNumberText.setText(String.valueOf(progress));
            //    seekBar.setMax(Integer.parseInt(String.valueOf(toNumber.getText())) - Integer.parseInt(String.valueOf(fromNumber.getText())) + 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekBar.getProgress() < 1)
                    seekBar.setProgress(1);
            }
        });
    }

    public void chooseRangeNumbers(View view) {
        TextView fromNumber = findViewById(R.id.fromNumber);
        TextView toNumber = findViewById(R.id.toNumber);
        TextView outputNumber = findViewById(R.id.outputNumbers);
        TextView countNumberText = findViewById(R.id.countNumber);
        TextView errorText = findViewById(R.id.textinput_error);
        int outputValue = 0;

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

                if (!numbers.contains(outputValue)) {
                    numbers.add(outputValue);
                }
            }

            outputNumber.setText(String.valueOf(numbers));
        }



    }
}