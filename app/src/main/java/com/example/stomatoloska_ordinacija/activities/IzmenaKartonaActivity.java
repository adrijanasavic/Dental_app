package com.example.stomatoloska_ordinacija.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stomatoloska_ordinacija.R;
import com.example.stomatoloska_ordinacija.db.DatabaseHelper;
import com.example.stomatoloska_ordinacija.db.model.ZdravstveniKarton;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

public class IzmenaKartonaActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private EditText ime, prezime, vrstaUsluge, email, telefon, opis;
    private Button btn_save;
    private Button btn_cancel;
    private int objekat_id;
    private ZdravstveniKarton medical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izmena_kartona);

        inicijalizacija();
        preuzmiPodatkeIzIntenta();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelEdit();
            }
        });
    }

    private void inicijalizacija() {
        ime = findViewById(R.id.edit_ime);
        prezime = findViewById(R.id.edit_prezime);
        vrstaUsluge = findViewById(R.id.edit_vrstaUsluge);
        email = findViewById(R.id.edit_email);
        telefon = findViewById(R.id.edit_telefon);
        opis = findViewById(R.id.edit_opis);
        btn_save = findViewById(R.id.edit_btn_save);
        btn_cancel = findViewById(R.id.edit_btn_cancel);
    }

    private void preuzmiPodatkeIzIntenta() {
        objekat_id = getIntent().getIntExtra("objekat_id", -1);
        if (objekat_id < 0) {
            Toast.makeText(this, "Greska! " + objekat_id + " ne postoji",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        try {

            medical = getDatabaseHelper().getMedicalDao().queryForId(objekat_id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ime.setText(medical.getIme());
        prezime.setText(medical.getPrezime());
        vrstaUsluge.setText(medical.getNazivUsluge());
        email.setText(medical.getEmail());
        telefon.setText(medical.getTelefon());
        opis.setText(medical.getOpis());

    }

    //izmenom podataka setuje se objekat i upisuje u bazi
    private void saveChanges() {

        medical.setIme(ime.getText().toString());
        medical.setPrezime(prezime.getText().toString());
        medical.setNazivUsluge(vrstaUsluge.getText().toString());
        medical.setEmail(email.getText().toString());
        medical.setTelefon(telefon.getText().toString());
        medical.setOpis(opis.getText().toString());

        try {

            getDatabaseHelper().getMedicalDao().createOrUpdate(medical);

            Toast.makeText(IzmenaKartonaActivity.this, "Toast da su Vaši podaci " +
                    medical.getIme() + " izmenjen u bazi!", Toast.LENGTH_SHORT).show();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(IzmenaKartonaActivity.this, DetaljiKartonaActivity.class);
        intent.putExtra("objekat_id", medical.getId());
        startActivity(intent);

    }

    private void cancelEdit() {

        Toast.makeText(IzmenaKartonaActivity.this, "Toast da Vaši podaci " +
                medical.getIme() + " nisu izmenjeni u bazi!", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(IzmenaKartonaActivity.this, DetaljiKartonaActivity.class);
        intent.putExtra("objekat_id", medical.getId());
        startActivity(intent);
    }

    //Metoda koja komunicira sa bazom podataka
    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // nakon rada sa bazo podataka potrebno je obavezno
        //osloboditi resurse!
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
