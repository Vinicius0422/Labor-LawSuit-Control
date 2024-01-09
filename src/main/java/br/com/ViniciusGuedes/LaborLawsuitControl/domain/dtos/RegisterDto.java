package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.UserRole;

public record RegisterDto(String login, String password, UserRole role) {
}
