package com.example.raf.boulderingsessionloger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by raf on 20/03/2017.
 */

public class ProblemListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    CardView problemCard;
    TextView problemGrade;
    TextView problemAttemptsNumber;
    TextView problemIsTraining;
    TextView problemIsCompleted;
    TextView problemCircuitName;
    TextView problemCircuitNumber;

    final Context context;

    ProblemListViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        problemCard = (CardView)itemView.findViewById(R.id.problemCard);
        problemGrade = (TextView)itemView.findViewById(R.id.problemGradeTextView);
        problemAttemptsNumber = (TextView)itemView.findViewById(R.id.problemAttemptsTextView);
        problemIsTraining = (TextView)itemView.findViewById(R.id.problemIsTrainingTextView);
        problemIsCompleted = (TextView)itemView.findViewById(R.id.problemCompletedTextView);
        problemCircuitName = (TextView)itemView.findViewById(R.id.problemCircuitNameTextView);
        problemCircuitNumber = (TextView)itemView.findViewById(R.id.problemNumberTextView);

    }

    @Override
    public void onClick(View itemView) {
        Log.d(TAG, "onClick " + getAdapterPosition());

        //int isNew = 2;
        Intent intent = new Intent(context, AddProblem.class);
        //intent.putExtra("isNew", isNew);
        //intent.putExtra("sID", sessionID.getText());

        context.startActivity(intent);

    }
}