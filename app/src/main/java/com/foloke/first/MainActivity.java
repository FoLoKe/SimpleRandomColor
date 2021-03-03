package com.foloke.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random colorRandom;
    private TextView textView;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        constraintLayout = findViewById(R.id.layout);

        colorRandom = new Random();
        updateColor();

        constraintLayout.setOnClickListener(event -> {
            //THIS IS METHOD REFERENCE
            //Usually you would use "new Runnable() { run() {...CODE...} }"
            //or shortage as "lambda function" as "() -> {...CODE...} like method above"
            //but since this calls only one method within this class we can use this::method
            runOnUiThread(this::updateColor);
        });
    }

    private void updateColor() {
        int newColor = getRandomColor();
        textView.setText(getString(R.string.hello_text, colorString(newColor)));
        updateTextColor(newColor);
        constraintLayout.setBackgroundColor(newColor);
    }

    private int getRandomColor() {
        return (Color.rgb(colorRandom.nextInt(255),
                colorRandom.nextInt(255),
                colorRandom.nextInt(255)));
    }

    private String colorString(int intColor) {
        return String.format("#%06X", (0xFFFFFF & intColor));
    }

    private void updateTextColor(int intColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (Color.luminance(intColor) > 0.5) {
                textView.setTextColor(Color.BLACK);
            } else {
                textView.setTextColor(Color.WHITE);
            }
        } else {
            textView.setTextColor(Color.BLACK);
        }
    }

}