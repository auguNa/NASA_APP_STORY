package NASA_APP_STORY.service;

import NASA_APP_STORY.model.CO2DataPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OdiacDataService {

    private static final Logger logger = LoggerFactory.getLogger(OdiacDataService.class);

    private static final String STAC_API_URL = "https://earth.gov/ghgcenter/api/stac";
    private static final String COLLECTION_NAME = "odiac-ffco2-monthgrid-v2023";

    public JsonNode fetchOdiacData() {
        RestTemplate restTemplate = new RestTemplate();
        String url = STAC_API_URL + "/collections/" + COLLECTION_NAME + "/items?limit=300";
        logger.info("Fetching ODIAC data from API");
        return restTemplate.getForObject(url, JsonNode.class);
    }

/*     public List<CO2DataPoint> extractCO2DataForHeatMap(JsonNode data) {
        List<CO2DataPoint> co2DataPoints = new ArrayList<>();
        logger.info("Extracting CO2 data for heatmap");

        if (data.has("features")) {
            for (JsonNode feature : data.get("features")) {
                if (feature.has("assets") && feature.get("assets").has("co2-emissions")) {
                    CO2DataPoint dataPoint = new CO2DataPoint();
                    dataPoint.setId(feature.get("id").asText());

                    if (feature.has("bbox")) {
                        double[] bbox = new double[4];
                        for (int i = 0; i < 4; i++) {
                            bbox[i] = feature.get("bbox").get(i).asDouble();
                        }
                        dataPoint.setBbox(bbox);
                    }

                    co2DataPoints.add(dataPoint);
                }
            }
        }

        logger.info("Extracted {} data points", co2DataPoints.size());
        return co2DataPoints;
    } */
}