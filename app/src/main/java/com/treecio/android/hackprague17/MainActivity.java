package com.treecio.android.hackprague17;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.treecio.android.hackprague17.HackyItem.AddressAction;
import com.treecio.android.hackprague17.HackyItem.ContactAction;
import com.treecio.android.hackprague17.HackyItem.MeetAction;
import com.treecio.android.hackprague17.HackyItem.RemindAction;
import com.treecio.android.hackprague17.model.Call;
import com.treecio.android.hackprague17.model.CallAction;
import com.treecio.android.hackprague17.model.CardsFragment;
import com.treecio.android.hackprague17.storage.StoragePort;
import com.treecio.android.hackprague17.storage.StoredData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private StoragePort storagePort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storagePort = new StoragePort(this);

        loadFakeData();
        refresh();
    }

    public void loadFakeData() {
        StoredData d = storagePort.getData();

        Uri uri = Uri.parse("android.resource://com.treecio.android.hackprague17/drawable/ic_group_black_24dp");

        List<CallAction> l = new ArrayList<>();
        l.add(new MeetAction());
        l.add(new AddressAction());
        l.add(new ContactAction());
        l.add(new RemindAction());

        int id = d.obtainNextCallIndex();
        Call c = new Call(id, "Pavol Drotár", uri, new Date());
        c.setCallActions(l);
        d.getCalls().put(id, c);

        id = d.obtainNextCallIndex();
        c = new Call(id, "Juraj Mičko", uri, new Date());
        c.setCallActions(l);
        d.getCalls().put(id, c);

        id = d.obtainNextCallIndex();
        c = new Call(id, "Michal Pándy", uri, new Date());
        c.setCallActions(l);
        d.getCalls().put(id, c);

        id = d.obtainNextCallIndex();
        c = new Call(id, "Eduard Čuba", uri, new Date());
        c.setCallActions(l);
        d.getCalls().put(id, c);

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
