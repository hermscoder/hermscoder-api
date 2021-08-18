package com.herms.hermscoder.service.integration;

import com.herms.hermscoder.model.entity.Media;
import com.herms.hermscoder.model.integration.pexels.PhotoPexels;
import com.herms.hermscoder.model.integration.pexels.SearchPexelsResponse;
import com.herms.hermscoder.repository.MediaRepository;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.util.UriEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class PexelsIntegrationService {

    private static final String PEXELS_SEARCH_QUERY_URL = "https://api.pexels.com/v1/search";
    private static final String PEXELS_PHOTO_DETAIL_URL = "https://api.pexels.com/v1/photos";

    private static final int PER_PAGE_PHOTOS = 100;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MediaRepository mediaRepository;

    @Value("${pexels.api.key}")
    private String pexelsApiKey;


    public Media getRandomImageBasedOnKeyWords(String keyWords){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", pexelsApiKey);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PEXELS_SEARCH_QUERY_URL)
                .queryParam("query", UriEncoder.encode(keyWords))
                .queryParam("per_page", PER_PAGE_PHOTOS);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<SearchPexelsResponse> responseEntity = restTemplate
                .exchange(builder.toUriString(), HttpMethod.GET, entity, SearchPexelsResponse.class);

        Media media = null;
        if(responseEntity.getBody() != null && !Collections.isEmpty(responseEntity.getBody().getPhotos())) {

            Random r = new Random();
            int randomIndex = r.nextInt(100) + 1;

            PhotoPexels choosenPhoto = responseEntity.getBody().getPhotos().get(randomIndex);

            builder = UriComponentsBuilder.fromHttpUrl(PEXELS_PHOTO_DETAIL_URL)
                    .pathSegment(choosenPhoto.getId().toString());

            ResponseEntity<PhotoPexels> photoPexelsResponse = restTemplate
                    .exchange(builder.toUriString(), HttpMethod.GET, entity, PhotoPexels.class);

            if(photoPexelsResponse.getBody() != null) {
                media = new Media("IMG", photoPexelsResponse.getBody().getSrc().getLandscape(), null);
                media = mediaRepository.save(media);
            }

        }
        return media;
    }
}
