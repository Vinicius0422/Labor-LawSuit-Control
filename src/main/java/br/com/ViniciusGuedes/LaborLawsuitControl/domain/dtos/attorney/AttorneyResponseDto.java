package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney;

public class AttorneyResponseDto {

    private Long id;
    private String attorneyName;
    private String cpf;
    private String oabNumber;

    public AttorneyResponseDto() {
    }

    public AttorneyResponseDto(Long id, String attorneyName, String cpf, String oabNumber) {
        this.id = id;
        this.attorneyName = attorneyName;
        this.cpf = cpf;
        this.oabNumber = oabNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
