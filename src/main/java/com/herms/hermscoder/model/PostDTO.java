package com.herms.hermscoder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.herms.hermscoder.utils.ConvertUtils;

import javax.validation.constraints.NotBlank;

public class PostDTO {
    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String date;
    @NotBlank
    private String  text;

    public PostDTO(){}

    public PostDTO(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.date = ConvertUtils.localDateToString(post.getDate());
        this.text = post.getText();
    }

    public Post toPost() {
        var post = new Post();
        post.setId(this.id);
        post.setTitle(this.title);
        post.setAuthor(this.author);
        post.setDate(ConvertUtils.stringToLocalDate(this.date));
        post.setText(this.text);
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
