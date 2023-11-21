package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitStatus;

public class LawsuitStatusResponseDto {

    private Long id;
    private String status;

    public LawsuitStatusResponseDto() {
    }

    public LawsuitStatusResponseDto(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
