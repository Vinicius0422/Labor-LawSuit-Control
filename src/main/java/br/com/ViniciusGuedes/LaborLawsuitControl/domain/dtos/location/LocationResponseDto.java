package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.location;

public class LocationResponseDto {

    private Long id;
    private String location;

    public LocationResponseDto() {
    }

    public LocationResponseDto(Long id, String location) {
        this.id = id;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
