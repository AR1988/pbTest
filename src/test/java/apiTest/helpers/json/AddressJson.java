package apiTest.helpers.json;

import static apiTest.helpers.constans.ConstantsStrings.*;

public class AddressJson extends JsonEntity {
    private int contactId;
    private int addressId;
    private String street;
    private String zip;
    private String city;
    private String country;

    public AddressJson(int contactId, String street, String zip, String city, String country) {
        this.contactId = contactId;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toJson() {
        return JSON_START +
                "street" +
                JSON_FIELD_VALUE_SEPARATOR +
                street +
                JSON_NEXT_FIELD +
                "zip" +
                JSON_FIELD_VALUE_SEPARATOR +
                zip +
                JSON_NEXT_FIELD +
                "city" +
                JSON_FIELD_VALUE_SEPARATOR +
                city +
                JSON_NEXT_FIELD +
                "country" +
                JSON_FIELD_VALUE_SEPARATOR +
                country +
                JSON_NEXT_FIELD +
                "contactId" +
                JSON_FIELD_VALUE_SEPARATOR +
                contactId +
                JSON_END;
    }

    @Override
    public String toEditJson() {
        return JSON_START +
                "street" +
                JSON_FIELD_VALUE_SEPARATOR +
                street +

                JSON_NEXT_FIELD +
                "zip" +
                JSON_FIELD_VALUE_SEPARATOR +
                zip +

                JSON_NEXT_FIELD +
                "city" +
                JSON_FIELD_VALUE_SEPARATOR +
                city +

                JSON_NEXT_FIELD +
                "id" +
                JSON_FIELD_VALUE_SEPARATOR +
                addressId +

                JSON_NEXT_FIELD +
                "country" +
                JSON_FIELD_VALUE_SEPARATOR +
                country +

                JSON_NEXT_FIELD +
                "contactId" +
                JSON_FIELD_VALUE_SEPARATOR +
                contactId +
                JSON_END;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getContactId() {
        return contactId;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
