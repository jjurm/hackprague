package com.treecio.android.hackprague17.sms;

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
 */

public class SMSBuilder {

    private VCard vCard;

    public SMSBuilder() {
        this.vCard = new VCard();
    }

    public SMSBuilder addName(String familyName, String givenName) {
        StructuredName n = new StructuredName();
        n.setFamily(familyName);
        n.setGiven(givenName);
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
}
