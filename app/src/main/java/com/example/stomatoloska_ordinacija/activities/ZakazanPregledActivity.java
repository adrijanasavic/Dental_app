package com.example.stomatoloska_ordinacija.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.example.stomatoloska_ordinacija.R;

public class ZakazanPregledActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakazan_pregled);

        TextView ime = findViewById(R.id.ime);
        TextView email = findViewById(R.id.email);
        TextView telefon = findViewById(R.id.telefon);
        TextView osiguranje = findViewById(R.id.osiguranje);
        TextView klinika = findViewById(R.id.klinika);
        TextView usluga = findViewById(R.id.usluga);
        TextView specijalista = findViewById(R.id.specijalista);
        TextView datum = findViewById(R.id.datum);
        TextView vreme = findViewById(R.id.vreme);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String nameRes = sharedPref.getString("Ime", null);
        String emailRes = sharedPref.getString("Email", null);
        String phoneRes = sharedPref.getString("Telefon", null);
        String coverageRes = sharedPref.getString("Radio", null);
        String clinicRes = sharedPref.getString("Klinike", null);
        String procedureRes = sharedPref.getString("Usluge", null);
        String specialistRes = sharedPref.getString("Specijalisti", null);
        String dateRes = sharedPref.getString("Datum", null);
        String timeRes = sharedPref.getString("Vreme", null);

        ime.setText(nameRes);
        email.setText(emailRes);
        telefon.setText(phoneRes);
        osiguranje.setText(coverageRes);
        klinika.setText(clinicRes);
        usluga.setText(procedureRes);
        specijalista.setText(specialistRes);
        datum.setText(dateRes);
        vreme.setText(timeRes);
    }

    public void modify(View v) {
        startActivity(new Intent(ZakazanPregledActivity.this,LoginActivity.class));
    }

    public void book(View v) {
        startActivity(new Intent(ZakazanPregledActivity.this, PodaciZakazanogPregleda.class));
    }
}