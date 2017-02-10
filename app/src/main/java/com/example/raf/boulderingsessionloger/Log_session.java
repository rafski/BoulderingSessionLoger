package com.example.raf.boulderingsessionloger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Log_session extends AppCompatActivity {

    public void buttonPressed(View view) {

        int id = view.getId();
        String ourId = "";

        ourId = view.getResources().getResourceEntryName(id);

        //int resourceId = getResources().getIdentifier(ourId, null, null);

       //do stuff with specific button here

        Log.i("button tapped", ourId);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_session);
    }
}
