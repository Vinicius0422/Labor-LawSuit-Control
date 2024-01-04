package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant;

public class ClaimantRequestDto {

    private String claimantName;
    private String birthDate;
    private String occupation;
    private String rg;
    private String orgaoRg;
    private String cpf;
    private String pis;
    private String address;
    private String neighborhood;
    private String cep;
    private String bank;
    private String agency;
    private String operation;
    private String account;
    private String contact;
    private String email;
    private Long nationalityId;
    private Long maritalStatusId;
    private Long accountTypeId;
    private Long stateId;
    private Long cityId;


    public ClaimantRequestDto() {
    }

    public ClaimantRequestDto(String claimantName, String birthDate, String occupation, String rg, String orgaoRg, String cpf, String pis, String address, String neighborhood,
                              String cep, String bank, String agency, String operation, String account, String contact, String email, Long nationalityId, Long maritalStatusId,
                              Long accountTypeId, Long stateId, Long cityId) {
        this.claimantName = claimantName;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.rg = rg;
        this.orgaoRg = orgaoRg;
        this.cpf = cpf;
        this.pis = pis;
        this.address = address;
        this.neighborhood = neighborhood;
        this.cep = cep;
        this.bank = bank;
        this.agency = agency;
        this.operation = operation;
        this.account = account;
        this.contact = contact;
        this.email = email;
        this.nationalityId = nationalityId;
        this.maritalStatusId = maritalStatusId;
        this.accountTypeId = accountTypeId;
        this.stateId = stateId;
        this.cityId = cityId;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Long getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Long maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
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
}
