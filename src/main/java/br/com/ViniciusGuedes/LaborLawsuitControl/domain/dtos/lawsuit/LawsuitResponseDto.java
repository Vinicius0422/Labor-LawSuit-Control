package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantForLawsuitResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.LawsuitPhase;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.LawsuitStatus;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Location;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LawsuitResponseDto {

    private Long id;
    private String lawsuitNumber;
    private String civilCourt;
    private LocalDate distributionDate;
    private Double valueCase;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LawsuitPhase lawsuitPhaseId;
    private LawsuitStatus statusId;
    private Location locationId;
    private OnlyClaimantResponseDto claimant;
    private List<ProgressResponseDto> progress = new ArrayList<>();
    private List<AnnotationResponseDto> annotations = new ArrayList<>();
    private List<DefendantForLawsuitResponseDto> defendants = new ArrayList<>();
    private List<AttorneyResponseDto> attorneys = new ArrayList<>();

    public LawsuitResponseDto() {
    }

    public LawsuitResponseDto(Long id, String lawsuitNumber, String civilCourt, LocalDate distributionDate, Double valueCase, LocalDateTime createdAt,
                              LocalDateTime updatedAt, LawsuitPhase lawsuitPhaseId, LawsuitStatus statusId, Location locationId, OnlyClaimantResponseDto claimant,
                              List<ProgressResponseDto> progress, List<AnnotationResponseDto> annotations, List<DefendantForLawsuitResponseDto> defendants,
                              List<AttorneyResponseDto> attorneys) {
        this.id = id;
        this.lawsuitNumber = lawsuitNumber;
        this.civilCourt = civilCourt;
        this.distributionDate = distributionDate;
        this.valueCase = valueCase;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lawsuitPhaseId = lawsuitPhaseId;
        this.statusId = statusId;
        this.locationId = locationId;
        this.claimant = claimant;
        this.progress = progress;
        this.annotations = annotations;
        this.defendants = defendants;
        this.attorneys = attorneys;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LawsuitPhase getLawsuitPhaseId() {
        return lawsuitPhaseId;
    }

    public void setLawsuitPhaseId(LawsuitPhase lawsuitPhaseId) {
        this.lawsuitPhaseId = lawsuitPhaseId;
    }

    public LawsuitStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(LawsuitStatus statusId) {
        this.statusId = statusId;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public OnlyClaimantResponseDto getClaimant() {
        return claimant;
    }

    public void setClaimant(OnlyClaimantResponseDto claimant) {
        this.claimant = claimant;
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
