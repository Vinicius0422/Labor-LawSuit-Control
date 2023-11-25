package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.PersonType;

public class DefendantForLawsuitResponseDto {

    private Long defendantId;
    private String defendantName;
    private PersonType personType;
    private String cpfCnpj;

    public DefendantForLawsuitResponseDto() {
    }

    public DefendantForLawsuitResponseDto(Long defendantId, String defendantName, PersonType personType, String cpfCnpj) {
        this.defendantId = defendantId;
        this.defendantName = defendantName;
        this.personType = personType;
        this.cpfCnpj = cpfCnpj;
    }

    public Long getId() {
        return defendantId;
    }

    public void setId(Long defendantId) {
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
}
