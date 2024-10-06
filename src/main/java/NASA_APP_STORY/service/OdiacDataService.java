package NASA_APP_STORY.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class OdiacDataService {

    private static final String STAC_API_URL = "https://earth.gov/ghgcenter/api/stac";
    private static final String COLLECTION_NAME = "odiac-ffco2-monthgrid-v2023";

    public JsonNode fetchOdiacData() {
        // Create a RestTemplate object to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        // Create the URL for fetching the collection items
        String url = STAC_API_URL + "/collections/" + COLLECTION_NAME + "/items?limit=300";

        // Make the request and fetch the JSON data
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        return response;
    }
}
