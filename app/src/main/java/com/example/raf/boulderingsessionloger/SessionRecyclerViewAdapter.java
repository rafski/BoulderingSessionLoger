package com.example.raf.boulderingsessionloger;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by raf on 17/03/2017.
 */

public class SessionRecyclerViewAdapter extends RecyclerView.Adapter<SessionListViewHolder>{



    List<Session> sessions;

    SessionRecyclerViewAdapter(List<Session> sessions){
        this.sessions = sessions;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public SessionListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.session_list_item, viewGroup, false);
        SessionListViewHolder sessionListViewHolder = new SessionListViewHolder(v);
        return sessionListViewHolder;
    }

    @Override
    public void onBindViewHolder(SessionListViewHolder holder, int i) {
        holder.sendNumber.setText(sessions.get(i).numberOfSends);
        holder.sendAverage.setText(sessions.get(i).averageSendDifficulty);
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

}


