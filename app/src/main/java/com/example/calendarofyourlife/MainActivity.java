package com.example.calendarofyourlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IrIniciarSesion(View View){
        Intent i = new Intent(this,IniciarSesionActivity.class);
        startActivity(i);
    }

    public void IrRegistro(View View){
        Intent i = new Intent(this,RegistrarActivity.class);
        startActivity(i);
    }

}