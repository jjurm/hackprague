package com.treecio.android.hackprague17.sms;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import java.io.File;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.Address;
import ezvcard.property.Email;
import ezvcard.property.StructuredName;
import ezvcard.property.Telephone;
import ezvcard.util.TelUri;

/**
 * Created by Pali on 18.06.2017.
 * Use String sms = new SMSBuilder().addName("Pali").addAddress("Whatever").addPhone("Whatever").addEmail("dsdkjk").build(VCardVersion.V4_0);
 */

public class SMSBuilder {

    private VCard vCard;
    Context context;

    public SMSBuilder(Context context) {
        this.vCard = new VCard();
        this.context = context;
    }

    public SMSBuilder addName(String name) {
        StructuredName n = new StructuredName();
        n.setGiven(name);
        vCard.setStructuredName(n);

        return this;
    }

    public SMSBuilder addAddress(String a) {
        Address address = new Address();
        address.setStreetAddress(a);
        vCard.addAddress(address);

        return this;
    }

    public SMSBuilder addPhone(String number) {
        TelUri uri = new TelUri.Builder(number).build();
        Telephone tel = new Telephone(uri);
        tel.getTypes().add(TelephoneType.WORK);
        vCard.addTelephoneNumber(tel);

        return this;
    }

    public SMSBuilder addEmail(String e) {
        Email email = new Email(e);
        email.getTypes().add(EmailType.WORK);
        vCard.addEmail(email);
        return this;
    }

    public String build(VCardVersion v) {
        return Ezvcard.write(vCard).version(v).go();
    }

    public void SendSMS(String number, String sms) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, sms, null, null);
    }

    public void SendSMSInApp(String number, String sms) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body", sms);
        sendIntent.setType("vnd.android-dir/mms-sms");
        context.startActivity(sendIntent);
    }

}
