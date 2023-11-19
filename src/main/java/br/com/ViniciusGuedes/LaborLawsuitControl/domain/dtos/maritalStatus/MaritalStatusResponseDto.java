package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.maritalStatus;

public class MaritalStatusResponseDto {

    private Long id;
    private String maritalStatus;

    public MaritalStatusResponseDto() {
    }

    public MaritalStatusResponseDto(Long id, String maritalStatus) {
        this.id = id;
        this.maritalStatus = maritalStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
