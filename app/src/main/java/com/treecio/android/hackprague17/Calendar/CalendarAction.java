package com.treecio.android.hackprague17.Calendar;


import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;

import java.util.Date;

public class CalendarAction {

    Context context;

    public CalendarAction(Context context) { this.context = context; }

    public void createPersonalEvent(String description, String title, Date beginDate, Date endDate) {

        Intent intent = getIntent(description, title, beginDate, endDate);

        context.startActivity(intent);

    }

    public void createSharedEvent(String description, String title, Date beginDate, Date endDate, String attendeeMail) {

        Intent intent = getIntent(description, title, beginDate, endDate);
        intent.putExtra(Intent.EXTRA_EMAIL, attendeeMail);

        context.startActivity(intent);

    }

    @NonNull
    private Intent getIntent(String description, String title, Date beginDate, Date endDate) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, title);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginDate.getTime());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate.getTime());
        intent.putExtra(CalendarContract.Events.ALL_DAY, false);
        intent.putExtra(CalendarContract.Events.STATUS, 1);
        intent.putExtra(CalendarContract.Events.VISIBLE, 0);
        intent.putExtra(CalendarContract.Events.HAS_ALARM, 1);
        return intent;
    }


}
