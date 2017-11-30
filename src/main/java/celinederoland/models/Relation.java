package celinederoland.models;

public class Relation {

    protected Long id;
    protected String name;
    protected Entity dest;

    public Long id() {

        return this.id;
    }

    public Relation id(Long id) {

        this.id = id;
        return this;
    }

    public String name() {

        return this.name;
    }

    public Relation name(String name) {

        this.name = name;
        return this;
    }
}
