package org.backend.skillywilly.model;

import lombok.Data;

import java.util.List;

/**
 * Represents the Location entity used to store geographic location and related metadata.
 * <p>
 * This class contains detailed information about a specific geographic point or place,
 * such as its identifiers, coordinates, category, type, importance, name, and display name.
 * Additionally, it includes a bounding box and a GeoJson structure for spatial representation.
 * <p>
 * Annotations:
 * - @Data: Automatically generates boilerplate code such as getters, setters, equals, and hashCode implementations.
 */
@Data
public class Location {
    /**
     * A unique identifier for a specific place or location.
     * <p>
     * This variable is part of the Location entity and serves as an internal identifier
     * for the location, typically used to reference geographic data in external systems
     * or databases.
     */
    private long place_id;
    /**
     * Represents the license associated with the location data.
     * <p>
     * Indicates the terms under which the location information is provided
     * and can be used. Typically, this field contains details about the
     * usage rights or reference to the license governing the geographic data.
     */
    private String licence;
    /**
     * Represents the type of the geographic object as defined by the OpenStreetMap (OSM) data model.
     * <p>
     * This variable is used to indicate whether the geographic object is a node, way, or relation.
     * It serves as a classification to provide context on the OSM feature type.
     */
    private String osm_type;
    /**
     * Represents the unique identifier for the associated OpenStreetMap (OSM) entity.
     * <p>
     * This variable is used to store the numeric identifier that uniquely represents
     * a specific geographic element in the OpenStreetMap database. It is integral
     * to identifying and mapping geographic locations or objects from the OSM infrastructure.
     */
    private long osm_id;
    /**
     * Represents the latitude coordinate of a geographic location.
     * <p>
     * This variable holds the latitude value as a string, which specifies
     * the north-south position of a point on the Earth's surface.
     * The value is expected to adhere to the format of latitude coordinates,
     * typically in decimal degrees.
     */
    private String lat;
    /**
     * Represents the longitude coordinate of a geographic location.
     * <p>
     * This value corresponds to the east-west position of the location
     * on the Earth's surface and is represented as a String.
     * It is typically used in conjunction with the latitude coordinate
     * to define a specific point on the globe.
     */
    private String lon;
    /**
     * Represents the category of a geographic location.
     * <p>
     * The category provides a classification or grouping of the location,
     * such as "amenity," "building," "natural," etc., offering insights
     * into the purpose or characteristics of the location.
     */
    private String category;
    /**
     * Specifies the classification or designation of the location.
     * <p>
     * This variable represents a textual description that categorizes a geographic location,
     * describing its specific type. Examples might include classifications such as "city",
     * "road", or "park".
     */
    private String type;
    /**
     * Represents the relative importance or significance of a geographic location within a hierarchy of places.
     * <p>
     * This variable is used to rank locations based on their level of prominence or granularity,
     * where lower values typically indicate broader areas (e.g., a country) and higher values denote more specific locations
     * (e.g., a street or building).
     */
    private int place_rank;
    /**
     * Indicates the importance or relevance of the geographic location.
     * <p>
     * This value is represented as a double, typically ranging between 0 and 1,
     * where higher values signify greater significance or relevance of the location.
     */
    private double importance;
    /**
     * Specifies the type of address associated with a geographic location.
     * <p>
     * This variable is used to categorize the nature of a location's address,
     * such as residential, commercial, or other classifications.
     */
    private String addresstype;
    /**
     * The name of the geographic location or place.
     * <p>
     * This field typically holds the official name or a recognized identifier
     * for the location. It provides a human-readable string to identify
     * the specific geographic entity represented by this object.
     */
    private String name;
    /**
     * Stores a human-readable, full descriptive name of a location.
     * <p>
     * This field provides a detailed textual representation of the location,
     * often including elements such as street name, city, state, and country,
     * which is suitable for displaying to users.
     */
    private String display_name;
    /**
     * Represents the bounding box coordinates for a specific geographic location.
     * <p>
     * The bounding box is represented as a list of strings, generally containing
     * four elements corresponding to the minimum latitude, maximum latitude,
     * minimum longitude, and maximum longitude. This information defines the
     * rectangular area enclosing a particular geographic point or region.
     */
    private List<String> boundingbox;
    /**
     * Represents spatial data in GeoJSON format for geographic locations.
     * <p>
     * This variable holds a `GeoJson` object that contains the type of geometry
     * (e.g., Point, LineString, Polygon) and corresponding coordinates.
     * <p>
     * It provides detailed geometric representation to encapsulate location-related
     * data, enabling better spatial analysis or mapping functionalities.
     */
    private GeoJson geojson;

    @Data
    public static class GeoJson {
        /**
         * Represents the type of the GeoJSON object.
         * <p>
         * This attribute specifies the nature of the GeoJSON geometry or feature,
         * typically indicating the type of geographic data it holds, such as "Point,"
         * "LineString," or "Polygon."
         * <p>
         * It is expected to follow the GeoJSON specification for type values.
         */
        private String type;
        /**
         * Represents the set of coordinates within a GeoJSON structure.
         * <p>
         * This variable is a three-level nested List of Double values, structured hierarchically
         * to represent geographical coordinate data. It is typically used in GeoJSON objects
         * to encode complex geometrical shapes such as polygons or multipolygons.
         * <p>
         * Structure:
         * - The outermost List represents a collection of shapes or geometries.
         * - The middle List represents a single shape, which may consist of multiple rings or boundaries.
         * - The innermost List contains pairs of Double values, representing latitude and longitude coordinates.
         * <p>
         * Notes:
         * - The ordering of coordinates follows the GeoJSON specification, where each pair is defined as [longitude, latitude].
         * - This variable is commonly used for representing spatial data in applications that handle geospatial information.
         */
        private List<List<List<Double>>> coordinates;
    }
}
