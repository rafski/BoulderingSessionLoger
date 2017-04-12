package com.example.raf.boulderingsessionloger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Log_session extends AppCompatActivity {

    Date today;
    Date tomorrow;
    int isNewSession;
    String oldSessionId;

    public void getIntentExtras(){
        Intent intent = getIntent();
        isNewSession = intent.getIntExtra("isNew", 0);
        oldSessionId = intent.getStringExtra("sID");
    }

    public void getDate() {

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        today = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 1);

        tomorrow = cal.getTime();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_session);

        final List<Problem> problems = new ArrayList<>();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.problemRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        final ProblemRecyclerViewAdapter adapter = new ProblemRecyclerViewAdapter(problems);

        getIntentExtras();

        final String[] id = new String[1];

        Log.i("isnew", String.valueOf(isNewSession));
        getDate();

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (isNewSession == 1){


            final ParseObject BoulderingSession = new ParseObject("BoulderingSession");
            BoulderingSession.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException ex) {
                    if (ex == null) {

                        id[0] = BoulderingSession.getObjectId();

                        Log.i("created session id", id[0]);

                        ParseQuery<ParseObject> query = ParseQuery.getQuery("SavedProblem");

                        query.orderByAscending("createdAt");
                        query.whereEqualTo("parent", id[0]);

                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if (e == null){

                                    if (objects.size() > 0){

                                        for (ParseObject object : objects){

                                            problems.add(new Problem(object.getInt("problemGrade") ,object.getInt("problemAttempts") , object.getInt("problemNumber"), object.getBoolean("problemIsTraining"), object.getBoolean("problemIsTraining"), String.valueOf(object.getString("circuitName"))));
                                        }

                                        recyclerView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        });
                        Toast.makeText(getApplication().getBaseContext(), "Session created", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplication().getBaseContext(), "No session created", Toast.LENGTH_LONG).show();
                    }
                }
            });


        } if (isNewSession == 2){

            ParseQuery<ParseObject> query = ParseQuery.getQuery("SavedProblem");

            query.orderByAscending("createdAt");
            query.whereContains("parent", oldSessionId);

            Log.i("goingBack", oldSessionId);

            final String[] problemId = new String[1];

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {

                    if (e == null){

                        if (objects.size() > 0){

                            for (ParseObject object : objects){

                                problemId[0] = object.getObjectId();

                                problems.add(new Problem(object.getInt("problemGrade") ,object.getInt("problemAttempts") ,
                                        object.getInt("problemNumber"), object.getBoolean("problemIsTraining"),
                                        object.getBoolean("problemIsTraining"), String.valueOf(object.getString("circuitName"))));
                            }

                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });

        }

        final FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.addProblemFAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Log_session.this, AddProblem.class);
                startActivity(intent);
                isNewSession = 3;

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
    public void onResume() {
        super.onResume();

        final List<Problem> problems = new ArrayList<>();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.problemRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        final ProblemRecyclerViewAdapter adapter = new ProblemRecyclerViewAdapter(problems);


        if (isNewSession == 3) {
            ParseQuery<ParseObject> session = ParseQuery.getQuery("BoulderingSession");
            session.orderByDescending("createdAt");
            session.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(final ParseObject sessionObject, ParseException e) {
                    if (e == null) {
                        if (sessionObject != null) {

                            ParseQuery<ParseObject> query = ParseQuery.getQuery("SavedProblem");

                            query.orderByAscending("createdAt");
                            query.whereEqualTo("parent", sessionObject);

                            Log.i("notsessionid", sessionObject.getObjectId());

                            query.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {

                                    if (e == null) {

                                        if (objects.size() > 0) {

                                            for (ParseObject object : objects) {

                                                problems.add(new Problem(object.getInt("problemGrade"), object.getInt("problemAttempts"), object.getInt("problemNumber"), object.getBoolean("problemIsTraining"), object.getBoolean("problemIsTraining"), String.valueOf(object.getString("circuitName"))));
                                            }

                                            recyclerView.setAdapter(adapter);
                                            adapter.notifyDataSetChanged();

                                            Log.i("hello", "1");
                                        }
                                    }
                                }
                            });
                        }

                    }
                }
            });
        }
    }
}