package fr.bouyaghir.trotticycle.entity;

public class MoyenDeTransport {
    int id;
    String Type;

    public MoyenDeTransport(int id, String type) {
        this.id = id;
        Type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
