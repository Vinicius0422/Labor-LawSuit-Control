package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitStatus;

public class LawsuitStatusResponseDto {

    private Long lawsuitStatusId;
    private String status;

    public LawsuitStatusResponseDto() {
    }

    public LawsuitStatusResponseDto(Long lawsuitStatusId, String status) {
        this.lawsuitStatusId = lawsuitStatusId;
        this.status = status;
    }

    public Long getId() {
        return lawsuitStatusId;
    }

    public void setId(Long lawsuitStatusId) {
        this.lawsuitStatusId = lawsuitStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
