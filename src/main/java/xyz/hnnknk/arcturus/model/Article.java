package xyz.hnnknk.arcturus.model;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private String date;

    @Lob
    @Column(name = "body",length = Integer.MAX_VALUE)
    private String body;

    @Column(name = "url")
    private String url;

    public Article() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
