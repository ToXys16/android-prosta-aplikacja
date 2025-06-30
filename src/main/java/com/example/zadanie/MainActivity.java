package com.example.zadanie;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private Button button;
    private SeekBar seekBar;
    private Spinner spinner;
    private EditText editText;
    private ConstraintLayout layout;
    private TextView text1,text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner);
        button=findViewById(R.id.button);
        seekBar=findViewById(R.id.seekBar);
        editText=findViewById(R.id.editTextText);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        layout=findViewById(R.id.main);
        editText.addTextChangedListener(new TextWatcher() {
            private boolean czyformatowane = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (czyformatowane) return;

                czyformatowane = true;

                String input = s.toString();
                String[] words = input.split("\\s+");
                StringBuilder formatted = new StringBuilder();

                for (String word : words) {
                    if (!word.isEmpty()) {
                        formatted.append(Character.toUpperCase(word.charAt(0)))
                                .append(word.substring(1).toLowerCase())
                                .append(" ");
                    }
                }

                String result = formatted.toString().trim();

                if (!input.equals(result)) {
                    int cursorPos = editText.getSelectionStart();
                    editText.setText(result);
                    editText.setSelection(Math.min(cursorPos, result.length()));
                }

                czyformatowane = false;
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean tak) {
                text1.setTextSize(progress);
                text2.setTextSize(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        button.setOnClickListener(view -> {
            String color=spinner.getSelectedItem().toString();
            switch (color) {
                case "#FF000000":
                    layout.setBackgroundColor(Color.parseColor("#FF000000"));
                    break;
                case "#FFFFFFFF":
                    layout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    break;
                case "#FF0000":
                    layout.setBackgroundColor(Color.parseColor("#FF0000"));
                    break;
                default:
                    layout.setBackgroundColor(Color.WHITE);
                    break;
            }
        });

    }
}