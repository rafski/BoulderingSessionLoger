package com.example.raf.boulderingsessionloger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Log_session extends AppCompatActivity {

    Date today;
    Date tomorrow;


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


        getDate();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("SavedProblem");

        query.whereGreaterThanOrEqualTo("createdAt", today);
        query.whereLessThan("createdAt", tomorrow);

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
