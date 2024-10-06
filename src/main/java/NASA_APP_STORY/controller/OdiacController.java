package NASA_APP_STORY.controller;


import com.fasterxml.jackson.databind.JsonNode;
import NASA_APP_STORY.model.CO2DataPoint;
import NASA_APP_STORY.service.OdiacDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class OdiacController {

    private static final Logger logger = LoggerFactory.getLogger(OdiacController.class);

    @Autowired
    private OdiacDataService odiacDataService;

    @GetMapping("/odiac-data")
    public JsonNode getOdiacData() {
        logger.info("Fetching raw ODIAC data");
        return odiacDataService.fetchOdiacData();
    }

    @GetMapping("/heatmap-data")
    public List<CO2DataPoint> getHeatmapData() {
        logger.info("Fetching heatmap data");
        JsonNode data = odiacDataService.fetchOdiacData();
        List<CO2DataPoint> heatmapData = odiacDataService.extractCO2DataForHeatMap(data);
        logger.info("Returning heatmap data with {} points", heatmapData.size());
        return heatmapData;
    }
}