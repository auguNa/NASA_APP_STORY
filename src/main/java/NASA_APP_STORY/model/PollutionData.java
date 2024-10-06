package NASA_APP_STORY.model;

public class PollutionData {
    private String country;
    private double lat;
    private double lon;
    private double emissions; // Change to 'emissions'

    // Constructor
    public PollutionData(String country, double lat, double lon, double emissions) {
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.emissions = emissions; // Update parameter name
    }

    // Getters
    public String getCountry() {
        return country;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getEmissions() { // Update getter name
        return emissions;
    }
}
