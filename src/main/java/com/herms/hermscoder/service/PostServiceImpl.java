package com.herms.hermscoder.service;

import com.herms.hermscoder.exception.HermsCoderException;
import com.herms.hermscoder.model.dto.MediaDTO;
import com.herms.hermscoder.model.dto.SharePostContentDTO;
import com.herms.hermscoder.model.entity.Media;
import com.herms.hermscoder.model.entity.Post;
import com.herms.hermscoder.model.dto.PostDTO;
import com.herms.hermscoder.model.integration.linkedin.*;
import com.herms.hermscoder.repository.PostRepository;
import com.herms.hermscoder.service.integration.PexelsIntegrationService;
import com.herms.hermscoder.service.integration.SharePostService;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
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

    @Autowired
    private PexelsIntegrationService pexelsIntegrationService;

    @Autowired
    private MediaService mediaService;

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
            if(post.getThumbnail() == null) {
                post.setThumbnail(pexelsIntegrationService.getRandomImageBasedOnKeyWords(post.getKeyWords()));
            }
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
            Optional<Post> entityOptional = postRepository.findById(dto.getId());
            if(entityOptional.isPresent()) {
                Post entity = entityOptional.get();

                post.setDate(entity.getDate());

                //If media changed, we need to remove the previous one from cloudinary
                if((post.getThumbnail() !=  null && entity.getThumbnail() != null)
                        && !post.getThumbnail().getId().equals(entity.getThumbnail().getId())) {
                    try {
                        //remove entity.getThumbnail() from cloudinary and repository
                        Media toBeDeletedMedia = entity.getThumbnail();

                        post = postRepository.saveAndFlush(post);

                        mediaService.deleteMedia(toBeDeletedMedia.getId());


                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new HermsCoderException(e.getMessage());
                    }
                } else {
                    post = postRepository.saveAndFlush(post);
                }

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
