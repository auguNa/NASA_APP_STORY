package NASA_APP_STORY.controller;

import NASA_APP_STORY.model.CO2DataPoint;
import NASA_APP_STORY.model.PollutionData;
import NASA_APP_STORY.service.OdiacDataService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OdiacController {

    @Autowired
    private OdiacDataService odiacDataService;


    // Endpoint for getting raw ODIAC data
    @GetMapping("/odiac-data")
    public JsonNode getOdiacData() {
        return odiacDataService.fetchOdiacData();
    }

    // Endpoint for getting processed data points for heatmap
    @GetMapping("/heatmap-data")
    public List<CO2DataPoint> getHeatmapData() {
        JsonNode data = odiacDataService.fetchOdiacData();
        return odiacDataService.extractCO2DataForHeatMap(data);
    }

    @GetMapping("/pollution-data")
    public List<PollutionData> getPollutionData() {
        JsonNode odiacData = odiacDataService.fetchOdiacData();
        return odiacDataService.extractPollutionData(odiacData);
    }
}
