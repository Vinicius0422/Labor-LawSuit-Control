package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.maritalStatus;

public class MaritalStatusResponseDto {

    private Long maritalStatusId;
    private String maritalStatus;

    public MaritalStatusResponseDto() {
    }

    public MaritalStatusResponseDto(Long maritalStatusId, String maritalStatus) {
        this.maritalStatusId = maritalStatusId;
        this.maritalStatus = maritalStatus;
    }

    public Long getId() {
        return maritalStatusId;
    }

    public void setId(Long maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
