package xyz.hnnknk.arcturus.model;

public class Query {

    private String url;
    private String urlSuffix;
    private String dateTag;
    private String dateName;
    private String dateTextTag;
    private String titleTag;
    private String titleName;
    private String titleTextTag;
    private String isFullLinkToBody;
    private String bodyTag;
    private String bodyName;
    private String bodyTextTag;

    public Query() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }

    public String getDateTag() {
        return dateTag;
    }

    public void setDateTag(String dateTag) {
        this.dateTag = dateTag;
    }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public String getDateTextTag() {
        return dateTextTag;
    }

    public void setDateTextTag(String dateTextTag) {
        this.dateTextTag = dateTextTag;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public void setTitleTag(String titleTag) {
        this.titleTag = titleTag;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleTextTag() {
        return titleTextTag;
    }

    public void setTitleTextTag(String titleTextTag) {
        this.titleTextTag = titleTextTag;
    }

    public String getIsFullLinkToBody() {
        return isFullLinkToBody;
    }

    public void setIsFullLinkToBody(String isFullLinkToBody) {
        this.isFullLinkToBody = isFullLinkToBody;
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
        return "Query{" +
                "url='" + url + '\'' +
                ", urlSuffix='" + urlSuffix + '\'' +
                ", dateTag='" + dateTag + '\'' +
                ", dateName='" + dateName + '\'' +
                ", dateTextTag='" + dateTextTag + '\'' +
                ", titleTag='" + titleTag + '\'' +
                ", titleName='" + titleName + '\'' +
                ", titleTextTag='" + titleTextTag + '\'' +
                ", isFullLinkToBody=" + isFullLinkToBody +
                ", bodyTag='" + bodyTag + '\'' +
                ", bodyName='" + bodyName + '\'' +
                ", bodyTextTag='" + bodyTextTag + '\'' +
                '}';
    }
}
