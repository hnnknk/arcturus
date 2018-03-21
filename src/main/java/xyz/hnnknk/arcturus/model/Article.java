package xyz.hnnknk.arcturus.model;

public class Article {

    private String title;
    private String body;
    private String url;

    public Article() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
