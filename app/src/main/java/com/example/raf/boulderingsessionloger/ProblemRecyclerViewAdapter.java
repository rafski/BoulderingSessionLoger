package com.example.raf.boulderingsessionloger;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by raf on 20/03/2017.
 */

public class ProblemRecyclerViewAdapter extends RecyclerView.Adapter<ProblemViewHolder>{

    List<Problem> problems;

    ProblemRecyclerViewAdapter(List<Problem> problems){
        this.problems = problems;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ProblemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.problem_item, viewGroup, false);
        ProblemViewHolder problemViewHolder = new ProblemViewHolder(v);
        return problemViewHolder;
    }

    @Override
    public void onBindViewHolder(ProblemViewHolder holder, int i) {
        holder.problemGrade.setText(Integer.toString(problems.get(i).grade));
        holder.problemAttemptsNumber.setText(Integer.toString(problems.get(i).attemptsNumber));
        holder.problemIsTraining.setText(String.valueOf(problems.get(i).isTraining));
        holder.problemIsCompleted.setText(String.valueOf(problems.get(i).problemCompleted));
        holder.problemCircuitName.setText(problems.get(i).circuitName);
        holder.problemCircuitNumber.setText(Integer.toString(problems.get(i).problemNumber));

    }

    @Override
    public int getItemCount() {
        return problems.size();
    }

}