package NASA_APP_STORY.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class OdiacData {

    @JsonProperty("id")
    private String id;

    @JsonProperty("bbox")
    private List<Double> bbox;

    @JsonProperty("properties")
    private OdiacProperties properties;

    @JsonProperty("assets")
    private OdiacAssets assets;

    @Data
    public static class OdiacProperties {
        @JsonProperty("start_datetime")
        private String startDateTime;

        @JsonProperty("end_datetime")
        private String endDateTime;

    }

    @Data
    public static class OdiacAssets {
        @JsonProperty("co2-emissions")
        private Co2Emissions co2Emissions;

        @Data
        public static class Co2Emissions {
            @JsonProperty("href")
            private String href;

            @JsonProperty("title")
            private String title;

            @JsonProperty("description")
            private String description;
        }
    }
}
