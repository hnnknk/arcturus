package xyz.hnnknk.arcturus.model;

public class RssQuery {

    private String url;
    private String bodyTag;
    private String bodyName;
    private String bodyTextTag;

    public RssQuery() {
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBodyTag() {
        return bodyTag;
    }

    public void setBodyTag(String bodyTag) {
        this.bodyTag = bodyTag;
    }

    public String getBodyName() {
        return bodyName;
    }

    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }

    public String getBodyTextTag() {
        return bodyTextTag;
    }

    public void setBodyTextTag(String bodyTextTag) {
        this.bodyTextTag = bodyTextTag;
    }

    @Override
    public String toString() {
        return "RssQuery{" +
                "url='" + url + '\'' +
                ", bodyTag='" + bodyTag + '\'' +
                ", bodyName='" + bodyName + '\'' +
                ", bodyTextTag='" + bodyTextTag + '\'' +
                '}';
    }
}
