package com.example.stomatoloska_ordinacija.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.example.stomatoloska_ordinacija.R;

public class PodaciZakazanogPregleda extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podaci_zakazanog_pregleda);

        TextView klinika = findViewById(R.id.klinika);
        TextView procedura = findViewById(R.id.procedura);
        TextView specijalista = findViewById(R.id.specijalista);
        TextView datum = findViewById(R.id.datum);
        TextView vreme = findViewById(R.id.vreme);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String klinikaS = sharedPref.getString("Klinike", null);
        String proceduraS = sharedPref.getString("Usluge", null);
        String specijalistaS = sharedPref.getString("Specijalisti", null);
        String datumS = sharedPref.getString("Datum", null);
        String vremeS = sharedPref.getString("Vreme", null);

        klinika.setText(klinikaS);
        procedura.setText(proceduraS);
        specijalista.setText(specijalistaS);
        datum.setText(datumS);
        vreme.setText(vremeS);
    }

    public void opcija1(View v) {
        startActivity(new Intent(PodaciZakazanogPregleda.this,DodavanjeKartonaActivity.class));
    }

    public void opcija2(View v) {
        startActivity(new Intent(PodaciZakazanogPregleda.this,ZakazivanjeActivity.class));
    }
}