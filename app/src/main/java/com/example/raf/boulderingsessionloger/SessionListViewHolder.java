package com.example.raf.boulderingsessionloger;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by raf on 20/03/2017.
 */

public class SessionListViewHolder extends RecyclerView.ViewHolder {

    CardView sessionCard;
    TextView sendNumber;
    TextView sendAverage;

    SessionListViewHolder(View itemView) {
        super(itemView);
        sessionCard = (CardView)itemView.findViewById(R.id.sessionCard);
        sendNumber = (TextView)itemView.findViewById(R.id.numberOfSendsTextView);
        sendAverage = (TextView)itemView.findViewById(R.id.sendAverageTextView);

    }
}
