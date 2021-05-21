package com.example.stomatoloska_ordinacija.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.stomatoloska_ordinacija.R;

import java.text.DateFormat;
import java.util.Calendar;

public class ZakazivanjeActivity extends AppCompatActivity {

    private TextView date;
    private TextView time;

    String klinike;
    String usluge;
    String specijalisti;
    String datum;
    String vreme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakazivanje);

        final Spinner clinic = findViewById(R.id.klinika);
        final Spinner procedure = findViewById(R.id.procedure);
        final Spinner specialist = findViewById(R.id.specialist);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        LinearLayout button = findViewById(R.id.button);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(ZakazivanjeActivity.this, d, c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new TimePickerDialog(ZakazivanjeActivity.this, t, c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE), false).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                klinike = clinic.getSelectedItem().toString();
                usluge = procedure.getSelectedItem().toString();
                specijalisti = specialist.getSelectedItem().toString();
                datum = date.getText().toString();
                vreme = time.getText().toString();

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Klinike", klinike);
                editor.putString("Usluge", usluge);
                editor.putString("Specijalisti", specijalisti);
                editor.putString("Datum", datum);
                editor.putString("Vreme", vreme);
                editor.commit();

                if(datum.equals("Izaberite datum")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Izaberite odgovarajući datum",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 245);
                    toast.show();
                }
                else if(vreme.equals("Izaberite vreme")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Izaberite odgovarajući vreme",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 245);
                    toast.show();
                }
                else {
                    startActivity(new Intent(ZakazivanjeActivity.this,
                            PodaciZakazivanjaActivity.class));
                }
            }
        });

        final String[] specs1 = getResources().getStringArray(R.array.spec1);
        final String[] specs2 = getResources().getStringArray(R.array.spec2);
        final String[] specs3 = getResources().getStringArray(R.array.spec3);
        final String[] specs4 = getResources().getStringArray(R.array.spec4);
        final String[] specs5 = getResources().getStringArray(R.array.spec5);
        final String[] specs6 = getResources().getStringArray(R.array.spec6);

        clinic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                switch (position) {
                    case 0:
                        ArrayAdapter<String> clinic1 = new ArrayAdapter<String>(ZakazivanjeActivity.this,
                                android.R.layout.simple_spinner_item, specs1);
                        clinic1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        specialist.setAdapter(clinic1);
                        break;
                    case 1:
                        ArrayAdapter<String> clinic2 = new ArrayAdapter<String>(ZakazivanjeActivity.this,
                                android.R.layout.simple_spinner_item, specs2);
                        clinic2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        specialist.setAdapter(clinic2);
                        break;
                    case 2:
                        ArrayAdapter<String> clinic3 = new ArrayAdapter<String>(ZakazivanjeActivity.this,
                                android.R.layout.simple_spinner_item, specs3);
                        clinic3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        specialist.setAdapter(clinic3);
                        break;
                    case 3:
                        ArrayAdapter<String> clinic4 = new ArrayAdapter<String>(ZakazivanjeActivity.this,
                                android.R.layout.simple_spinner_item, specs4);
                        clinic4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        specialist.setAdapter(clinic4);
                        break;
                    case 4:
                        ArrayAdapter<String> clinic5 = new ArrayAdapter<String>(ZakazivanjeActivity.this,
                                android.R.layout.simple_spinner_item, specs5);
                        clinic5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        specialist.setAdapter(clinic5);
                        break;
                    case 5:
                        ArrayAdapter<String> clinic6 = new ArrayAdapter<String>(ZakazivanjeActivity.this,
                                android.R.layout.simple_spinner_item, specs6);
                        clinic6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        specialist.setAdapter(clinic6);
                        break;

                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {}
        });
    }

    Calendar c = Calendar.getInstance();
    DateFormat fmtDate = DateFormat.getDateInstance(DateFormat.LONG);
    DateFormat fmtTime = DateFormat.getTimeInstance(DateFormat.SHORT);

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            date.setText(fmtDate.format(c.getTime()));
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            time.setText(fmtTime.format(c.getTime()));
        }
    };
}
