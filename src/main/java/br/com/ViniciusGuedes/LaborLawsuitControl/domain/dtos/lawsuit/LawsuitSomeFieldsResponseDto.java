package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit;

public class LawsuitSomeFieldsResponseDto {

    private Long lawsuitId;
    private String lawsuitNumber;
    private Double valueCase;
    private Long lawsuitPhaseId;
    private String phase;
    private Long lawsuitStatusId;
    private String status;
    private Long locationId;
    private String location;

    public LawsuitSomeFieldsResponseDto() {
    }

    public LawsuitSomeFieldsResponseDto(Long lawsuitId, String lawsuitNumber, Double valueCase, Long lawsuitPhaseId,
                                        String phase, Long lawsuitStatusId, String status, Long locationId, String location) {
        this.lawsuitId = lawsuitId;
        this.lawsuitNumber = lawsuitNumber;
        this.valueCase = valueCase;
        this.lawsuitPhaseId = lawsuitPhaseId;
        this.phase = phase;
        this.lawsuitStatusId = lawsuitStatusId;
        this.status = status;
        this.locationId = locationId;
        this.location = location;
    }

    public Long getLawsuitId() {
        return lawsuitId;
    }

    public void setLawsuitId(Long lawsuitId) {
        this.lawsuitId = lawsuitId;
    }

    public String getLawsuitNumber() {
        return lawsuitNumber;
    }

    public void setLawsuitNumber(String lawsuitNumber) {
        this.lawsuitNumber = lawsuitNumber;
    }

    public Double getValueCase() {
        return valueCase;
    }

    public void setValueCase(Double valueCase) {
        this.valueCase = valueCase;
    }

    public Long getLawsuitPhaseId() {
        return lawsuitPhaseId;
    }

    public void setLawsuitPhaseId(Long lawsuitPhaseId) {
        this.lawsuitPhaseId = lawsuitPhaseId;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Long getLawsuitStatusId() {
        return lawsuitStatusId;
    }

    public void setLawsuitStatusId(Long lawsuitStatusId) {
        this.lawsuitStatusId = lawsuitStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
