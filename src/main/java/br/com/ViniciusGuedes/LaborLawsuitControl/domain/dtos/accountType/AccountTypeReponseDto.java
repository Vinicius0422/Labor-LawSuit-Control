package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.accountType;

import jakarta.persistence.Column;

public class AccountTypeReponseDto {

    private Long id;
    private String accountType;

    public AccountTypeReponseDto() {
    }

    public AccountTypeReponseDto(Long id, String accountType) {
        this.id = id;
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
