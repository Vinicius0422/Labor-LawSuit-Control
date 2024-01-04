package br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "claimant")
public class Claimant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long claimantId;

    @Column(length = 150, nullable = false, name = "claimant_name")
    private String claimantName;

    @Column(name = "birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(length = 50)
    private String occupation;

    @Column(length = 9, nullable = false)
    private String ctps;

    @Column(length = 9, nullable = false, name = "serie_ctps")
    private String serieCtps;

    @Column(length = 7, nullable = false, unique = true)
    private String rg;

    @Column(length = 10, nullable = false, name = "orgao_rg")
    private String orgaoRg;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(length = 50)
    private String pis;

    @Column(length = 255, nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    private String neighborhood;

    @Column(length = 2, nullable = false)
    private String uf;

    @Column(length = 10, nullable = false)
    private String cep;

    @Column(length = 50)
    private String bank;

    @Column(length = 10)
    private String agency;

    @Column(length = 10)
    private String operation;

    @Column(length = 20)
    private String account;

    @Column(nullable = false)
    private String contact;

    @Column(length = 150)
    private String email;

    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private Nationality nationality;

    @ManyToOne
    @JoinColumn(name = "maritalstatus_id")
    private MaritalStatus maritalStatus;

    @ManyToOne
    @JoinColumn(name = "accounttype_id")
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Claimant() {
    }

    public Claimant(String claimantName, LocalDate birthDate, String occupation, String ctps, String serieCtps, String rg, String orgaoRg, String cpf, String pis,
                    String address, String neighborhood, String uf, String cep, String bank, String agency, String operation, String account, String contact,
                    String email, LocalDateTime createdAt, LocalDateTime updatedAt, Nationality nationality, MaritalStatus maritalStatus, AccountType accountType, State state, City city) {
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
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.accountType = accountType;
        this.state = state;
        this.city = city;
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

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Claimant)) return false;

        Claimant claimant = (Claimant) o;

        if (claimantId != null ? !claimantId.equals(claimant.claimantId) : claimant.claimantId != null) return false;
        if (claimantName != null ? !claimantName.equals(claimant.claimantName) : claimant.claimantName != null)
            return false;
        if (birthDate != null ? !birthDate.equals(claimant.birthDate) : claimant.birthDate != null) return false;
        if (occupation != null ? !occupation.equals(claimant.occupation) : claimant.occupation != null) return false;
        if (ctps != null ? !ctps.equals(claimant.ctps) : claimant.ctps != null) return false;
        if (serieCtps != null ? !serieCtps.equals(claimant.serieCtps) : claimant.serieCtps != null) return false;
        if (rg != null ? !rg.equals(claimant.rg) : claimant.rg != null) return false;
        if (orgaoRg != null ? !orgaoRg.equals(claimant.orgaoRg) : claimant.orgaoRg != null) return false;
        if (cpf != null ? !cpf.equals(claimant.cpf) : claimant.cpf != null) return false;
        if (pis != null ? !pis.equals(claimant.pis) : claimant.pis != null) return false;
        if (address != null ? !address.equals(claimant.address) : claimant.address != null) return false;
        if (neighborhood != null ? !neighborhood.equals(claimant.neighborhood) : claimant.neighborhood != null)
            return false;
        if (uf != null ? !uf.equals(claimant.uf) : claimant.uf != null) return false;
        if (cep != null ? !cep.equals(claimant.cep) : claimant.cep != null) return false;
        if (bank != null ? !bank.equals(claimant.bank) : claimant.bank != null) return false;
        if (agency != null ? !agency.equals(claimant.agency) : claimant.agency != null) return false;
        if (operation != null ? !operation.equals(claimant.operation) : claimant.operation != null) return false;
        if (account != null ? !account.equals(claimant.account) : claimant.account != null) return false;
        if (contact != null ? !contact.equals(claimant.contact) : claimant.contact != null) return false;
        if (email != null ? !email.equals(claimant.email) : claimant.email != null) return false;
        if (createdAt != null ? !createdAt.equals(claimant.createdAt) : claimant.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(claimant.updatedAt) : claimant.updatedAt != null) return false;
        if (nationality != null ? !nationality.equals(claimant.nationality) : claimant.nationality != null)
            return false;
        if (maritalStatus != null ? !maritalStatus.equals(claimant.maritalStatus) : claimant.maritalStatus != null)
            return false;
        if (accountType != null ? !accountType.equals(claimant.accountType) : claimant.accountType != null)
            return false;
        if (state != null ? !state.equals(claimant.state) : claimant.state != null) return false;
        return city != null ? city.equals(claimant.city) : claimant.city == null;
    }

    @Override
    public int hashCode() {
        int result = claimantId != null ? claimantId.hashCode() : 0;
        result = 31 * result + (claimantName != null ? claimantName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (occupation != null ? occupation.hashCode() : 0);
        result = 31 * result + (ctps != null ? ctps.hashCode() : 0);
        result = 31 * result + (serieCtps != null ? serieCtps.hashCode() : 0);
        result = 31 * result + (rg != null ? rg.hashCode() : 0);
        result = 31 * result + (orgaoRg != null ? orgaoRg.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (pis != null ? pis.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (neighborhood != null ? neighborhood.hashCode() : 0);
        result = 31 * result + (uf != null ? uf.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        result = 31 * result + (agency != null ? agency.hashCode() : 0);
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (maritalStatus != null ? maritalStatus.hashCode() : 0);
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
