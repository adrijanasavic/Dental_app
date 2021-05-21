package com.example.stomatoloska_ordinacija.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.stomatoloska_ordinacija.R;
import com.example.stomatoloska_ordinacija.adapter.AdapterKartonPacijenta;
import com.example.stomatoloska_ordinacija.db.DatabaseHelper;
import com.example.stomatoloska_ordinacija.db.model.ZdravstveniKarton;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

public class ListaKartonaActivity extends AppCompatActivity implements AdapterKartonPacijenta.OnRecyclerItemClickListener {

    private DatabaseHelper databaseHelper;

    private Toolbar toolbar;

    RecyclerView recyclerView;
    AdapterKartonPacijenta adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_kartona);

        recyclerView = findViewById(R.id.rv_list);
        setupToolbar();

        setupRecycleView();
    }


    //Pocetak setup-a za toolbar
    // action bar prikazuje opcije iz meni.xml
    //uneti u action main.xml AppBarLayout i onda Toolbar

    //setuje toolbar
    public void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Vaš karton");

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // onOptionsItemSelected method is called whenever an item in the Toolbar is selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                Intent intent = new Intent(this, DodavanjeKartonaActivity.class);
                startActivity(intent);
                break;

            case R.id.action_about:
                aboutDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    //^^^ kraj setupa za toolbar

    @Override
    public void onRVItemClick(ZdravstveniKarton objekat) {
        Toast.makeText(this, "Kliknuto na RV listu na " + objekat.getIme() + objekat.getId(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetaljiKartonaActivity.class);
        intent.putExtra("objekat_id", objekat.getId());

        startActivity(intent);
    }

    //stavljanje RV adaptera
    private void setupRecycleView() {
        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {

            adapter = new AdapterKartonPacijenta(getDatabaseHelper().getMedicalDao().queryForAll(), this);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);

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


    private void aboutDialog() {
        AlertDialog about = new AlertDialog.Builder(this)
                .setTitle("O aplikaciji")
                .setMessage("Naziv aplikacije: Stomatološka ordinacija\nAutor: Vladimir Perišić")
                .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }

                })
                .setNegativeButton("Ne", null)
                .show();
    }

}


