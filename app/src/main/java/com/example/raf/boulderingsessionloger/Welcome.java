package com.example.raf.boulderingsessionloger;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Welcome extends AppCompatActivity {

    int isNew;
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
            case R.id.graphsmenubutton:
                Intent intent = new Intent(Welcome.this, Graphs.class);
                startActivity(intent);
                return true;
            default:
                return false;

        }

    }

    public void goToLogSession(View view) {

        isNew = 1;
        Intent intent = new Intent(Welcome.this, Log_session.class);
        intent.putExtra("isNew", isNew );
        startActivity(intent);
    }

    public void goToLogIn() {

        Intent intent = new Intent(Welcome.this, Login.class);
        startActivity(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        isNetworkAvailable();

        final List<Session> sessions = new ArrayList<>();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler);

        parentLayout = findViewById(R.id.coordinatorLayout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        final SessionRecyclerViewAdapter adapter = new SessionRecyclerViewAdapter(sessions);
        recyclerView.setAdapter(adapter);

        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            // do stuff with the user
        } else {
            Intent intent = new Intent(Welcome.this, Login.class);
            startActivity(intent);

        }

        ParseQuery<ParseObject> oldSessions = new ParseQuery<ParseObject>("BoulderingSession");
        if (isNetworkAvailable() == false){
            oldSessions.fromLocalDatastore();
        }
        oldSessions.orderByDescending("createdAt");
        oldSessions.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> sessionsParse, ParseException e) {
                if (e == null){

                    if (sessionsParse.size() > 0){

                        for (final ParseObject sessionParse : sessionsParse){

                            String createdAt = sessionParse.getCreatedAt().toString();
                            String input = createdAt;
                            String output = input.substring(0, 10) +" "+ input.substring(30, 34);
                            final String sessionDate = output;
                            final String sID = sessionParse.getObjectId();

                            ParseQuery<ParseObject> problemsInSession = new ParseQuery<ParseObject>("SavedProblem");
                            if (isNetworkAvailable() == false){
                                problemsInSession.fromLocalDatastore();
                            }
                            problemsInSession.whereEqualTo("parent", sessionParse);
                            problemsInSession.findInBackground(new FindCallback<ParseObject>() {

                                @Override
                                public void done(List<ParseObject> problems, ParseException e) {
                                    if (e == null){
                                        int totalGrade = 0;

                                        if (problems.size() >0){

                                            for (final ParseObject problem : problems){

                                                totalGrade = totalGrade + problem.getInt("problemGrade");

                                            }
                                        }

                                        int averageGrade;
                                        if (problems.size() > 0){
                                            averageGrade = totalGrade/problems.size();
                                        }else{
                                            averageGrade = 0;
                                        }

                                        sessions.add(new Session(String.valueOf(problems.size()) + " problems",
                                                "V" + averageGrade + " average", sessionDate, sID));
                                        recyclerView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });


        final FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.addSessionFAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToLogSession(view);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if (dy > 0 ||dy<0 && FAB.isShown())
                {
                    FAB.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    FAB.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        final List<Session> sessions = new ArrayList<>();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler);

        parentLayout = findViewById(R.id.coordinatorLayout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        final SessionRecyclerViewAdapter adapter = new SessionRecyclerViewAdapter(sessions);
        recyclerView.setAdapter(adapter);

        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            // do stuff with the user
        } else {
            Intent intent = new Intent(Welcome.this, Login.class);
            startActivity(intent);

        }

        ParseQuery<ParseObject> oldSessions = new ParseQuery<ParseObject>("BoulderingSession");
        oldSessions.orderByDescending("createdAt");
        oldSessions.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> sessionsParse, ParseException e) {
                if (e == null){

                    if (sessionsParse.size() > 0){

                        for (final ParseObject sessionParse : sessionsParse){

                            String createdAt = sessionParse.getCreatedAt().toString();
                            String input = createdAt;
                            String output = input.substring(0, 10) +" "+ input.substring(30, 34);
                            final String sessionDate = output;

                            final String sID = sessionParse.getObjectId();

                            ParseQuery<ParseObject> problemsInSession = new ParseQuery<ParseObject>("SavedProblem");

                            problemsInSession.whereEqualTo("parent", sessionParse);
                            problemsInSession.findInBackground(new FindCallback<ParseObject>() {

                                @Override
                                public void done(List<ParseObject> problems, ParseException e) {
                                    if (e == null){
                                        int totalGrade = 0;

                                        if (problems.size() >0){

                                            for (final ParseObject problem : problems){

                                                totalGrade = totalGrade + problem.getInt("problemGrade");

                                            }
                                        }
                                        int averageGrade;
                                        if (problems.size() > 0){
                                            averageGrade = totalGrade/problems.size();
                                        }else{
                                            averageGrade = 0;
                                        }
                                        sessions.add(new Session(String.valueOf(problems.size()) + " problems",
                                                "V" + averageGrade + " average", sessionDate, sID));


                                        recyclerView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });

    }
}
