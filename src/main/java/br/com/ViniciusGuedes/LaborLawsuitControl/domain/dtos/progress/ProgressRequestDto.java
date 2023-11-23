package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress;

public class ProgressRequestDto {

    private String progressDate;
    private String expectedDate;
    private String expectedTime;
    private String description;
    private Long lawsuitPhaseId;
    private Long lawsuitId;

    public ProgressRequestDto() {
    }

    public ProgressRequestDto(String progressDate, String expectedDate, String expectedTime, String description, Long lawsuitPhaseId, Long lawsuitId) {
        this.progressDate = progressDate;
        this.expectedDate = expectedDate;
        this.expectedTime = expectedTime;
        this.description = description;
        this.lawsuitPhaseId = lawsuitPhaseId;
        this.lawsuitId = lawsuitId;
    }

    public String getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(String progressDate) {
        this.progressDate = progressDate;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLawsuitPhaseId() {
        return lawsuitPhaseId;
    }

    public void setLawsuitPhaseId(Long lawsuitPhaseId) {
        this.lawsuitPhaseId = lawsuitPhaseId;
    }

    public Long getLawsuitId() {
        return lawsuitId;
    }

    public void setLawsuitId(Long lawsuitId) {
        this.lawsuitId = lawsuitId;
    }
}
