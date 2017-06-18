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
import com.treecio.android.hackprague17.HackyItem.TheaterAction;
import com.treecio.android.hackprague17.model.Call;
import com.treecio.android.hackprague17.model.CallAction;
import com.treecio.android.hackprague17.model.CardsFragment;
import com.treecio.android.hackprague17.storage.StoragePort;
import com.treecio.android.hackprague17.storage.StoredData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private StoragePort storagePort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storagePort = new StoragePort(this);

        loadFakeData();

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        refresh();
    }

    public void loadFakeData() {
        StoredData d = storagePort.getData();
        if (d.getLastUsedIndex() != 0) {
            return;
            /*d.getCalls().clear();
            d.setNextIndex(1);
            storagePort.saveData();*/
        }

        List<CallAction> l;
        Calendar cal;
        Call call;

        cal = Calendar.getInstance();
        cal.set(2017, 5, 18, 11, 21);
        l = new ArrayList<>();


        MeetAction meetAction = new MeetAction();
        meetAction.setDescription("Meet in park today with Pavol");
        meetAction.setTitle("Meeting");
        meetAction.setContactInfo("pavol.drotar3@gmail.com");
        l.add(meetAction);

        call = Call.createCall(this, d.obtainNextCallIndex(), "+421908822644", cal.getTime(), l);
        d.getCalls().put(call.getId(), call);

        cal = Calendar.getInstance();
        cal.set(2017, 5, 18, 11, 48);
        l = new ArrayList<>();

        AddressAction aa = new AddressAction();
        aa.setTitle("Address");
        aa.setDescription("Prague, Main Station");
        l.add(aa);

        l.add(new MeetAction());
        call = Call.createCall(this, d.obtainNextCallIndex(), "+421948117728", cal.getTime(), l);
        d.getCalls().put(call.getId(), call);

        cal = Calendar.getInstance();
        cal.set(2017, 5, 18, 12, 39);
        l = new ArrayList<>();

        RemindAction ra = new RemindAction();
        ra.setTitle("Reminder");
        ra.setDescription("Buy potatoes tomorrow");
        l.add(ra);

        call = Call.createCall(this, d.obtainNextCallIndex(), "+421910420214", cal.getTime(), l);
        d.getCalls().put(call.getId(), call);

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

        transaction.replace(R.id.fragmentHolder, n);
        transaction.commit();
    }
}
