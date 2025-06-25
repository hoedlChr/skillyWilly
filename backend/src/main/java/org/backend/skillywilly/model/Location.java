package org.backend.skillywilly.model;

import lombok.Data;

import java.util.List;

@Data
public class Location {
    private long place_id;
    private String licence;
    private String osm_type;
    private long osm_id;
    private String lat;
    private String lon;
    private String category;
    private String type;
    private int place_rank;
    private double importance;
    private String addresstype;
    private String name;
    private String display_name;
    private List<String> boundingbox;
    private GeoJson geojson;

    @Data
    public static class GeoJson {
        private String type;
        private Object coordinates;
    }
}
