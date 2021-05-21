package com.example.stomatoloska_ordinacija.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stomatoloska_ordinacija.R;

public class LoginActivity extends AppCompatActivity {

    Button odustani, log;
    EditText ed1, ed2;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        odustani = findViewById(R.id.odustani);
        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);

        log = findViewById(R.id.log);
        tx1 = findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().trim().equals("Ordinacija") &&
                        ed2.getText().toString().trim().equals("Ordinacija")) {
                    Toast.makeText(getApplicationContext(),
                            "Preusmeravanje...", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, DodavanjeKartonaActivity.class));

                } else {
                    Toast.makeText(getApplicationContext(), "Pogrešno korisničko ime ili lozinka ", Toast.LENGTH_SHORT).show();

                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.GRAY);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        odustani.setEnabled(false);
                        finish();
                    }

                }
            }
        });

        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


