package com.example.smart_commute.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MapClient {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * origin: "经度,纬度"
     * destination: "经度,纬度"
     */
    public String getRoute(String origin, String destination) {

        String url = String.format(
            "https://router.project-osrm.org/route/v1/driving/%s;%s?overview=full&geometries=polyline",
            origin,
            destination
        );

        String json = restTemplate.getForObject(url, String.class);
        return json;
    }
}
