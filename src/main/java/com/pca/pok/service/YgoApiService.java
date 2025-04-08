package com.pca.pok.service;

import com.pca.pok.dto.YgoApiDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YgoApiService {
    private final RestTemplate restTemplate = new RestTemplate();

    public YgoApiDto fetchAllCards() {
        String url = "https://db.ygoprodeck.com/api/v7/cardinfo.php";
        return restTemplate.getForObject(url, YgoApiDto.class);
    }
}
