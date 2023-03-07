package models;

import java.util.Objects;

public class Image {
    private Long id;
    private Long questionId;
    private String publicId;
    private String url;
    private Integer width;
    private Integer height;

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", publicId='" + publicId + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) &&
                Objects.equals(questionId, image.questionId) &&
                Objects.equals(publicId, image.publicId) &&
                Objects.equals(url, image.url) &&
                Objects.equals(width, image.width) &&
                Objects.equals(height, image.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, publicId, url, width, height);
    }
}
