package com.example.raf.boulderingsessionloger;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Graphs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        final List<Integer> graphPoints = new ArrayList<>();


        ParseQuery<ParseObject> sessionAverageGrade = new ParseQuery<>("BoulderingSession");
        sessionAverageGrade.orderByDescending("createdAt");
        sessionAverageGrade.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> sessionsForGraphs, ParseException e) {
                if (e == null) {
                    if (sessionsForGraphs.size() > 0) {
                        int count = 0;
                        for (final ParseObject sessionForGraphs : sessionsForGraphs) {

                            int countNew = count++;
                            ParseQuery<ParseObject> problemsInSession = new ParseQuery<ParseObject>("SavedProblem");

                            problemsInSession.whereEqualTo("parent", sessionForGraphs);
                            final int finalCount = countNew;
                            problemsInSession.findInBackground(new FindCallback<ParseObject>() {

                                @Override
                                public void done(List<ParseObject> problems, ParseException e) {
                                    LineGraphSeries<DataPoint> seriesAverages = null;
                                    GraphView averageGradeGraph = null;
                                    LineGraphSeries<DataPoint> seriesAttempts = null;
                                    GraphView attemptsGraph = null;
                                    int averageGrade = 0;
                                    int totalAttempts = 0;
                                    if (e == null) {

                                        int totalGrade = 0;

                                        if (problems.size() > 0) {

                                            for (final ParseObject problem : problems) {

                                                totalGrade = totalGrade + problem.getInt("problemGrade");

                                            }
                                        }

                                        totalAttempts = 0;
                                        if (problems.size() > 0) {

                                            for (final ParseObject problem : problems) {

                                                totalAttempts = totalAttempts + problem.getInt("problemAttempts");

                                            }
                                        }

                                        if (problems.size() > 0) {
                                            averageGrade = totalGrade / problems.size();
                                        } else {
                                            averageGrade = 0;
                                        }

                                    }

                                    graphPoints.add(averageGrade);

                                    seriesAverages = new LineGraphSeries<>(new DataPoint[]{

                                            new DataPoint(finalCount, averageGrade)

                                    });

                                    seriesAttempts = new LineGraphSeries<>(new DataPoint[]{

                                            new DataPoint(finalCount, totalAttempts)

                                    });

                                    averageGradeGraph = (GraphView) findViewById(R.id.averageGraph);

                                    seriesAverages.setColor(Color.GREEN);
                                    seriesAverages.setDrawDataPoints(true);
                                    seriesAverages.setDataPointsRadius(10);
                                    seriesAverages.setThickness(8);
                                    seriesAverages.setAnimated(true);

                                    averageGradeGraph.getViewport().setXAxisBoundsManual(true);
                                    averageGradeGraph.getViewport().setMinX(0);
                                    averageGradeGraph.getViewport().setMaxX(10);

                                    averageGradeGraph.getViewport().setScrollableY(true);
                                    averageGradeGraph.getViewport().setScalableY(true);

                                    averageGradeGraph.addSeries(seriesAverages);

                                    attemptsGraph = (GraphView) findViewById(R.id.attemptsGraph);

                                    seriesAttempts.setColor(Color.BLACK);
                                    seriesAttempts.setDrawDataPoints(true);
                                    seriesAttempts.setDataPointsRadius(10);
                                    seriesAttempts.setThickness(5);
                                    seriesAttempts.setAnimated(true);
                                    seriesAttempts.setBackgroundColor(Color.BLACK);
                                    seriesAttempts.setDrawBackground(true);

                                    attemptsGraph.getViewport().setXAxisBoundsManual(true);
                                    attemptsGraph.getViewport().setMinX(0);
                                    attemptsGraph.getViewport().setMaxX(10);

                                    attemptsGraph.getViewport().setScrollableY(true);
                                    attemptsGraph.getViewport().setScalableY(true);

                                    attemptsGraph.addSeries(seriesAttempts);
                                }
                            });
                        }
                    }
                }
            }
        });
    }
}
