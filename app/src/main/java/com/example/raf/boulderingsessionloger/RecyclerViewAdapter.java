package com.example.raf.boulderingsessionloger;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by raf on 17/03/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SessionViewHolder>{

    public static class SessionViewHolder extends RecyclerView.ViewHolder {

        CardView sessionCard;
        TextView sendNumber;
        TextView sendAverage;

        SessionViewHolder(View itemView) {
            super(itemView);
            sessionCard = (CardView)itemView.findViewById(R.id.sessionCard);
            sendNumber = (TextView)itemView.findViewById(R.id.numberOfSendsTextView);
            sendAverage = (TextView)itemView.findViewById(R.id.sendAverageTextView);

        }
    }

    List<Session> sessions;

    RecyclerViewAdapter(List<Session> sessions){
        this.sessions = sessions;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.welcome_item, viewGroup, false);
        SessionViewHolder sessionViewHolder = new SessionViewHolder(v);
        return sessionViewHolder;
    }

    @Override
    public void onBindViewHolder(SessionViewHolder personViewHolder, int i) {
        personViewHolder.sendNumber.setText(sessions.get(i).numberOfSends);
        personViewHolder.sendAverage.setText(sessions.get(i).averageSendDifficulty);
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }



}


