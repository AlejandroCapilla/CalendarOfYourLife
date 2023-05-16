package com.example.calendarofyourlife;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton altaObj;

    Button btnDiaIni, btnDiaFin, btnColor, btnCrearObj;

    EditText DiaIni, DiaFin;
    private int dia,mes,anio, defaultcolor, SemanaIni, SemanaFin;

    ColorStateList colorStateList;

    RadioButton rdo_1_1;

    RadioButton[][] radioButtons = new RadioButton[100][52];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        altaObj =(FloatingActionButton) findViewById(R.id.altaObj);

        rdo_1_1 =(RadioButton) findViewById(R.id.rdo_1_1);

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 52; j++) {
                int id = getResources().getIdentifier("rdo_" + (i+1) + "_" + (j+1), "id", getPackageName());
                radioButtons[i][j] = findViewById(id);
            }
        }

        altaObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfazAltaObj();
            }
        });



        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        long totalWeeks = prefs.getLong("weeks", 0);

        int weeksCount = 0;
        // Recorrer todos los años y semanas y marcar los radio buttons correspondientes
        for (int year = 1; year <= 100 && weeksCount < totalWeeks; year++) {
            for (int week = 1; week <= 52 && weeksCount < totalWeeks; week++) {
                // Obtener el ID del radio button correspondiente al año y la semana actual
                int radioButtonId = getResources().getIdentifier("rdo_" + year + "_" + week, "id", getPackageName());
                // Marcar el radio button como seleccionado
                RadioButton radioButton = findViewById(radioButtonId);
                radioButton.setChecked(true);
                weeksCount++;
            }
        }




    }

    public void interfazAltaObj(){
        Dialog popup = new Dialog(this);
        popup.setContentView(R.layout.activity_alta_objetivo);

        btnDiaIni =(Button) popup.findViewById(R.id.btnDiaIni);
        DiaIni =(EditText) popup.findViewById(R.id.DiaIni);
        btnDiaFin =(Button) popup.findViewById(R.id.btnDiaFin);
        DiaFin =(EditText) popup.findViewById(R.id.DiaFin);
        btnColor =(Button) popup.findViewById(R.id.btnColor);
        btnCrearObj =(Button) popup.findViewById(R.id.btnRegistrar);

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

        popup.show();
        btnCrearObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

    public void IrIniciarSesion(View View){
        Intent i = new Intent(this,IniciarSesionActivity.class);
        startActivity(i);
    }

    public void IrRegistro(View View){
        Intent i = new Intent(this,RegistrarActivity.class);
        startActivity(i);
    }

    public void DiaIni() {
        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);

        DatePickerDialog datepickerdialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                SemanaIni = selectedDate.get(Calendar.WEEK_OF_YEAR);
                DiaIni.setText(SemanaIni+"");
            }
        }
                ,anio,mes,dia);
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
                SemanaFin = selectedDate.get(Calendar.WEEK_OF_YEAR);
                DiaFin.setText(SemanaFin+"");
            }
        }
                ,anio,mes,dia);
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
                colorStateList = new ColorStateList(
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
                int total_semanas = SemanaFin - SemanaIni;
                int semana = 0, anio = 0;
                for (int i = 0; i < total_semanas; i++){
                    if (semana > 51){
                        anio++;
                        semana = 0;
                    }
                    radioButtons[anio][semana].setButtonTintList(colorStateList);
                    semana++;
                }
            }
        });

        ambilWarnaDialog.show();
    }

    private int getWeeksForYearAndWeek(int year, int week) {
        // Asumiendo que el primer día de la semana es Lunes
        LocalDate date = LocalDate.of(year, 1, 1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        int weeks = 0;
        while (date.getYear() <= year) {
            if (date.getYear() == year && date.get(WeekFields.ISO.weekOfWeekBasedYear()) > week) {
                break;
            }
            weeks++;
            date = date.plusWeeks(1);
        }
        return weeks;
    }
    private int getWeeksForYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
    }
}