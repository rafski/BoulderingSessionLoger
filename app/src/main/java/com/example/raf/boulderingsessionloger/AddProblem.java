package com.example.raf.boulderingsessionloger;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddProblem extends AppCompatActivity {

    Switch problemIsTraining;
    Switch problemIsCompleted;
    EditText problemCircuitName;
    EditText problemNumber;

    boolean isCompleted = true;
    boolean isTraining = false;

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(AddProblem.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);

        setupUI(findViewById(R.id.addProblemParent));

        ParseQuery<ParseObject> circuitNameQuery = ParseQuery.getQuery("SavedProblem");

        circuitNameQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d("objects", "Retrieved " + objects.size());

                    ParseObject[] data = objects.toArray(new ParseObject[objects.size()]);
                    String[] strings = new String[objects.size()];
                    for (int i = 0; i < data.length; i++) {
                        strings[i] = data[i].getString("circuitName");
                    }

                    Set<String> set = new HashSet<String>(Arrays.asList(strings));

                    strings = set.toArray(new String[set.size()]);
                    // Test to see if it was correctly printing out the array I wanted.
                    //System.out.println(Arrays.toString(strings));
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, strings);
                    AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.problemAddCircuitNameEditText);
                    if(objects.size() < 100) textView.setThreshold(1);
                    else textView.setThreshold(2);
                    textView.setAdapter(adapter);
                } else {
                    Log.d("users", "Error: " + e.getMessage());
                }
            }
        });



        final NumberPicker gradeNumberPicker = (NumberPicker) findViewById(R.id.grade_number_picker);
        final NumberPicker attemptsNumberPicker = (NumberPicker) findViewById(R.id.attempts_number_picker);


        // set divider color
        gradeNumberPicker.setDividerColor(ContextCompat.getColor(this, R.color.colorPrimary));
        gradeNumberPicker.setDividerColorResource(R.color.colorPrimary);

        // set formatter
        gradeNumberPicker.setFormatter(getString(R.string.number_picker_formatter));
        gradeNumberPicker.setFormatter(R.string.number_picker_formatter);

        // set selected text color
        gradeNumberPicker.setSelectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        gradeNumberPicker.setSelectedTextColorResource(R.color.colorPrimary);

        // set text color
        gradeNumberPicker.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        gradeNumberPicker.setTextColorResource(R.color.colorAccent);

        // set text size
        gradeNumberPicker.setTextSize(getResources().getDimension(R.dimen.text_size));
        gradeNumberPicker.setTextSize(R.dimen.text_size);

        // set typeface
        gradeNumberPicker.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        gradeNumberPicker.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        gradeNumberPicker.setTypeface(getString(R.string.roboto_light));
        gradeNumberPicker.setTypeface(R.string.roboto_light, Typeface.NORMAL);
        gradeNumberPicker.setTypeface(R.string.roboto_light);

        // set divider color
        attemptsNumberPicker.setDividerColor(ContextCompat.getColor(this, R.color.colorPrimary));
        attemptsNumberPicker.setDividerColorResource(R.color.colorPrimary);

        // set formatter
        attemptsNumberPicker.setFormatter(getString(R.string.attemps_picker_formatter));
        attemptsNumberPicker.setFormatter(R.string.attemps_picker_formatter);

        // set selected text color
        attemptsNumberPicker.setSelectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        attemptsNumberPicker.setSelectedTextColorResource(R.color.colorPrimary);

        // set text color
        attemptsNumberPicker.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        attemptsNumberPicker.setTextColorResource(R.color.colorAccent);

        // set text size
        attemptsNumberPicker.setTextSize(getResources().getDimension(R.dimen.text_size));
        attemptsNumberPicker.setTextSize(R.dimen.text_size);

        // set typeface
        attemptsNumberPicker.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        attemptsNumberPicker.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        attemptsNumberPicker.setTypeface(getString(R.string.roboto_light));
        attemptsNumberPicker.setTypeface(R.string.roboto_light, Typeface.NORMAL);
        attemptsNumberPicker.setTypeface(R.string.roboto_light);


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

                if (!problemCircuitName.getText().toString().equals("")  && !problemNumber.getText().toString().equals("")) {

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
                                    SavedProblem.put("problemGrade", Integer.parseInt(String.valueOf(gradeNumberPicker.getValue())));
                                    SavedProblem.put("problemAttempts", Integer.parseInt(String.valueOf(attemptsNumberPicker.getValue())));
                                    SavedProblem.put("problemIsCompleted", isCompleted);
                                    SavedProblem.put("problemItTraining", isTraining);
                                    SavedProblem.put("parent", object);
                                    SavedProblem.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException ex) {
                                            if (ex == null) {

                                                problemCircuitName.setText(null);
                                                problemNumber.setText(null);
                                                gradeNumberPicker.setValue(3);
                                                attemptsNumberPicker.setValue(1);
                                                problemIsCompleted.setChecked(true);
                                                problemIsTraining.setChecked(true);
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
