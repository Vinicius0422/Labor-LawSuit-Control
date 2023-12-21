package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitSomeFieldsResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.PersonType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DefendantResponseDto {

    private Long defendantId;
    private String defendantName;
    private PersonType personType;
    private String cpfCnpj;
    private String address;
    private String city;
    private String neighborhood;
    private String uf;
    private String cep;
    private String contact;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<LawsuitSomeFieldsResponseDto> lawsuits = new ArrayList<>();

    public DefendantResponseDto() {
    }

    public DefendantResponseDto(Long defendantId, String defendantName, PersonType personType,
                                String cpfCnpj, String address, String city, String neighborhood,
                                String uf, String cep, String contact, String email, LocalDateTime createdAt,
                                LocalDateTime updatedAt, List<LawsuitSomeFieldsResponseDto> lawsuits) {
        this.defendantId = defendantId;
        this.defendantName = defendantName;
        this.personType = personType;
        this.cpfCnpj = cpfCnpj;
        this.address = address;
        this.city = city;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.cep = cep;
        this.contact = contact;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lawsuits = lawsuits;
    }

    public DefendantResponseDto(Long defendantId, String defendantName, PersonType personType,
                                String cpfCnpj, String address, String city, String neighborhood, String uf,
                                String cep, String contact, String email, LocalDateTime createdAt,
                                LocalDateTime updatedAt) {
        this.defendantId = defendantId;
        this.defendantName = defendantName;
        this.personType = personType;
        this.cpfCnpj = cpfCnpj;
        this.address = address;
        this.city = city;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.cep = cep;
        this.contact = contact;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getDefendantId() {
        return defendantId;
    }

    public void setDefendantId(Long defendantId) {
        this.defendantId = defendantId;
    }

    public String getDefendantName() {
        return defendantName;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
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

    public List<LawsuitSomeFieldsResponseDto> getLawsuits() {
        return lawsuits;
    }

    public void setLawsuits(List<LawsuitSomeFieldsResponseDto> lawsuits) {
        this.lawsuits = lawsuits;
    }
}
