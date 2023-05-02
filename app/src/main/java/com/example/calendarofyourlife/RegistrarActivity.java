package com.example.calendarofyourlife;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnFechaNacimiento;
    EditText FechaNacimiento;
    private int dia,mes,anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnFechaNacimiento =(Button) findViewById(R.id.btnFechaNacimiento);
        FechaNacimiento =(EditText) findViewById(R.id.FechaNacimiento);
        btnFechaNacimiento.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);

        DatePickerDialog datepickerdialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                FechaNacimiento.setText((dayOfMonth)+"/"+(month+1)+"/"+(year));
            }
        }
                ,dia,mes,anio);
        datepickerdialog.show();
    }
}