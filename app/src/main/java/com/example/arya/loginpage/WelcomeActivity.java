package com.example.arya.loginpage;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    SharedPreferences prefs;
    TextView tvName;
    Button btLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("myShare", MODE_PRIVATE);
        setContentView(R.layout.activity_welcome);
        tvName = (TextView) findViewById(R.id.tvName);
        btLogOut = (Button) findViewById(R.id.btLogout);
        tvName.setText(String.valueOf(prefs.getString("uName", "noName")));
        btLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logMeOut();

            }
        });
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            logMeOut();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void logMeOut() {
        final Dialog dialog = new Dialog(WelcomeActivity.this);
        dialog.setContentView(R.layout.custom_exit_dialog);
        Button btYes = (Button) dialog.findViewById(R.id.btYes);
        Button btNo = (Button) dialog.findViewById(R.id.btNo);
        Button btExit = (Button) dialog.findViewById(R.id.btExit);
        dialog.setTitle("Enter you data");
        btYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("uName", "a");
                editor.putString("pass", "a");
                editor.commit();
                WelcomeActivity.this.finish();
                dialog.dismiss();
            }
        });
        btNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                WelcomeActivity.this.finishAffinity();
            }
        });
        dialog.show();
    }
}
