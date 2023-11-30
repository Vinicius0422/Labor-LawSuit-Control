package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.accountType.AccountTypeResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.accountType.AccountTypeResponseDto(a.id, a.accountType) FROM AccountType a")
    List<AccountTypeResponseDto> findAllAccountsType();
}
