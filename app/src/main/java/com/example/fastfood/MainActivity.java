package com.example.fastfood;


import android.content.Intent;
import android.media.MediaPlayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    public ImageView btn_inicio;
     public MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        mp = (MediaPlayer) MediaPlayer.create(this, R.raw.click);

        btn_inicio = (ImageView) findViewById(R.id.btn_inicio);
        btn_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(v.getContext(), MenuActivity.class);
                startActivityForResult(intent, 0);

            }
        });

    }


}