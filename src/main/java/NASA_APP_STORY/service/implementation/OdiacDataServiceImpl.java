package NASA_APP_STORY.service.implementation;

import NASA_APP_STORY.model.CO2DataPoint;
import NASA_APP_STORY.service.OdiacDataService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdiacDataServiceImpl implements OdiacDataService {
    private static final String STAC_API_URL = "https://earth.gov/ghgcenter/api/stac";
    private static final String COLLECTION_NAME = "odiac-ffco2-monthgrid-v2023";

    public JsonNode fetchOdiacData() {
        RestTemplate restTemplate = new RestTemplate();
        String url = STAC_API_URL + "/collections/" + COLLECTION_NAME + "/items?limit=300";
        return restTemplate.getForObject(url, JsonNode.class);
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
}