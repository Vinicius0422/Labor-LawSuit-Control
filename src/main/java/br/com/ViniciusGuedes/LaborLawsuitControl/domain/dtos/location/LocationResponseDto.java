package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.location;

public class LocationResponseDto {

    private Long locationId;
    private String location;

    public LocationResponseDto() {
    }

    public LocationResponseDto(Long locationId, String location) {
        this.locationId = locationId;
        this.location = location;
    }

    public Long getId() {
        return locationId;
    }

    public void setId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
