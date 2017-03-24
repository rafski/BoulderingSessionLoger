package com.example.raf.boulderingsessionloger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class AddProblem extends AppCompatActivity {

    EditText problemGrade;
    EditText problemAttempts;
    Switch problemIsTraining;
    Switch problemIsCompleted;
    EditText problemCircuitName;
    EditText problemNumber;

    boolean isCompleted = true;
    boolean isTraining = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);



        problemGrade = (EditText) findViewById(R.id.problemAddGradeEditText);
        problemAttempts = (EditText) findViewById(R.id.problemAddAttemptsEditText);
        problemIsTraining = (Switch) findViewById(R.id.trainingAddProblemSwitch);
        problemIsCompleted = (Switch) findViewById(R.id.completedAddProblemSwitch);
        problemCircuitName = (EditText) findViewById(R.id.problemAddCircuitNameEditText);
        problemNumber = (EditText) findViewById(R.id.problemAddCircuitNumberEditText);

        problemIsCompleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    isCompleted = true;
                }else{
                    isCompleted = false;
                }

            }
        });

        problemIsTraining.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    isTraining = true;
                }else{
                    isTraining = false;
                }

            }
        });


        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.saveProblemFAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (!problemCircuitName.getText().toString().equals("")  && !problemNumber.getText().toString().equals("")
                        && !problemGrade.getText().toString().equals("")  && !problemAttempts.getText().toString().equals("")) {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("BoulderingSession");

                    query.orderByDescending("createdAt");
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                if (object != null){

                                    ParseObject SavedProblem = new ParseObject("SavedProblem");
                                    SavedProblem.put("circuitName", problemCircuitName.getText().toString());
                                    SavedProblem.put("problemNumber", Integer.parseInt(problemNumber.getText().toString()));
                                    SavedProblem.put("problemGrade", Integer.parseInt(problemGrade.getText().toString()));
                                    SavedProblem.put("problemAttempts", Integer.parseInt(problemAttempts.getText().toString()));
                                    SavedProblem.put("problemIsCompleted", isCompleted);
                                    SavedProblem.put("problemItTraining", isTraining);
                                    SavedProblem.put("parent", object);
                                    SavedProblem.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException ex) {
                                            if (ex == null) {
                                                Snackbar snackbar = Snackbar
                                                        .make(view, "Problem Saved", Snackbar.LENGTH_LONG);
                                                snackbar.setAction("CLOSE", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                    }
                                                }).show();
                                            } else {
                                                Snackbar snackbar = Snackbar
                                                        .make(view, "Saving problem failed", Snackbar.LENGTH_LONG);
                                                snackbar.setAction("CLOSE", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                    }
                                                }).show();
                                                ex.printStackTrace();
                                            }
                                        }
                                    });


                                }

                            } else {
                                Toast.makeText(getApplication().getBaseContext(), "Oops", Toast.LENGTH_LONG).show();
                            }
                        }
                    });



                }else{

                    Toast.makeText(getApplication().getBaseContext(), "Please fill in all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
