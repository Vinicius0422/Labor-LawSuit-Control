package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.accountType.AccountTypeReponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.maritalStatus.MaritalStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.nationality.NationalityResponseDto;

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
    private NationalityResponseDto nationalityId;
    private MaritalStatusResponseDto maritalStatusId;
    private AccountTypeReponseDto accountTypeId;
    private List<LawsuitClaimantResponseDto> lawsuits = new ArrayList<>();

    public ClaimantResponseDto() {
    }

    public ClaimantResponseDto(Long claimantId, String claimantName, LocalDate birthDate, String occupation, String ctps, String serieCtps, String rg, String orgaoRg, String cpf,
                               String pis, String address, String city, String neighborhood, String uf, String cep, String bank, String agency, String operation,
                               String account, String contact, String email, LocalDateTime createdAt, LocalDateTime updatedAt, NationalityResponseDto nationalityId,
                               MaritalStatusResponseDto maritalStatusId, AccountTypeReponseDto accountTypeId, List<LawsuitClaimantResponseDto> lawsuits) {
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
        this.maritalStatusId = maritalStatusId;
        this.accountTypeId = accountTypeId;
        this.lawsuits = lawsuits;
    }

    public Long getId() {
        return claimantId;
    }

    public void setId(Long claimantId) {
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

    public NationalityResponseDto getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(NationalityResponseDto nationalityId) {
        this.nationalityId = nationalityId;
    }

    public MaritalStatusResponseDto getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(MaritalStatusResponseDto maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public AccountTypeReponseDto getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(AccountTypeReponseDto accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public List<LawsuitClaimantResponseDto> getLawsuits() {
        return lawsuits;
    }

    public void setLawsuits(List<LawsuitClaimantResponseDto> lawsuits) {
        this.lawsuits = lawsuits;
    }
}
