package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.nationality;

public class NationalityResponseDto {

    private Long id;
    private String nationality;

    public NationalityResponseDto() {
    }

    public NationalityResponseDto(Long id, String nationality) {
        this.id = id;
        this.nationality = nationality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
