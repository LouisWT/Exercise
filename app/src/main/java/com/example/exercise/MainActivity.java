package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button jumpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        jumpBtn = findViewById(R.id.start_button);
        jumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise();
            }
        });
    }

    public void startExercise() {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.horizontal_slide, R.anim.horizontal_slide_back);
    }
}