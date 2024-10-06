package NASA_APP_STORY.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OdiacDataService {

    private static final Logger logger = LoggerFactory.getLogger(OdiacDataService.class);

    private static final String STAC_API_URL = "https://earth.gov/ghgcenter/api/stac";
    private static final String COLLECTION_NAME = "odiac-ffco2-monthgrid-v2023";

    public JsonNode fetchOdiacData() {
        // Create a RestTemplate object to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();
        String url = STAC_API_URL + "/collections/" + COLLECTION_NAME + "/items?limit=300";

        try {
            // Make the request and fetch the JSON data
            return restTemplate.getForObject(url, JsonNode.class);
        } catch (RestClientException e) {
            logger.error("Error fetching data from ODIAC API: " + e.getMessage());
            return null;
        }
    }
}
