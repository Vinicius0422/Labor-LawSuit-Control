package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney;

public class AttorneyResponseDto {

    private Long attorneyId;
    private String attorneyName;
    private String cpf;
    private String oabNumber;

    public AttorneyResponseDto() {
    }

    public AttorneyResponseDto(Long attorneyId, String attorneyName, String cpf, String oabNumber) {
        this.attorneyId = attorneyId;
        this.attorneyName = attorneyName;
        this.cpf = cpf;
        this.oabNumber = oabNumber;
    }

    public Long getId() {
        return attorneyId;
    }

    public void setId(Long attorneyId) {
        this.attorneyId = attorneyId;
    }

    public String getAttorneyName() {
        return attorneyName;
    }

    public void setAttorneyName(String attorneyName) {
        this.attorneyName = attorneyName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getOabNumber() {
        return oabNumber;
    }

    public void setOabNumber(String oabNumber) {
        this.oabNumber = oabNumber;
    }
}
