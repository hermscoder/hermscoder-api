package com.herms.hermscoder.utils;

import com.herms.hermscoder.model.Post;
import com.herms.hermscoder.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyData {

    @Autowired
    PostRepository postRepository;

//    Commenting this because I just wanted to generate data once
//    @PostConstruct
    public void savePosts(){

        List<Post> postList = new ArrayList<>();
        var post1 = new Post();
        post1.setAuthor("Bruno Alexandre");
        post1.setDate(LocalDate.now());
        post1.setTitle("Docker");
        post1.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        var post2 = new Post();
        post2.setAuthor("Michelli Brito");
        post2.setDate(LocalDate.now());
        post2.setTitle("API REST");
        post2.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        postList.add(post1);
        postList.add(post2);

        for(Post post: postList){
            postRepository.save(post);
        }
    }
}