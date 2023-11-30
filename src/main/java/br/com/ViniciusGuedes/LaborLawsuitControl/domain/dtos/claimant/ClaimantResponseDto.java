package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.AccountType;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Lawsuit;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.MaritalStatus;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Nationality;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClaimantResponseDto {

    private Long claimantId;
    private String claimantName;
    private LocalDate birthDate;
    private String occupation;
    private String ctps;
    private String serieCtps;
    private String rg;
    private String orgaoRg;
    private String cpf;
    private String pis;
    private String address;
    private String city;
    private String neighborhood;
    private String uf;
    private String cep;
    private String bank;
    private String agency;
    private String operation;
    private String account;
    private String contact;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long nationalityId;
    private String nationality;
    private Long maritalStatusId;
    private String maritalStatus;
    private Long accountTypeId;
    private String accountType;
    private List<LawsuitClaimantResponseDto> lawsuits = new ArrayList<>();


    public ClaimantResponseDto() {
    }

    public ClaimantResponseDto(Long claimantId, String claimantName, LocalDate birthDate, String occupation, String ctps, String serieCtps, String rg, String orgaoRg,
                               String cpf, String pis, String address, String city, String neighborhood, String uf, String cep, String bank, String agency,
                               String operation, String account, String contact, String email, LocalDateTime createdAt, LocalDateTime updatedAt, Long nationalityId,
                               String nationality, Long maritalStatusId, String maritalStatus, Long accountTypeId, String accountType) {
        this.claimantId = claimantId;
        this.claimantName = claimantName;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.ctps = ctps;
        this.serieCtps = serieCtps;
        this.rg = rg;
        this.orgaoRg = orgaoRg;
        this.cpf = cpf;
        this.pis = pis;
        this.address = address;
        this.city = city;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.cep = cep;
        this.bank = bank;
        this.agency = agency;
        this.operation = operation;
        this.account = account;
        this.contact = contact;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nationalityId = nationalityId;
        this.nationality = nationality;
        this.maritalStatusId = maritalStatusId;
        this.maritalStatus = maritalStatus;
        this.accountTypeId = accountTypeId;
        this.accountType = accountType;
    }

    public ClaimantResponseDto(Long claimantId, String claimantName, LocalDate birthDate, String occupation, String ctps, String serieCtps, String rg, String orgaoRg,
                               String cpf, String pis, String address, String city, String neighborhood, String uf, String cep, String bank, String agency,
                               String operation, String account, String contact, String email, LocalDateTime createdAt, LocalDateTime updatedAt, Long nationalityId,
                               String nationality, Long maritalStatusId, String maritalStatus, Long accountTypeId, String accountType,
                               List<LawsuitClaimantResponseDto> lawsuits) {
        this.claimantId = claimantId;
        this.claimantName = claimantName;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.ctps = ctps;
        this.serieCtps = serieCtps;
        this.rg = rg;
        this.orgaoRg = orgaoRg;
        this.cpf = cpf;
        this.pis = pis;
        this.address = address;
        this.city = city;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.cep = cep;
        this.bank = bank;
        this.agency = agency;
        this.operation = operation;
        this.account = account;
        this.contact = contact;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nationalityId = nationalityId;
        this.nationality = nationality;
        this.maritalStatusId = maritalStatusId;
        this.maritalStatus = maritalStatus;
        this.accountTypeId = accountTypeId;
        this.accountType = accountType;
        this.lawsuits = lawsuits;
    }

//    public ClaimantResponseDto(String claimantName, LocalDate birthDate, String occupation, String ctps, String serieCtps, String rg, String orgaoRg, String cpf, String pis,
//                    String address, String city, String neighborhood, String uf, String cep, String bank, String agency, String operation, String account, String contact,
//                    String email, LocalDateTime createdAt, LocalDateTime updatedAt, Nationality nationalityId, MaritalStatus maritalStatusId, AccountType accountTypeId,
//                    List<Lawsuit> lawsuits) {
//        this.claimantName = claimantName;
//        this.birthDate = birthDate;
//        this.occupation = occupation;
//        this.ctps = ctps;
//        this.serieCtps = serieCtps;
//        this.rg = rg;
//        this.orgaoRg = orgaoRg;
//        this.cpf = cpf;
//        this.pis = pis;
//        this.address = address;
//        this.city = city;
//        this.neighborhood = neighborhood;
//        this.uf = uf;
//        this.cep = cep;
//        this.bank = bank;
//        this.agency = agency;
//        this.operation = operation;
//        this.account = account;
//        this.contact = contact;
//        this.email = email;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//        this.nationalityId = nationalityId.getId();
//        this.maritalStatusId = maritalStatusId;
//        this.accountTypeId = accountTypeId;
//        this.lawsuits = lawsuits;
//    }
//

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public String getSerieCtps() {
        return serieCtps;
    }

    public void setSerieCtps(String serieCtps) {
        this.serieCtps = serieCtps;
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

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Long getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Long maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<LawsuitClaimantResponseDto> getLawsuits() {
        return lawsuits;
    }

    public void setLawsuits(List<LawsuitClaimantResponseDto> lawsuits) {
        this.lawsuits = lawsuits;
    }
}
