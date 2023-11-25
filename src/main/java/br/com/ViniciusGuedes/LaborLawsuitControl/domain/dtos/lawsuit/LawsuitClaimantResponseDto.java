package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantForLawsuitResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitPhase.LawsuitPhaseResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitStatus.LawsuitStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.location.LocationResponseDto;
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
    private LawsuitPhaseResponseDto lawsuitPhaseId;
    private LawsuitStatusResponseDto statusId;
    private LocationResponseDto locationId;
    private List<ProgressResponseDto> progress = new ArrayList<>();
    private List<AnnotationResponseDto> annotations = new ArrayList<>();
    private List<DefendantForLawsuitResponseDto> defendants = new ArrayList<>();
    private List<AttorneyResponseDto> attorneys = new ArrayList<>();

    public LawsuitClaimantResponseDto() {
    }

    public LawsuitClaimantResponseDto(Long lawsuitId, String lawsuitNumber, String civilCourt, LocalDate distributionDate, Double valueCase, LocalDateTime createdAt,
                                      LocalDateTime updatedAt, LawsuitPhaseResponseDto lawsuitPhaseId, LawsuitStatusResponseDto statusId, LocationResponseDto locationId,
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
        this.statusId = statusId;
        this.locationId = locationId;
        this.progress = progress;
        this.annotations = annotations;
        this.defendants = defendants;
        this.attorneys = attorneys;
    }

    public Long getId() {
        return lawsuitId;
    }

    public void setId(Long lawsuitId) {
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

    public LawsuitPhaseResponseDto getLawsuitPhaseId() {
        return lawsuitPhaseId;
    }

    public void setLawsuitPhaseId(LawsuitPhaseResponseDto lawsuitPhaseId) {
        this.lawsuitPhaseId = lawsuitPhaseId;
    }

    public LawsuitStatusResponseDto getStatusId() {
        return statusId;
    }

    public void setStatusId(LawsuitStatusResponseDto statusId) {
        this.statusId = statusId;
    }

    public LocationResponseDto getLocationId() {
        return locationId;
    }

    public void setLocationId(LocationResponseDto locationId) {
        this.locationId = locationId;
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
