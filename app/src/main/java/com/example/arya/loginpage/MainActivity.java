package com.example.arya.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    EditText etUName;
    EditText etPassword;
    String SaveUName;
    String SavePassword;
    Button btLogin, btReset;
    String uName, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("myShare", MODE_PRIVATE);
        SaveUName = String.valueOf(prefs.getString("uName", "a"));
        SavePassword = String.valueOf(prefs.getString("pass", "a"));
        if (SaveUName.equals("sanyam") && SavePassword.equals("123")) {
            welcomePage();
        }
        etUName = (EditText) findViewById(R.id.etUName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        btReset = (Button) findViewById(R.id.btReset);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uName = etUName.getText().toString();
                pass = etPassword.getText().toString();
                if (uName.equals("sanyam") && pass.equals("123")) {
                    saveUName();
                    welcomePage();
                } else {
                    Toast.makeText(MainActivity.this, "Wrong Username " +
                            "or Password", Toast.LENGTH_SHORT).show();
                    etUName.setText("");
                    etPassword.setText("");


                }
            }
        });
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUName.setText("");
                etPassword.setText("");
            }
        });
    }

    private void saveUName() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("uName", uName);
        editor.putString("pass", pass);
        editor.commit();
    }

    private void welcomePage() {
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }

}
