package apiTest.helpers.json;

public abstract class JsonEntity {

    public abstract String toJson();

    public abstract String toEditJson();

    public abstract void setContactId(int contactId);
}
