package xyz.hnnknk.arcturus.model;

public class Query {

    private long id;
    private String name;
    private String titleArg;
    private String bodyArg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitleArg() {
        return titleArg;
    }

    public void setTitleArg(String titleArg) {
        this.titleArg = titleArg;
    }

    public String getBodyArg() {
        return bodyArg;
    }

    public void setBodyArg(String bodyArg) {
        this.bodyArg = bodyArg;
    }

    @Override
    public String toString() {
        return "Query{" +
                "name='" + name + '\'' +
                ", titleArg='" + titleArg + '\'' +
                ", bodyArg='" + bodyArg + '\'' +
                '}';
    }
}
