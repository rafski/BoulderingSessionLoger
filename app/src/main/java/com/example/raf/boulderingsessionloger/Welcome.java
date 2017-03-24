package com.example.raf.boulderingsessionloger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Welcome extends AppCompatActivity {

    private List<Session> sessions;
    private RecyclerView recyclerView;
    View parentLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){

            case R.id.logoutmenubutton:
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                goToLogIn();
                return true;
            default:
                return false;

        }

    }

    public void goToLogSession(View view) {

        boolean isNew = true;

        Intent intent = new Intent(Welcome.this, Log_session.class);
        intent.putExtra("isNew", isNew );
        startActivity(intent);
    }

    public void goToLogIn() {

        Intent intent = new Intent(Welcome.this, Login.class);
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

        parentLayout = findViewById(R.id.coordinatorLayout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        SessionRecyclerViewAdapter adapter = new SessionRecyclerViewAdapter(sessions);
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

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.addSessionFAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToLogSession(view);
            }
        });
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
        SessionRecyclerViewAdapter adapter = new SessionRecyclerViewAdapter(sessions);
        recyclerView.setAdapter(adapter);
    }
}
