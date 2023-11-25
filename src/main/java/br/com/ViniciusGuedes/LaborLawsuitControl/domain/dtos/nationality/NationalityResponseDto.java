package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.nationality;

public class NationalityResponseDto {

    private Long nationalityId;
    private String nationality;

    public NationalityResponseDto() {
    }

    public NationalityResponseDto(Long nationalityId, String nationality) {
        this.nationalityId = nationalityId;
        this.nationality = nationality;
    }

    public Long getId() {
        return nationalityId;
    }

    public void setId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
