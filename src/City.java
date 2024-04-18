class City {
    private int cityId;
    private String cityName;
    private String cityType;

    // Constructors
    public City() {
    }

    public City(int cityId, String cityName, String cityType) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityType = cityType;
    }

    // Getters and setters
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityType() {
        return cityType;
    }

    public void setCityType(String cityType) {
        this.cityType = cityType;
    }

    // toString method
    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", cityType='" + cityType + '\'' +
                '}';
    }
}
