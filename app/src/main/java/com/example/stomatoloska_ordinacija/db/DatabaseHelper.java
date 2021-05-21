package com.example.stomatoloska_ordinacija.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.stomatoloska_ordinacija.db.model.ZdravstveniKarton;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    //Dajemo ime bazi
    private static final String DATABASE_NAME = "baza.db";

    //i pocetnu verziju baze. Obicno krece od 1
    private static final int DATABASE_VERSION = 1;

    private Dao<ZdravstveniKarton, Integer> medicalRecord = null;

    //Potrebno je dodati konstruktor zbog pravilne inicijalizacije biblioteke
    public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    //Prilikom kreiranja baze potrebno je da pozovemo odgovarajuce metode biblioteke
    //prilikom kreiranja moramo pozvati TableUtils.createTable za svaku tabelu koju imamo
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable( connectionSource, ZdravstveniKarton.class );

            Log.d( "REZ", "Kreirana tabela u bazi" );

        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

    //kada zelimo da izmenomo tabele, moramo pozvati TableUtils.dropTable za sve tabele koje imamo
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            TableUtils.dropTable( connectionSource, ZdravstveniKarton.class, true );

            onCreate( db, connectionSource );

        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }


    //jedan Dao objekat sa kojim komuniciramo. Ukoliko imamo vise tabela
    //potrebno je napraviti Dao objekat za svaku tabelu
    public Dao<ZdravstveniKarton, Integer> getMedicalDao() throws SQLException {

        if (medicalRecord == null) {
            medicalRecord = getDao( ZdravstveniKarton.class );
        }

        return medicalRecord;
    }



    //obavezno prilikom zatvarnaj rada sa bazom osloboditi resurse
    @Override
    public void close() {
        medicalRecord = null;
        super.close();
    }
}
