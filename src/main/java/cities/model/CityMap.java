package cities.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Graph representation of a set of cities, which is built from data in
 * a provided CSV file.
 */
public class CityMap {
    /**
     * The path to the file containing city data.
     */
    public static final String CITY_DATA_FILE = "uscities.csv";

    private static final int NUM_DATA_FIELDS = 4;
    private static final int CITY_NAME_FIELD = 0;
    private static final int POPULATION_FIELD = 1; // for optional milestone
    private static final int LATITUDE_FIELD = 2;
    private static final int LONGITUDE_FIELD = 3;
    private static final int NUM_CITIES = 5; // file has 100

    private static final int EARTH_RADIUS_IN_MILES = 3963;

    private final Graph<City> graph = new Graph<>();

    /**
     * Constructs a graph representation of a set of cities.
     *
     * @throws IOException if the city data cannot be read
     */
    public CityMap() throws IOException {
        makeGraph();
    }

    /**
     * Gets the graph representation of the cities, where each city is anode,
     * and there are edges between each pair of cities weighted with the
     * distance between them.
     *
     * @return a graph representation of cities and the distances between them
     */
    public final Graph<City> getGraph() {
        return graph;
    }

    private static double distanceInMiles(City city1, City city2) {
        // Distance, d = 3963.0 * arccos[(sin(lat1) * sin(lat2)) + cos(lat1) * cos(lat2) * cos(long2 – long1)]
        return EARTH_RADIUS_IN_MILES
                * Math.acos(
                        (Math.sin(Math.toRadians(city1.getLatitude())) * Math.sin(Math.toRadians(city2.getLatitude())))
                + Math.cos(Math.toRadians(city1.getLatitude()))
                * Math.cos(Math.toRadians(city2.getLatitude()))
                * Math.cos(Math.toRadians(city2.getLongitude() - city1.getLongitude())));
    }

    private List<String> readDataFromResource() throws IOException {
        try (InputStream resource = this.getClass().getClassLoader().getResourceAsStream(CITY_DATA_FILE)) {
            if (resource == null) {
                throw new IOException("Unable to read resource file " + CITY_DATA_FILE);
            }
            return new BufferedReader(new InputStreamReader(resource,
                    StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
        }
    }

    private void makeGraph() throws IOException {
        List<String> lines = readDataFromResource();
        int numCities = 0;
        for (String line : lines) {
            if (numCities++ >= NUM_CITIES) {
                break;
            }
            String[] fields = line.split(",");
            if (fields.length == NUM_DATA_FIELDS) {
                City city = new City(fields[CITY_NAME_FIELD],
                        Double.parseDouble(fields[LATITUDE_FIELD]),
                        Double.parseDouble(fields[LONGITUDE_FIELD]));
                graph.addNode(city);
            } else {
                throw new IOException("Unable to parse line: " + line);
            }
        }

        // Complete this method by adding edges connecting each pair of nodes.
        // The weight of the edge should be the distance in miles between their
        // cities. Use the provided helper method. Because the edges are
        // undirected, you should add only one edge between each pair of nodes.
        // Do not make an edge between a node and itself.
    }
}
