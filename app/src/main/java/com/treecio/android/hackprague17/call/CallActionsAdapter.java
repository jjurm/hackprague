package com.treecio.android.hackprague17.call;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.treecio.android.hackprague17.R;
import com.treecio.android.hackprague17.model.Call;
import com.treecio.android.hackprague17.model.CallAction;

/**
 * Created by Pali on 17.06.2017.
 */

public class CallActionsAdapter extends RecyclerView.Adapter<CallActionsAdapter.CardViewHolder> {

    private Call call;

    public CallActionsAdapter(Call call) {
        this.call = call;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.action_card, parent, false);
        return new CardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {

        CallAction action = call.getCallActions().get(position);


        switch (action.getType()) {
            case Address:
                    holder.actionIcon.setImageResource(R.drawable.ic_place_black_24dp);
                break;
            case Meet:
                holder.actionIcon.setImageResource(R.drawable.ic_group_black_24dp);
                break;
            case Remind:
                holder.actionIcon.setImageResource(R.drawable.ic_notifications_black_24dp);
                break;
            case Log:
                holder.actionIcon.setImageResource(R.drawable.ic_format_list_bulleted_black_24dp);
                break;
        }

        holder.actionText.setText(action.getDescription());

        holder.actionCard.setOnClickListener(new View.OnClickListener () {

            @Override
            public void onClick(View v) {
                //TODO send to app
            }
        });
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
