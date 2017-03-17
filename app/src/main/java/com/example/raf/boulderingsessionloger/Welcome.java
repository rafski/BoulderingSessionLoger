package com.example.raf.boulderingsessionloger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Welcome extends AppCompatActivity {

    private List<Session> sessions;
    private RecyclerView recyclerView;

    public void goToLogSession(View view) {

        Intent intent = new Intent(Welcome.this, Log_session.class);
        startActivity(intent);

    }

    public void goToViewHistory(View view) {

        Intent intent = new Intent(Welcome.this, View_history.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(sessions);
        recyclerView.setAdapter(adapter);

        initializeData();
        initializeAdapter();

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
        } else {
            Intent intent = new Intent(Welcome.this, Login.class);
            startActivity(intent);
        }
    }


    private void initializeData() {
        sessions = new ArrayList<>();
        sessions.add(new Session("12 sends", "V4 average"));
        sessions.add(new Session("3 sends", "V6 average"));
        sessions.add(new Session("21 sends", "V3 average"));
        sessions.add(new Session("12 sends", "V4 average"));
        sessions.add(new Session("3 sends", "V6 average"));
        sessions.add(new Session("21 sends", "V3 average"));
        sessions.add(new Session("12 sends", "V4 average"));
        sessions.add(new Session("3 sends", "V6 average"));
        sessions.add(new Session("21 sends", "V3 average"));
    }

    private void initializeAdapter() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(sessions);
        recyclerView.setAdapter(adapter);
    }
}
