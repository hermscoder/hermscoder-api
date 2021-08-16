package com.herms.hermscoder.service;

import com.herms.hermscoder.exception.HermsCoderException;
import com.herms.hermscoder.model.dto.SharePostContentDTO;
import com.herms.hermscoder.model.entity.Post;
import com.herms.hermscoder.model.dto.PostDTO;
import com.herms.hermscoder.model.linkedin.*;
import com.herms.hermscoder.repository.PostRepository;
import com.herms.hermscoder.service.integration.SharePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements BlogService<PostDTO> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private SharePostService sharePostService;

    @Transactional
    @Override
    public List<PostDTO> findAll() {
        return postRepository.findAllOrderByDateDesc().stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PostDTO findById(Long id) {
        var post = postRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Post for id " + id + " not found!"));

        return new PostDTO(post);
    }

    @Transactional
    @Override
    public PostDTO save(PostDTO dto) {
        if(dto.getId() == null){
            var post = dto.toPost();
            post.setAuthor(authService.getCurrentUser().get());
            post.setDate(LocalDate.now());
            return new PostDTO(postRepository.save(post));
        } else {
            return update(dto);
        }
    }

    @Transactional
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

    public PostShareResponse sharePostOnLinkedin(Long postId, SharePostContentDTO sharePostContent) {
        return sharePostService.sharePostOnLinkedin(postId, sharePostContent);
    }
}
