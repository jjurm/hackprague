package com.treecio.android.hackprague17.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.treecio.android.hackprague17.R;
import com.treecio.android.hackprague17.call.CallActionsActivity;
import com.treecio.android.hackprague17.storage.StoragePort;
import com.treecio.android.hackprague17.storage.StoredData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Pali on 17.06.2017.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private StoragePort storagePort;
    private StoredData storedData;
    private HashMap<Integer, Call> callsMap;
    private List<Call> calls;

    private Context context;

    public CardsAdapter(StoragePort storagePort, Context context) {
        this.storagePort = storagePort;
        this.context = context;
        this.callsMap = storagePort.getData().getCalls();
        this.calls = sortByDate(callsMap);
    }

    List<Call> sortByDate(HashMap<Integer, Call> callsMap) {
        List<Map.Entry<Integer, Call>> entries = new ArrayList<Map.Entry<Integer, Call>>(callsMap.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Call>>() {

            @Override
            public int compare(Map.Entry<Integer, Call> o1, Map.Entry<Integer, Call> o2) {
                if(o2.getValue().date.after(o2.getValue().date)) return -1;
                else return 1;
            }
        });
        List<Call> c = new ArrayList<Call>();
        for(Map.Entry<Integer, Call> e : entries) {
            c.add(e.getValue());
        }
        return c;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_card, parent, false);

        return new CardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        final Call call = calls.get(position);

        holder.callerName.setText(call.callerName);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date d = call.date;
        holder.date.setText(df.format(d));

        holder.photo.setImageURI(call.photo);

        holder.callCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CallActionsActivity.class);
                intent.putExtra("id", call.getId());
                context.startActivity(intent);
            }
        });
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
