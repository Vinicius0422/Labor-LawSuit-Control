package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.PersonType;

import java.time.LocalDateTime;

public class DefendantRequestDto {

    private String defendantName;
    private String personType;
    private String cpfCnpj;
    private String address;
    private Long stateId;
    private Long cityId;
    private String neighborhood;
    private String cep;
    private String contact;
    private String email;

    public DefendantRequestDto() {
    }

    public DefendantRequestDto(String defendantName, String personType, String cpfCnpj, String address, Long stateId,
                               Long cityId, String neighborhood, String cep, String contact,
                               String email) {
        this.defendantName = defendantName;
        this.personType = personType;
        this.cpfCnpj = cpfCnpj;
        this.address = address;
        this.stateId = stateId;
        this.cityId = cityId;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.contact = contact;
        this.email = email;
    }

    public String getDefendantName() {
        return defendantName;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
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

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
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
}
