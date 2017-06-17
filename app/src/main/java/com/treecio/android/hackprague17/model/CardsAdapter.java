package com.treecio.android.hackprague17.model;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.treecio.android.hackprague17.R;
import com.treecio.android.hackprague17.storage.StoragePort;
import com.treecio.android.hackprague17.storage.StoredData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Pali on 17.06.2017.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private StoragePort storagePort;
    private StoredData storedData;
    private List<Call> calls;

    public CardsAdapter(StoragePort storagePort) {
        this.storagePort = storagePort;
        this.calls = storagePort.getData().getCalls();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_card, parent, false);

        return new CardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Call call = calls.get(position);

        holder.callerName.setText(call.callerName);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date d = call.date;
        holder.date.setText(df.format(d));

        holder.photo.setImageURI(call.photo);
    }

    @Override
    public int getItemCount() {
        return calls.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        protected TextView callerName;
        protected TextView date;
        protected ImageView photo;
        protected CardView callCard;

        public CardViewHolder(View v) {
            super(v);
            callerName =  (TextView) v.findViewById(R.id.callerName);
            date = (TextView)  v.findViewById(R.id.date);
            photo = (ImageView)  v.findViewById(R.id.photo);
            callCard = (CardView) v.findViewById(R.id.card_view);
        }
    }
}