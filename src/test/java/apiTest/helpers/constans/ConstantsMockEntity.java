package apiTest.helpers.constans;

import apiTest.helpers.json.AddressJson;
import apiTest.helpers.json.ContactJson;
import apiTest.helpers.json.EmailJson;
import apiTest.helpers.json.PhoneJson;

public abstract class ConstantsMockEntity {
    public static final AddressJson ADDRESS_JSON = new AddressJson(0, "582 Dickens Wall", "92594", "Gingerland", "Bangladesh");
    public static final AddressJson ADDRESS_EDIT_JSON = new AddressJson(0, "Landsberger Alee 222", "12398", "Berlin", "Germany");

    public static final PhoneJson PHONE_JSON = new PhoneJson(0, "007", "012345678901");
    public static final PhoneJson PHONE_EDIT_JSON = new PhoneJson(0, "999", "99999999999");

    public static final EmailJson EMAIL_JSON = new EmailJson(0, "user.email@gmail.com");
    public static final EmailJson EMAIL_EDIT_JSON = new EmailJson(0, "edit.EMAIL@googleMail.com");

    public static final ContactJson CONTACT_JSON = new ContactJson("Petr", "Ivanov", "Contact: Peter Ivanov");
    public static final ContactJson CONTACT_EDIT_JSON = new ContactJson("Vasja", "Pupkin", "Contact: Vasja Puplin, edited");
}
