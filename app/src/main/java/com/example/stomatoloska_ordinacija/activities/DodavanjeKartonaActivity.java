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

public class DodavanjeKartonaActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private ZdravstveniKarton medical;

    private EditText ime, prezime, vrstaUsluge, email, telefon, opis;
    private Button btn_add;
    private Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_kartona);

        inicijalizacija();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add();

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DodavanjeKartonaActivity.this,
                        ListaKartonaActivity.class));
            }
        });
    }

    private void inicijalizacija() {
        ime = findViewById(R.id.add_ime);
        prezime = findViewById(R.id.add_prezime);
        vrstaUsluge = findViewById(R.id.add_usluga);
        email = findViewById(R.id.add_email);
        telefon = findViewById(R.id.add_telefon);
        opis = findViewById(R.id.add_opis);
        btn_add = findViewById(R.id.btn_add);
        btn_cancel = findViewById(R.id.btn_cancel);
    }

    //dodaju se uneseni podaci u objekat i taj objekat u bazu
    private void add() {
        //validacija unesenih polja. Provera da li su polja uneta
        if (validateInput(ime)
                && validateInput(opis)
        ) {
            medical = new ZdravstveniKarton();
            medical.setIme(ime.getText().toString().trim());
            medical.setPrezime(prezime.getText().toString().trim());
            medical.setNazivUsluge(vrstaUsluge.getText().toString().trim());
            medical.setEmail(email.getText().toString().trim());
            medical.setTelefon(telefon.getText().toString().trim());
            medical.setOpis(opis.getText().toString().trim());

            try {

                getDatabaseHelper().getMedicalDao().create(medical);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Toast.makeText(DodavanjeKartonaActivity.this, "Toast da je novi pacijent"
                    + medical.getIme() + " upisano u bazi", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(DodavanjeKartonaActivity.this,
                    ListaKartonaActivity.class);
            startActivity(intent);
            finish();
        }
    }


    //validacija unosa
    public static boolean validateInput(EditText editText) {
        String titleInput = editText.getText().toString().trim();

        if (titleInput.isEmpty()) {
            editText.setError("Polje ne moze da bude prazno!");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
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
