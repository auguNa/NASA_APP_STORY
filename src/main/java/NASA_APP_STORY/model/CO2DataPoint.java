package NASA_APP_STORY.model;

public class CO2DataPoint {
    private String id;
    private double[] bbox;  // Bounding box coordinates [minLon, minLat, maxLon, maxLat]

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double[] getBbox() {
        return bbox;
    }

    public void setBbox(double[] bbox) {
        this.bbox = bbox;
    }
}
