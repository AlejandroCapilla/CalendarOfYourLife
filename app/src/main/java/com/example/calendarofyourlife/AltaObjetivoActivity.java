package com.example.calendarofyourlife;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import yuku.ambilwarna.AmbilWarnaDialog;

public class AltaObjetivoActivity extends AppCompatActivity {

    Button btnDiaIni, btnDiaFin, btnColor;
    EditText DiaIni, DiaFin;

    private int dia,mes,anio, defaultcolor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_objetivo);

        btnDiaIni =(Button) findViewById(R.id.btnDiaIni);
        DiaIni =(EditText) findViewById(R.id.DiaIni);
        btnDiaFin =(Button) findViewById(R.id.btnDiaFin);
        DiaFin =(EditText) findViewById(R.id.DiaFin);
        btnColor =(Button) findViewById(R.id.btnColor);

        btnDiaIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaIni();
            }
        });
        btnDiaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaFin();
            }
        });
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Color(); }
        });
    }

    public void DiaIni() {
        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);

        DatePickerDialog datepickerdialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DiaIni.setText((dayOfMonth)+"/"+(month+1)+"/"+(year));
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
            }
        }
                ,dia,mes,anio);
        datepickerdialog.show();
    }

    public void DiaFin() {
        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);

        DatePickerDialog datepickerdialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                DiaFin.setText((dayOfMonth)+"/"+(month+1)+"/"+(year));
            }
        }
                ,dia,mes,anio);
        datepickerdialog.show();
    }

    public void Color(){
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultcolor = color;
                ColorStateList colorStateList = new ColorStateList(
                        new int[][]
                                {
                                        new int[]{-android.R.attr.state_enabled}, // Disabled
                                        new int[]{android.R.attr.state_enabled}   // Enabled
                                },
                        new int[]
                                {
                                        defaultcolor, // disabled
                                        defaultcolor   // enabled
                                }
                );
                //RB1.setButtonTintList(colorStateList);
            }
        });
        ambilWarnaDialog.show();

    }
}
