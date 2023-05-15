package com.example.calendarofyourlife;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String MY_CHANNEL_ID = "myChannel";
    Button btnFechaNacimiento;
    EditText FechaNacimiento;
    private int dia,mes,anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        Button btnmain = findViewById(R.id.btnRegistrar);

        btnFechaNacimiento =(Button) findViewById(R.id.btnFechaNacimiento);
        FechaNacimiento =(EditText) findViewById(R.id.FechaNacimiento);
        btnFechaNacimiento.setOnClickListener(this);

        btnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // RegistrarActivityNotification miclase = new RegistrarActivityNotification();

                Intent intent = new Intent(RegistrarActivity.this, Notifications.class);
                startActivity(intent);
            }
        });



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
                LocalDate today = LocalDate.now();
                LocalDate birthdate = LocalDate.of(year, (month+1), dayOfMonth);
                Period p = Period.between(birthdate, today);
                long weeks = ChronoUnit.WEEKS.between(birthdate, today);
                //FechaNacimiento.setText(weeks + " SEMANAS");
                SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong("weeks", weeks);
                editor.apply();
                FechaNacimiento.setText((dayOfMonth)+"/"+(month+1)+"/"+(year));
                //FechaNacimiento.setText(p.getYears()+" ANIOS"+p.getMonths()+" MESES"+p.getDays()+" DIAS");
            }
        }
                ,dia,mes,anio);
        datepickerdialog.show();
    }
}