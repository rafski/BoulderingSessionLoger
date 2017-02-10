package com.example.raf.boulderingsessionloger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    public void goToLogSession(View view){

        Intent intent = new Intent(Welcome.this, Log_session.class);
        startActivity(intent);

    }

    public void goToViewHistory(View view){

        Intent intent = new Intent(Welcome.this, View_history.class);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
}
