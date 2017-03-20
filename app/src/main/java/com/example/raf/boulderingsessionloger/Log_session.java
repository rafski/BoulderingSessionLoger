package com.example.raf.boulderingsessionloger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Log_session extends AppCompatActivity {

    private List<Problem> problems;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_session);

        recyclerView = (RecyclerView) findViewById(R.id.problemRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ProblemRecyclerViewAdapter adapter = new ProblemRecyclerViewAdapter(problems);
        recyclerView.setAdapter(adapter);

        initializeData();
        initializeAdapter();

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.addProblemFAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Log_session.this, AddProblem.class);
                startActivity(intent);

            }
        });
    }


    private void initializeData() {
        problems = new ArrayList<>();
        problems.add(new Problem(1 ,2 , 3, true, false, "Blue"));
        problems.add(new Problem(1 ,5 , 3, true, false, "Red"));
        problems.add(new Problem(1 ,6 , 3, true, true, "Circuit Board Red"));
        problems.add(new Problem(1 ,7 , 3, true, false, "Green"));
        problems.add(new Problem(1 ,2 , 8, false, false, "Blue"));
        problems.add(new Problem(1 ,2 , 1, true, false, "Hendrix"));
        problems.add(new Problem(1 ,2 , 2, true, false, "Blue"));
        problems.add(new Problem(5 ,2 , 3, true, false, "Comp Wall"));

    }

    private void initializeAdapter() {
        ProblemRecyclerViewAdapter adapter = new ProblemRecyclerViewAdapter(problems);
        recyclerView.setAdapter(adapter);
    }

}
