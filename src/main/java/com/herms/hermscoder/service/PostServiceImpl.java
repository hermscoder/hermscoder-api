package com.herms.hermscoder.service;

import com.herms.hermscoder.model.Post;
import com.herms.hermscoder.model.PostDTO;
import com.herms.hermscoder.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements BlogService<PostDTO> {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostDTO> findAll() {
        return postRepository.findAll().stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @Override
    public PostDTO findById(Long id) {
        PostDTO dto = null;

        var post = postRepository.findById(id).orElse(null);

        if(post != null) {
            dto = new PostDTO(post);
        }
        return dto;
    }

    @Override
    public PostDTO save(PostDTO dto) {
        var post = dto.toPost();

        if(post.getId() == null){
            post.setDate(LocalDate.now());
        } else {
            update(dto);
        }

        return new PostDTO(postRepository.save(post));
    }

    @Override
    public PostDTO update(PostDTO dto) {
        var post = dto.toPost();
        if(dto.getId() == null){
            save(dto);
        } else {
            Optional<Post> entity = postRepository.findById(dto.getId());
            if(entity.isPresent()) {
                post.setDate(entity.get().getDate());
            }else {
                throw new EntityNotFoundException("Post with id " + dto.getId() + " not found.");
            }
        }

        return new PostDTO(postRepository.save(post));
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }


}
