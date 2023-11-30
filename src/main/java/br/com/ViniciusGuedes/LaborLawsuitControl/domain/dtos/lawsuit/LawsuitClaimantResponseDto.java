package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantForLawsuitResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LawsuitClaimantResponseDto {

    private Long lawsuitId;
    private String lawsuitNumber;
    private String civilCourt;
    private LocalDate distributionDate;
    private Double valueCase;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long lawsuitPhaseId;
    private String phase;
    private Long lawsuitStatusId;
    private String status;
    private Long locationId;
    private String location;
    private List<ProgressResponseDto> progress = new ArrayList<>();
    private List<AnnotationResponseDto> annotations = new ArrayList<>();
    private List<DefendantForLawsuitResponseDto> defendants = new ArrayList<>();
    private List<AttorneyResponseDto> attorneys = new ArrayList<>();

    public LawsuitClaimantResponseDto() {
    }

    public LawsuitClaimantResponseDto(Long lawsuitId, String lawsuitNumber, String civilCourt, LocalDate distributionDate, Double valueCase, LocalDateTime createdAt,
                                      LocalDateTime updatedAt, Long lawsuitPhaseId, String phase, Long lawsuitStatusId, String status, Long locationId, String location) {
        this.lawsuitId = lawsuitId;
        this.lawsuitNumber = lawsuitNumber;
        this.civilCourt = civilCourt;
        this.distributionDate = distributionDate;
        this.valueCase = valueCase;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lawsuitPhaseId = lawsuitPhaseId;
        this.phase = phase;
        this.lawsuitStatusId = lawsuitStatusId;
        this.status = status;
        this.locationId = locationId;
        this.location = location;
    }

    public LawsuitClaimantResponseDto(Long lawsuitId, String lawsuitNumber, String civilCourt, LocalDate distributionDate, Double valueCase, LocalDateTime createdAt,
                                      LocalDateTime updatedAt, Long lawsuitPhaseId, String phase, Long lawsuitStatusId, String status, Long locationId, String location,
                                      List<ProgressResponseDto> progress, List<AnnotationResponseDto> annotations, List<DefendantForLawsuitResponseDto> defendants,
                                      List<AttorneyResponseDto> attorneys) {
        this.lawsuitId = lawsuitId;
        this.lawsuitNumber = lawsuitNumber;
        this.civilCourt = civilCourt;
        this.distributionDate = distributionDate;
        this.valueCase = valueCase;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lawsuitPhaseId = lawsuitPhaseId;
        this.phase = phase;
        this.lawsuitStatusId = lawsuitStatusId;
        this.status = status;
        this.locationId = locationId;
        this.location = location;
        this.progress = progress;
        this.annotations = annotations;
        this.defendants = defendants;
        this.attorneys = attorneys;
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

    public String getCivilCourt() {
        return civilCourt;
    }

    public void setCivilCourt(String civilCourt) {
        this.civilCourt = civilCourt;
    }

    public LocalDate getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(LocalDate distributionDate) {
        this.distributionDate = distributionDate;
    }

    public Double getValueCase() {
        return valueCase;
    }

    public void setValueCase(Double valueCase) {
        this.valueCase = valueCase;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    public List<ProgressResponseDto> getProgress() {
        return progress;
    }

    public void setProgress(List<ProgressResponseDto> progress) {
        this.progress = progress;
    }

    public List<AnnotationResponseDto> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationResponseDto> annotations) {
        this.annotations = annotations;
    }

    public List<DefendantForLawsuitResponseDto> getDefendants() {
        return defendants;
    }

    public void setDefendants(List<DefendantForLawsuitResponseDto> defendants) {
        this.defendants = defendants;
    }

    public List<AttorneyResponseDto> getAttorneys() {
        return attorneys;
    }

    public void setAttorneys(List<AttorneyResponseDto> attorneys) {
        this.attorneys = attorneys;
    }
}
