package com.treecio.android.hackprague17.model;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treecio.android.hackprague17.R;
import com.treecio.android.hackprague17.storage.StoragePort;


/**
 * Created by Pali on 17.06.2017.
 */
public class CardsFragment extends Fragment {

    public CardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cards, container, false);

        //create RecyclerView
        RecyclerView rView = (RecyclerView) v.findViewById(R.id.cardList);
        rView.setHasFixedSize(true);

        //Add adapter to RecyclerView
        StoragePort storagePort = new StoragePort(getActivity());
        CardsAdapter adapter = new CardsAdapter(storagePort, getActivity());
        rView.setAdapter(adapter);

        //Set LayoutManager of the RecyclerView
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rView.setLayoutManager(llm);

        return v;
    }

}
