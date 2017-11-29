package celinederoland.models;

public class Dragoon {

    private int id = 0;
    private String name;
    private transient int secret;

    public Dragoon() {
    }

    public Dragoon(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public Dragoon(String name) {

        this.name = name;
    }

    public int id() {

        return id;
    }

    public Dragoon id(int id) {

        this.id = id;
        return this;
    }

    public String name() {

        return name;
    }

    public Dragoon name(String name) {

        this.name = name;
        return this;
    }
}
