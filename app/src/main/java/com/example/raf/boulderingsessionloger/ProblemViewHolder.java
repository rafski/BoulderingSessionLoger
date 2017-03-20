package com.example.raf.boulderingsessionloger;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by raf on 20/03/2017.
 */

public class ProblemViewHolder extends RecyclerView.ViewHolder {

    CardView problemCard;
    TextView problemGrade;
    TextView problemAttemptsNumber;
    TextView problemIsTraining;
    TextView problemIsCompleted;
    TextView problemCircuitName;
    TextView problemCircuitNumber;

    ProblemViewHolder(View itemView) {
        super(itemView);
        problemCard = (CardView)itemView.findViewById(R.id.problemCard);
        problemGrade = (TextView)itemView.findViewById(R.id.problemGradeTextView);
        problemAttemptsNumber = (TextView)itemView.findViewById(R.id.problemAttemptsTextView);
        problemIsTraining = (TextView)itemView.findViewById(R.id.problemIsTrainingTextView);
        problemIsCompleted = (TextView)itemView.findViewById(R.id.problemCompletedTextView);
        problemCircuitName = (TextView)itemView.findViewById(R.id.circuitNameTextView);
        problemCircuitNumber = (TextView)itemView.findViewById(R.id.problemNumberTextView);


    }
}