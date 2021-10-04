package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.reto1.databinding.ActivityDesicionFotoBinding;

public class DesicionFoto extends AppCompatActivity {

    private Button cameraBtn,galeriaBtn;
    private int select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desicion_foto);

        cameraBtn = findViewById(R.id.cameraBtn);
        galeriaBtn = findViewById(R.id.galeriaBtn);

        cameraBtn.setOnClickListener(v->{
            select =0 ;
            Intent data = new Intent();
            data.putExtra("select",select);
            setResult(RESULT_OK,data);
            finish();
        });
        galeriaBtn.setOnClickListener(v->{
            select=1;
            Intent data = new Intent();
            data.putExtra("select",select);
            setResult(RESULT_OK,data);
            finish();
        });



    }


}