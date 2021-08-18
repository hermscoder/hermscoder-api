package com.herms.hermscoder.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.herms.hermscoder.model.entity.Post;
import com.herms.hermscoder.utils.ConvertUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PostDTO {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private UserDTO author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String date;
    @NotBlank
    private String subTitle;
    @NotNull
    private Integer readingTime;
    @NotBlank
    private String text;

    private MediaDTO thumbnail;

    private String keyWords;

    public PostDTO(){}

    public PostDTO(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = new UserDTO(post.getAuthor());
        this.date = ConvertUtils.localDateToString(post.getDate());
        this.subTitle = post.getSubTitle();
        this.readingTime = post.getReadingTime();
        this.text = post.getText();
        this.thumbnail = post.getThumbnail() != null ? new MediaDTO(post.getThumbnail()) : null;
        this.keyWords = post.getKeyWords();
    }

    public Post toPost() {
        var post = new Post();
        post.setId(this.id);
        post.setTitle(this.title);
        post.setAuthor(this.author != null ? this.author.toUser() : null);
        post.setDate(ConvertUtils.stringToLocalDate(this.date));
        post.setReadingTime(this.readingTime);
        post.setSubTitle(this.subTitle);
        post.setText(this.text);
        post.setThumbnail(this.thumbnail != null ? this.thumbnail.toMedia() : null);
        post.setKeyWords(this.keyWords);
        return post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(Integer readingTime) {
        this.readingTime = readingTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MediaDTO getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MediaDTO thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
