package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.accountType;

import jakarta.persistence.Column;

public class AccountTypeReponseDto {

    private Long accountTypeId;
    private String accountType;

    public AccountTypeReponseDto() {
    }

    public AccountTypeReponseDto(Long accountTypeId, String accountType) {
        this.accountTypeId = accountTypeId;
        this.accountType = accountType;
    }

    public Long getId() {
        return accountTypeId;
    }

    public void setId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
