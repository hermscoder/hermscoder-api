package com.herms.hermscoder.service;

import com.herms.hermscoder.model.dto.UserDTO;
import com.herms.hermscoder.model.entity.Post;
import com.herms.hermscoder.model.dto.PostDTO;
import com.herms.hermscoder.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements BlogService<PostDTO> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthService authService;

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


}
