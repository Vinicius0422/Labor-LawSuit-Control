package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LawsuitResponseDto {

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
    private Long claimantId;
    private String claimantName;
    private String rg;
    private String orgaoRg;
    private String cpf;
    private String address;
    private String stateName;
    private String cityName;
    private String neighborhood;
    private String uf;
    private String cep;
    private List<ProgressResponseDto> progress = new ArrayList<>();
    private List<AnnotationResponseDto> annotations = new ArrayList<>();
    private List<DefendantSomeFieldsResponseDto> defendants = new ArrayList<>();
    private List<AttorneyResponseDto> attorneys = new ArrayList<>();

    public LawsuitResponseDto() {
    }

    public LawsuitResponseDto(Long lawsuitId, String lawsuitNumber, String civilCourt, LocalDate distributionDate, Double valueCase, LocalDateTime createdAt,
                              LocalDateTime updatedAt, Long lawsuitPhaseId, String phase, Long lawsuitStatusId, String status, Long locationId, String location,
                              Long claimantId, String claimantName, String rg, String orgaoRg, String cpf, String address, String stateName, String cityName,
                              String neighborhood, String uf, String cep) {
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
        this.claimantId = claimantId;
        this.claimantName = claimantName;
        this.rg = rg;
        this.orgaoRg = orgaoRg;
        this.cpf = cpf;
        this.address = address;
        this.stateName = stateName;
        this.cityName = cityName;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.cep = cep;
    }

    public LawsuitResponseDto(Long lawsuitId, String lawsuitNumber, String civilCourt, LocalDate distributionDate, Double valueCase, LocalDateTime createdAt, LocalDateTime updatedAt,
                              Long lawsuitPhaseId, String phase, Long lawsuitStatusId, String status, Long locationId, String location, Long claimantId, String claimantName, String rg,
                              String orgaoRg, String cpf, String address, String stateName, String cityName, String neighborhood, String uf, String cep, List<ProgressResponseDto> progress,
                              List<AnnotationResponseDto> annotations, List<DefendantSomeFieldsResponseDto> defendants, List<AttorneyResponseDto> attorneys) {
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
        this.claimantId = claimantId;
        this.claimantName = claimantName;
        this.rg = rg;
        this.orgaoRg = orgaoRg;
        this.cpf = cpf;
        this.address = address;
        this.stateName = stateName;
        this.cityName = cityName;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.cep = cep;
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

    public Long getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(Long claimantId) {
        this.claimantId = claimantId;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoRg() {
        return orgaoRg;
    }

    public void setOrgaoRg(String orgaoRg) {
        this.orgaoRg = orgaoRg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public List<DefendantSomeFieldsResponseDto> getDefendants() {
        return defendants;
    }

    public void setDefendants(List<DefendantSomeFieldsResponseDto> defendants) {
        this.defendants = defendants;
    }

    public List<AttorneyResponseDto> getAttorneys() {
        return attorneys;
    }

    public void setAttorneys(List<AttorneyResponseDto> attorneys) {
        this.attorneys = attorneys;
    }
}
