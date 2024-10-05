package NASA_APP_STORY;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppStatusController {

    // Create an endpoint to check the app status
    @GetMapping("/status")
    public String checkStatus() {
        return "The NASA-APP-STORY application is running!";
    }
}
