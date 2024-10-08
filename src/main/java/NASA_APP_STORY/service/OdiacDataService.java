package NASA_APP_STORY.service;

import NASA_APP_STORY.model.CO2DataPoint;
import NASA_APP_STORY.model.PollutionData;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface OdiacDataService {
    JsonNode fetchOdiacData();

    List<CO2DataPoint> extractCO2DataForHeatMap(JsonNode jsonData);

    List<PollutionData> extractPollutionData(JsonNode odiacData);
}
