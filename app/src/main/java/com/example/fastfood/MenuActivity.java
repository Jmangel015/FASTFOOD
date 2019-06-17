package com.example.fastfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;



public class MenuActivity extends AppCompatActivity {
    ImageView micro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        micro = (ImageView) findViewById(R.id.imageView);
        micro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Pedido.class);
                startActivityForResult(intent, 0);

            }
        });

    }

}