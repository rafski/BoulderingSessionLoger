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
    boolean isNewSession;

    public void getIntentExtras(){
        Intent intent = getIntent();
        isNewSession = intent.getBooleanExtra("isNew", false);
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

        Log.i("isnew", String.valueOf(isNewSession));
        getDate();

        if (isNewSession){

            ParseQuery<ParseObject> query = ParseQuery.getQuery("BoulderingSession");

            query.orderByDescending("sessionID");
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        if (object != null){

                            ParseObject BoulderingSession = new ParseObject("BoulderingSession");
                            BoulderingSession.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException ex) {
                                    if (ex == null) {
                                        Toast.makeText(getApplication().getBaseContext(), "Session created", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplication().getBaseContext(), "Oops", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            Toast.makeText(getApplication().getBaseContext(), "Session created", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplication().getBaseContext(), "Oops", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        ParseQuery<ParseObject> query = ParseQuery.getQuery("SavedProblem");

        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null){

                    if (objects.size() > 0){

                        for (ParseObject object : objects){

                            problems.add(new Problem(object.getInt("problemGrade") ,object.getInt("problemAttempts") , object.getInt("problemNumber"), object.getBoolean("problemIsTraining"), object.getBoolean("problemIsTraining"), String.valueOf(object.getString("circuitName"))));
                        }

                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.addProblemFAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Log_session.this, AddProblem.class);
                startActivity(intent);

            }
        });

    }

}
