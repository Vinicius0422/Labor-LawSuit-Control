package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant;

public class OnlyClaimantResponseDto {

    private Long claimantId;
    private String claimantName;
    private String rg;
    private String orgaoRg;
    private String cpf;
    private String address;
    private String stateName;
    private String cityName;
    private String neighborhood;
    private String uf;
    private String cep;

    public OnlyClaimantResponseDto() {
    }

    public OnlyClaimantResponseDto(Long claimantId, String claimantName, String rg, String orgaoRg, String cpf, String address, String stateName,
                                   String cityName, String neighborhood, String uf, String cep) {
        this.claimantId = claimantId;
        this.claimantName = claimantName;
        this.rg = rg;
        this.orgaoRg = orgaoRg;
        this.cpf = cpf;
        this.address = address;
        this.stateName = stateName;
        this.cityName = cityName;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.cep = cep;
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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
