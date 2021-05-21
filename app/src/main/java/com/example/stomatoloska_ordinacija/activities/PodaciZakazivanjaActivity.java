package com.example.stomatoloska_ordinacija.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.stomatoloska_ordinacija.R;

public class PodaciZakazivanjaActivity extends AppCompatActivity {

    String ime;
    String email;
    String telefon;
    String radio;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podaci_zakazivanja);

        final EditText name = findViewById(R.id.editText1);
        final EditText email = findViewById(R.id.editText2);
        final EditText phone = findViewById(R.id.editText3);
        final RadioGroup radioG = findViewById(R.id.radioG);
        LinearLayout button = findViewById(R.id.save);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedId = radioG.getCheckedRadioButtonId();

                radioButton = findViewById(selectedId);

                ime = name.getText().toString();
                PodaciZakazivanjaActivity.this.email = email.getText().toString();
                telefon = phone.getText().toString();
                radio = radioButton.getText().toString();

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Ime", ime);
                editor.putString("Email", PodaciZakazivanjaActivity.this.email);
                editor.putString("Telefon", telefon);
                editor.putString("Radio", radio);
                editor.commit();

                if(ime.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Molim Vas unesite Vaše ime i prezime", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 400);
                    toast.show();
                }
                else if(PodaciZakazivanjaActivity.this.email.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Molim Vas unesite Vašu email adresu", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 600);
                    toast.show();
                }
                else if(telefon.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Molim Vas unesite Vaš broj telefona", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 800);
                    toast.show();
                }
                else {
                    startActivity(new Intent(PodaciZakazivanjaActivity.this, ZakazanPregledActivity.class));
                }
            }
        });
    }
}