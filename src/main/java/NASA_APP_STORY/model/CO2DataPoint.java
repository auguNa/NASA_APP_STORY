package NASA_APP_STORY.model;

public class CO2DataPoint {
    private double latitude;
    private double longitude;
    private double co2Concentration;

    public CO2DataPoint(double latitude, double longitude, double co2Concentration) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.co2Concentration = co2Concentration;
    }

    // Getters and setters
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getCo2Concentration() {
        return co2Concentration;
    }

    public void setCo2Concentration(double co2Concentration) {
        this.co2Concentration = co2Concentration;
    }
}
