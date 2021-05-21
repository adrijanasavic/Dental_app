package com.example.stomatoloska_ordinacija.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "atrakcije")
public class ZdravstveniKarton  {

    // Anotacija DatabaseField se koristi da oderedi koja tabela odgovara kojoj klasi u Javi
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "ime")
    private String ime;

    @DatabaseField(columnName = "prezime")
    private String prezime;

    @DatabaseField(columnName = "nazivUsluge")
    private String nazivUsluge;

    @DatabaseField(columnName = "email")
    private String email;

    @DatabaseField(columnName = "telefon")
    private String telefon;

    @DatabaseField(columnName = "opis")
    private String opis;


    public ZdravstveniKarton() {
        // konstruktor bez parametara je obavezan u ORMLite
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return ime;
    }

    public void setDatum(int year, int month, int dayOfMonth) {
    }
}
