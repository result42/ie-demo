package com.antsyferov.iedemo.service;

import com.antsyferov.iedemo.model.Dummy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class DummyProviderImpl implements DummyProvider {

    private static final String JSON_URL = "https://jsonplaceholder.typicode.com/posts";

    @Override
    public List<Dummy> fetchReversed() {
        List<Dummy> result = new RestTemplate()
            .exchange(
                JSON_URL,
                HttpMethod.GET,
                RequestEntity.EMPTY,
                new ParameterizedTypeReference<List<Dummy>>() {
                }
            ).getBody();

        Collections.reverse(result);
        return result;
    }
}
