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

public class SessionListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CardView sessionCard;
    TextView sendNumber;
    TextView sendAverage;
    TextView sessionID;

    final Context context;

    SessionListViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(this);
        sessionCard = (CardView)itemView.findViewById(R.id.sessionCard);
        sendNumber = (TextView)itemView.findViewById(R.id.numberOfSendsTextView);
        sendAverage = (TextView)itemView.findViewById(R.id.sendAverageTextView);
        sessionID = (TextView)itemView.findViewById(R.id.sessionHistoryID);
    }

    @Override
    public void onClick(View itemView) {
        Log.d(TAG, "onClick " + getAdapterPosition());

        int isNew = 2;
        Intent intent = new Intent(context, Log_session.class);
        intent.putExtra("isNew", isNew);
        intent.putExtra("sID", sessionID.getText());

        context.startActivity(intent);

    }

}
