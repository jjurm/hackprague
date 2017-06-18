package com.treecio.android.hackprague17;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.treecio.android.hackprague17.model.Call;
import com.treecio.android.hackprague17.model.CardsFragment;
import com.treecio.android.hackprague17.storage.StoragePort;
import com.treecio.android.hackprague17.storage.StoredData;

import java.util.Date;

public class MainActivity extends FragmentActivity {

    private StoragePort storagePort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storagePort = new StoragePort(this);

        loadFakeData();

        setContentView(R.layout.activity_main);
    }

    public void loadFakeData() {
        StoredData d = storagePort.getData();

        Uri uri = Uri.parse("android.resource://com.treecio.android.hackprague17/drawable/ic_group_black_24dp");

        int id = d.obtainNextCallIndex();
        d.getCalls().put(id, new Call(id, "Pavol Drotár", uri, new Date()));

        id = d.obtainNextCallIndex();
        d.getCalls().put(id, new Call(id, "Juraj Mičko", uri, new Date()));

        id = d.obtainNextCallIndex();
        d.getCalls().put(id, new Call(id, "Michal Pándy", uri, new Date()));

        id = d.obtainNextCallIndex();
        d.getCalls().put(id, new Call(id, "Eduard Čuba", uri, new Date()));

        storagePort.saveData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:
                refresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void refresh() {
        CardsFragment n = new CardsFragment();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.cardsFragment, n);
        transaction.commit();
    }
}
