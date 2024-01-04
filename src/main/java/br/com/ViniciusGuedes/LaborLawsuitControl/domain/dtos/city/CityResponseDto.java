package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.city;

public class CityResponseDto {

    private Long cityId;
    private String name;

    public CityResponseDto() {
    }

    public CityResponseDto(Long cityId, String name) {
        this.cityId = cityId;
        this.name = name;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
