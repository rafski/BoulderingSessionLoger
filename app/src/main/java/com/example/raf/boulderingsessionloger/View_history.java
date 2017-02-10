package com.example.raf.boulderingsessionloger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class View_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        final ListView sessionHistory = (ListView)findViewById(R.id.sessionHistory);

        ArrayList<String> sessionDates = new ArrayList<String>();

        sessionDates.add("12/01/2017");
        sessionDates.add("14/01/2017");
        sessionDates.add("19/01/2017");
        sessionDates.add("23/01/2017");
        sessionDates.add("1/02/2017");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sessionDates);
        sessionHistory.setAdapter(arrayAdapter);
        sessionHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String sessionDate = (sessionHistory.getItemAtPosition(i).toString());

                Log.i("The date was ", sessionDate);
            }
        });
    }
}
