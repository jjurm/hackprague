package com.treecio.android.hackprague17.call;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.treecio.android.hackprague17.Calendar.CalendarAction;
import com.treecio.android.hackprague17.R;
import com.treecio.android.hackprague17.model.Call;
import com.treecio.android.hackprague17.model.CallAction;
import com.treecio.android.hackprague17.sms.SMSBuilder;

import java.util.Date;

import ezvcard.VCardVersion;

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

        final CallAction action = call.getCallActions().get(position);


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
            case Contact:
                holder.actionIcon.setImageResource(R.drawable.ic_format_list_bulleted_black_24dp);
                break;
        }

        holder.actionText.setText(action.getDescription());

        holder.actionOpenButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switch (action.getType()) {
                    case Address:
                        String strUri = "geo:0,0?q=" + action.getDescription();
                        Uri uri = Uri.parse(strUri);

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(uri);
                        if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
                            v.getContext().startActivity(intent);
                        }
                        break;
                    case Meet:
                        CalendarAction ca = new CalendarAction(v.getContext());

                        ca.createPersonalEvent(action.getDescription(), action.getTitle(), new Date(), new Date());
                        break;
                    case Remind:
                        CalendarAction c = new CalendarAction(v.getContext());

                        c.createPersonalEvent(action.getDescription(), action.getTitle(), new Date(), new Date());

                        break;
                    case Contact:
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(action.getTitle()));
                        if (i.resolveActivity(v.getContext().getPackageManager()) != null) {
                            v.getContext().startActivity(i);
                        }
                        break;
                }
            }
        });

        holder.actionShareButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String sms;
                switch (action.getType()) {
                    case Address:
                        sms = new SMSBuilder(v.getContext()).addAddress(action.getDescription()).build(VCardVersion.V4_0);
                        SMSBuilder.SendSMS(call.getNumber(), sms);
                        break;
                    case Meet:
                        CalendarAction ca = new CalendarAction(v.getContext());

                        if(call.getId() == 4) {
                            ca.createSharedEvent(action.getDescription(), action.getTitle(), new Date(), new Date(), action.getContactinfo());
                        } else {
                            ca.createSharedEvent(action.getDescription(), action.getTitle(), new Date(), new Date(), "pavol.drotar3@gmail.com");
                        }
                        break;
                    case Remind:
                        sms = new SMSBuilder(v.getContext()).addNote(action.getDescription()).build(VCardVersion.V4_0);
                        SMSBuilder.SendSMS(call.getNumber(), sms);
                        break;
                    case Contact:
                        sms = new SMSBuilder(v.getContext()).addName(action.getTitle()).addPhone(action.getDescription()).build(VCardVersion.V4_0);
                        SMSBuilder.SendSMS(call.getNumber(), sms);
                        break;
                }
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
        protected Button actionOpenButton;
        protected Button actionShareButton;

        public CardViewHolder(View v) {
            super(v);
            actionText =  (TextView) v.findViewById(R.id.actionText);
            actionIcon = (ImageView)  v.findViewById(R.id.actionIcon);
            actionCard = (CardView) v.findViewById(R.id.actionCard);
            actionOpenButton = (Button) v.findViewById(R.id.actionOpenButton);
            actionShareButton = (Button) v.findViewById(R.id.actionShareButton);
        }
    }
}
