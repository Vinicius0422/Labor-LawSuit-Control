package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant;

public class OnlyClaimantResponseDto {

    private Long claimantId;
    private String claimantName;
    private String rg;
    private String orgaoRg;
    private String cpf;
    private String address;
    private String city;
    private String neighborhood;
    private String uf;
    private String cep;

    public OnlyClaimantResponseDto() {
    }

    public OnlyClaimantResponseDto(Long claimantId, String claimantName, String rg, String orgaoRg, String cpf, String address,
                                   String city, String neighborhood, String uf, String cep) {
        this.claimantId = claimantId;
        this.claimantName = claimantName;
        this.rg = rg;
        this.orgaoRg = orgaoRg;
        this.cpf = cpf;
        this.address = address;
        this.city = city;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.cep = cep;
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
}
