package cities.model;

/**
 * A city, including its name and location.
 */
public class City {
    private String name;
    private double latitude;
    private double longitude;

    /**
     * Constructs a new city.
     *
     * @param name the name of the city
     * @param latitude the latitude of the city
     * @param longitude the longitude of the city
     */
    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the name of this city.
     *
     * @return the name of this city
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the latitude of this city.
     *
     * @return the latitude of this city
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of this city.
     *
     * @return the longitude of this city
     */
    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("%s (lat. %f, long. %f)", name, latitude, longitude);
    }
}
