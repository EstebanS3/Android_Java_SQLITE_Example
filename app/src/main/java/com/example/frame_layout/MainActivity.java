package com.example.frame_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    Button et_Inicio,et_Detener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_Inicio=findViewById(R.id.Iniciar);
        et_Detener=findViewById(R.id.Detener);
    }

    public void Variador(View vista){
        if(et_Inicio.isShown()){
            et_Inicio.setVisibility(View.INVISIBLE);
            et_Detener.setVisibility(View.VISIBLE);
        } else if (et_Detener.isShown()) {
            et_Inicio.setVisibility(View.VISIBLE);
            et_Detener.setVisibility(View.INVISIBLE);
        }
    }

    public void Detener(View vista){

    }
}