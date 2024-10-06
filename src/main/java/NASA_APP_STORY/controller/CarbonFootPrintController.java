package NASA_APP_STORY.controller;

import NASA_APP_STORY.entity.CarbonFootPrint;
import NASA_APP_STORY.repository.UserRepository;
import NASA_APP_STORY.service.CarbonFootPrintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/carbon-footprint")

public class CarbonFootPrintController {
    @Autowired
    private CarbonFootPrintService carbonFootPrintService;

    @PostMapping("/log")
    public void logFootPrint(@RequestBody CarbonFootPrint carbonFootPrint) {
        carbonFootPrintService.logFootPrint(carbonFootPrint);
    }
}
