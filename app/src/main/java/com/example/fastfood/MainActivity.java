package com.example.fastfood;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public ImageView btn_inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        btn_inicio = (ImageView) findViewById(R.id.btn_inicio);
        btn_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MenuActivity.class);
                startActivityForResult(intent, 0);

            }
        });

    }


}