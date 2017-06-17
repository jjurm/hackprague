package com.treecio.android.hackprague17.call;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.treecio.android.hackprague17.R;
import com.treecio.android.hackprague17.model.Call;

/**
 * Created by Pali on 17.06.2017.
 */

public class CallActionsAdapter extends RecyclerView.Adapter<CallActionsAdapter.CardViewHolder> {

    private Call call;

    public CallActionsAdapter(Call call) {
        this.call = call;
        //TODO get call actions
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return call.getCallActions().size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        protected TextView actionText;
        protected ImageView actionIcon;
        protected CardView actionCard;

        public CardViewHolder(View v) {
            super(v);
            actionText =  (TextView) v.findViewById(R.id.actionText);
            actionIcon = (ImageView)  v.findViewById(R.id.photo);
            actionCard = (CardView) v.findViewById(R.id.actionCard);
        }
    }
}
