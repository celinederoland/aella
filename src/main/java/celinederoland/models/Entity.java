package celinederoland.models;

public class Entity {

    protected Long id;
    protected String name;
    protected String uri;
    protected String description;

    public Long id() {

        return this.id;
    }

    public Entity id(Long id) {

        this.id = id;
        return this;
    }

    public String name() {

        return this.name;
    }

    public Entity name(String name) {

        this.name = name;
        return this;
    }

    public String uri() {

        return this.uri;
    }

    public Entity uri(String uri) {

        this.uri = uri;
        return this;
    }

    public String description() {

        return this.description;
    }

    public Entity description(String description) {

        this.description = description;
        return this;
    }
}
