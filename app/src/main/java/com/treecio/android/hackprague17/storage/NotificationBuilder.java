package com.treecio.android.hackprague17.storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;

import com.treecio.android.hackprague17.MainActivity;
import com.treecio.android.hackprague17.R;
import com.treecio.android.hackprague17.call.CallActionsActivity;
import com.treecio.android.hackprague17.model.Call;

import java.io.IOException;

import br.com.goncalves.pugnotification.notification.PugNotification;

/**
 * Create a notification after call ends.
 */

public class NotificationBuilder {

    private Context context;

    /**
     * Context should be from getBaseContext()
     */

    public NotificationBuilder(Context context) {
        this.context = context;
    }

    public void createNotification(Call call){

      PugNotification.with(context)
                .load()
                .smallIcon(R.drawable.pugnotification_ic_launcher)
                .autoCancel(false)
                .largeIcon(UriToBitmap(call.getPhoto()))
                .title("Take action")
                .message("Yo, check out the summary of your call with " + call.getCallerName() + " !")
                .click(CallActionsActivity.class) //Pali, sem pridaj aka aktivita sa ma spustit po tom co sa stlaci notifikacia
                .vibrate(new long[]{300, 300})
                .sound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .simple()
                .build();
    }

    private Bitmap UriToBitmap(Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
