package NASA_APP_STORY.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import NASA_APP_STORY.service.OdiacDataService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class OdiacController {

    @Autowired
    private OdiacDataService odiacDataService;

    @GetMapping("/odiac-data")
    public JsonNode getOdiacData() {
        return odiacDataService.fetchOdiacData();
    }
}
