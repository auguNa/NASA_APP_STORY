package NASA_APP_STORY.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import NASA_APP_STORY.service.OdiacDataService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OdiacController {

    @Autowired
    private OdiacDataService odiacDataService;

    private static final String OPENCAGE_API_URL = "https://api.opencagedata.com/geocode/v1/json";
    private static final String OPENCAGE_API_KEY = "YOUR_API_KEY"; // Replace with your OpenCage API key

    @GetMapping("/odiac-data")
    public JsonNode getOdiacData() {
        return odiacDataService.fetchOdiacData();
    }

    @GetMapping("/pollution-data")
    public List<PollutionData> getPollutionData() {
        JsonNode odiacData = odiacDataService.fetchOdiacData();
        return extractPollutionData(odiacData);
    }

    private List<PollutionData> extractPollutionData(JsonNode odiacData) {
        List<PollutionData> pollutionDataList = new ArrayList<>();
        if (odiacData.has("features")) {
            for (JsonNode feature : odiacData.get("features")) {
                String country = "Unknown Country";
                if (feature.has("bbox")) {
                    double lat = (feature.get("bbox").get(1).asDouble() + feature.get("bbox").get(3).asDouble()) / 2; // Average latitude
                    double lon = (feature.get("bbox").get(0).asDouble() + feature.get("bbox").get(2).asDouble()) / 2; // Average longitude
                    double co2Concentration = feature.get("assets").get("co2-emissions").get("raster:bands").get(0).get("statistics").get("mean").asDouble();

                    // Perform reverse geocoding to get country name
                    country = getCountryFromCoordinates(lat, lon);

                    pollutionDataList.add(new PollutionData(country, lat, lon, co2Concentration));
                }
            }
        }
        return pollutionDataList;
    }

    // Function to get country name from lat/lon using OpenCage API
    private String getCountryFromCoordinates(double lat, double lon) {
        RestTemplate restTemplate = new RestTemplate();
        String url = OPENCAGE_API_URL + "?q=" + lat + "," + lon + "&key=" + OPENCAGE_API_KEY;
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        if (response != null && response.has("results") && response.get("results").size() > 0) {
            return response.get("results").get(0).get("components").get("country").asText();
        }
        return "Unknown Country";
    }

    class PollutionData {
        private String country;
        private double lat;
        private double lon;
        private double co2Concentration;

        public PollutionData(String country, double lat, double lon, double co2Concentration) {
            this.country = country;
            this.lat = lat;
            this.lon = lon;
            this.co2Concentration = co2Concentration;
        }

        public String getCountry() { return country; }
        public double getLat() { return lat; }
        public double getLon() { return lon; }
        public double getCo2Concentration() { return co2Concentration; }
    }
}
