package com.herms.hermscoder.service.integration;

import com.herms.hermscoder.exception.HermsCoderException;
import com.herms.hermscoder.model.dto.PostDTO;
import com.herms.hermscoder.model.dto.SharePostContentDTO;
import com.herms.hermscoder.model.integration.linkedin.*;
import com.herms.hermscoder.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.util.UriEncoder;

import java.util.Arrays;
import java.util.Map;

@Service
public class SharePostService {

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${linkedin.api.client.id}")
    private String clientId;

    @Value("${linkedin.api.client.secret}")
    private String clientSecret;

    public PostShareResponse sharePostOnLinkedin(Long postId, SharePostContentDTO sharePostContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(LinkedInConstants.ACCESS_TOKEN_URL)
                .queryParam("grant_type", LinkedInConstants.GRAND_TYPE)
                .queryParam("code", sharePostContent.getAuthorizationCode())
                .queryParam("redirect_uri", UriEncoder.encode(sharePostContent.getRedirectUri()))
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> responseEntity = restTemplate
                .exchange(builder.toUriString(), HttpMethod.GET, entity, Map.class);
        if(responseEntity != null && responseEntity.getBody() != null) {
            String accessToken = (String) responseEntity.getBody().get("access_token");


            headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "Bearer " + accessToken);
            headers.set("X-Restli-Protocol-Version", "2.0.0");
            entity = new HttpEntity<>(headers);

            responseEntity = restTemplate
                    .exchange(LinkedInConstants.PROFILE_INFORMATION_URL, HttpMethod.GET, entity, Map.class);
            if(responseEntity != null && responseEntity.getBody() != null) {
                String linkedInUserId = (String) responseEntity.getBody().get("id");

                PostDTO post = postService.findById(postId);

                HttpEntity<LinkedInPostInformationDTO> postEntity =
                        new HttpEntity<>(createNewLinkedInPostInformationObj(post, sharePostContent, linkedInUserId), headers);

                Map map = restTemplate.postForObject(LinkedInConstants.SHARE_API_URL, postEntity, Map.class);

                return new PostShareResponse(LinkedInConstants.POST_URL + map.get("id"));
            } else {
                throw new HermsCoderException("Unable to retrieve LinkedIn user data");
            }

        } else {
            throw new HermsCoderException("Unable to authenticate LinkedIn user");
        }
    }

    private LinkedInPostInformationDTO createNewLinkedInPostInformationObj(PostDTO post, SharePostContentDTO sharePostContent, String linkedInUserId) {
        LinkedInPostInformationDTO linkedInPostInformationDTO = new LinkedInPostInformationDTO();
        linkedInPostInformationDTO.setAuthorId(LinkedInConstants.USER_IDENTIFIER_ROOT + linkedInUserId);
        linkedInPostInformationDTO.setSpecificContent(
                new SpecificContent(
                        new ShareContent(
                                new ShareCommentary(sharePostContent.getCommentaryText()),
                                Arrays.asList(
                                        new LinkedinMedia(sharePostContent.getPostUrl(),
                                                Arrays.asList(new Thumbnails(post.getThumbnail().getUrl())),
                                                new Title(post.getTitle()))))));
        linkedInPostInformationDTO.setVisibility(new Visibility());
        return linkedInPostInformationDTO;
    }
}
