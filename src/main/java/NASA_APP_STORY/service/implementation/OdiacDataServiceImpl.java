package NASA_APP_STORY.service.implementation;

import NASA_APP_STORY.model.CO2DataPoint;
import NASA_APP_STORY.model.PollutionData;
import NASA_APP_STORY.service.OdiacDataService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdiacDataServiceImpl implements OdiacDataService {
    private static final String STAC_API_URL = "https://earth.gov/ghgcenter/api/stac";
    private static final String COLLECTION_NAME = "odiac-ffco2-monthgrid-v2023";

    private static final String OPENCAGE_API_URL = "https://api.opencagedata.com/geocode/v1/json";
    private static final String OPENCAGE_API_KEY = "edfb5d687ba94f8dbd84e37a61b3dafe";

    public JsonNode fetchOdiacData() {
        // Create a RestTemplate object to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();
        String url = STAC_API_URL + "/collections/" + COLLECTION_NAME + "/items?limit=300";

        try {
            // Make the request and fetch the JSON data
            return restTemplate.getForObject(url, JsonNode.class);
        } catch (RestClientException e) {
            return null;
        }
    }

    // Extract CO2 data points from the fetched data
    public List<CO2DataPoint> extractCO2DataForHeatMap(JsonNode jsonData) {
        List<CO2DataPoint> co2DataPoints = new ArrayList<>();
        JsonNode features = jsonData.get("features");

        for (JsonNode feature : features) {
            JsonNode bbox = feature.get("bbox");
            JsonNode rasterBands = feature.get("assets").get("co2-emissions").get("raster:bands").get(0);
            double co2Concentration = rasterBands.get("statistics").get("mean").asDouble();

            double minLon = bbox.get(0).asDouble();
            double minLat = bbox.get(1).asDouble();
            double maxLon = bbox.get(2).asDouble();
            double maxLat = bbox.get(3).asDouble();

            double centerLat = (minLat + maxLat) / 2;
            double centerLon = (minLon + maxLon) / 2;

            co2DataPoints.add(new CO2DataPoint(centerLat, centerLon, co2Concentration));
        }
        return co2DataPoints;
    }

    public List<PollutionData> extractPollutionData(JsonNode odiacData) {
        List<PollutionData> pollutionDataList = new ArrayList<>();
        if (odiacData.has("features")) {
            for (JsonNode feature : odiacData.get("features")) {
                JsonNode bbox = feature.get("bbox");
                if (bbox == null || !feature.has("assets") || !feature.get("assets").has("co2-emissions")) {
                    continue; // Skip if bbox or assets are missing
                }

                double minLat = bbox.get(1).asDouble();
                double maxLat = bbox.get(3).asDouble();
                double minLon = bbox.get(0).asDouble();
                double maxLon = bbox.get(2).asDouble();

                // Average latitude and longitude
                double lat = (minLat + maxLat) / 2;
                double lon = (minLon + maxLon) / 2;

                // Get CO2 concentration
                double emissions = feature.get("assets").get("co2-emissions")
                        .get("raster:bands").get(0).get("statistics").get("mean").asDouble();

                // Get country name using reverse geocoding
                String country = getCountry(lat, lon);
                // Create a new PollutionData object
                pollutionDataList.add(new PollutionData(country, lat, lon, emissions));
            }
        }
        return pollutionDataList;
    }


    // Function to get country name from lat/lon using OpenCage API
    private String getCountry(double lat, double lon) {
        if (lat == 0.0 && lon == 0.0) {
            return "Unknown Country";
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = OPENCAGE_API_URL + "?q=" + lat + "," + lon + "&key=" + OPENCAGE_API_KEY;

        try {
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);
            System.out.println("OpenCage API response: " + response);

            // Check if the response contains results
            if (response != null && response.has("results") && response.get("results").size() > 0) {
                JsonNode components = response.get("results").get(0).get("components");
                if (components != null && components.has("country")) {
                    return components.get("country").asText();  // Correctly retrieve the country
                }
            }
        } catch (Exception e) {
            System.out.println("Error calling OpenCage API: " + e.getMessage());
        }
        return "Unknown Country";  // Fallback if country not found
    }

}