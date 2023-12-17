package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LawsuitRequestDto {

    private String lawsuitNumber;
    private String civilCourt;
    private String distributionDate;
    private Double valueCase;
    private Long lawsuitPhaseId;
    private Long lawsuitStatusId;
    private Long locationId;
    private String cpfClaimant;
    private List<String> cpfCnpjDefendants = new ArrayList<>();
    private List<Long> attorneys = new ArrayList<>();

    public LawsuitRequestDto() {
    }

    public LawsuitRequestDto(String lawsuitNumber, String civilCourt, String distributionDate, Double valueCase, Long lawsuitPhaseId,
                             Long lawsuitStatusId, Long locationId, String cpfClaimant, List<String> cpfCnpjDefendants, List<Long> attorneys) {
        this.lawsuitNumber = lawsuitNumber;
        this.civilCourt = civilCourt;
        this.distributionDate = distributionDate;
        this.valueCase = valueCase;
        this.lawsuitPhaseId = lawsuitPhaseId;
        this.lawsuitStatusId = lawsuitStatusId;
        this.locationId = locationId;
        this.cpfClaimant = cpfClaimant;
        this.cpfCnpjDefendants = cpfCnpjDefendants;
        this.attorneys = attorneys;
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

    public String getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(String distributionDate) {
        this.distributionDate = distributionDate;
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

    public Long getLawsuitStatusId() {
        return lawsuitStatusId;
    }

    public void setLawsuitStatusId(Long lawsuitStatusId) {
        this.lawsuitStatusId = lawsuitStatusId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getCpfClaimant() {
        return cpfClaimant;
    }

    public void setCpfClaimant(String cpfClaimant) {
        this.cpfClaimant = cpfClaimant;
    }

    public List<String> getCpfCnpjDefendants() {
        return cpfCnpjDefendants;
    }

    public void setCpfCnpjDefendants(List<String> cpfCnpjDefendants) {
        this.cpfCnpjDefendants = cpfCnpjDefendants;
    }

    public List<Long> getAttorneys() {
        return attorneys;
    }

    public void setAttorneys(List<Long> attorneys) {
        this.attorneys = attorneys;
    }
}
