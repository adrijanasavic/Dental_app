package com.example.stomatoloska_ordinacija.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stomatoloska_ordinacija.R;
import com.example.stomatoloska_ordinacija.db.DatabaseHelper;
import com.example.stomatoloska_ordinacija.db.model.ZdravstveniKarton;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

public class DetaljiKartonaActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private Toolbar toolbar;

    private TextView ime, prezime, vrstaUsluge, email, telefon, opis;

    private ZdravstveniKarton medical;
    private int objekat_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_kartona);

        setupToolbar();
        inicijalizacija();
        preuzmiPodatkeIzIntenta();
    }


    private void inicijalizacija() {
        ime = findViewById(R.id.detail_ime);
        prezime = findViewById(R.id.detail_prezime);
        vrstaUsluge = findViewById(R.id.detail_vrstaUsluge);
        email = findViewById(R.id.detail_email);
        telefon = findViewById(R.id.detail_telefon);
        opis = findViewById(R.id.detail_opis);

    }

    //preuzima podatke koje se nalaze u intentu koji je trigerovao aktivnost
    //setuje vrednosti polja na trenutnu vrednost objekta izvucenog iz baze na osnovu objekat_id
    private void preuzmiPodatkeIzIntenta() {
        objekat_id = getIntent().getIntExtra("objekat_id", -1);
        if (objekat_id < 0) {
            Toast.makeText(this, "Greska! " + objekat_id + " ne postoji", Toast.LENGTH_SHORT).show();
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


    //setuje toolbar
    public void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Vaši podaci kartona");

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, ListaKartonaActivity.class));
                break;
            case R.id.action_edit:
                Intent intent1 = new Intent(DetaljiKartonaActivity.this, IzmenaKartonaActivity.class);
                intent1.putExtra("objekat_id", medical.getId());
                startActivity(intent1);
                break;
            case R.id.action_delete:
                delete();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
    //^^^ kraj setupa za toolbar


    //brise objekat i sve objekte koji su vezani
    //izbacuje alert dialog za potvrdu brisanja
    private void delete() {
        AlertDialog dialogDelete = new AlertDialog.Builder(this)
                .setTitle("Brisanje")
                .setMessage("Da li ste sigurni da želite da obrišete?")
                .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {

                            getDatabaseHelper().getMedicalDao().delete(medical);
//
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(DetaljiKartonaActivity.this,
                                ListaKartonaActivity.class);
                        startActivity(intent);
                        Toast.makeText(DetaljiKartonaActivity.this,
                                "Toast da su Vaši podaci " + medical.getIme() +
                                        " obrisan iz baze!", Toast.LENGTH_SHORT).show();
                    }


                })
                .setNegativeButton("Odustani", null)
                .show();
    }


    //***** za komuikaciju sa bazom i oslobadjanje resursa
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
    //***** KRAJ za komuikaciju sa bazom i oslobadjanje resursa


}
