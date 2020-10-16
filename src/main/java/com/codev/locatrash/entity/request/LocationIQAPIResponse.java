package com.codev.locatrash.entity.request;

import lombok.Data;

import java.util.List;

@Data
public class LocationIQAPIResponse {
    private String place_id;
    private String licence;
    private String osm_type;
    private String osm_id;
    private List<String> boundingbox;
    private String lat;
    private String lon;
    private String display_name;
    private String clazz;
    private String type;
    private String importance;
    private String icon;
}
