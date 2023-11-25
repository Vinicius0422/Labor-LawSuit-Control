package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Claimant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimantRepository extends JpaRepository<Claimant, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto(c.id, c.claimantName," +
            " c.rg, c.orgaoRg, c.cpf, c.address, c.city, c.neighborhood, c.uf, c.cep) FROM Claimant c")
    List<OnlyClaimantResponseDto> findAllClaimants();

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto(c.id, c.claimantName," +
            "c.birthDate, c.occupation, c.ctps, c.serieCtps, c.rg, c.orgaoRg, c.cpf, c.pis, c.address, c.city, c.neighborhood, c.uf," +
            " c.cep, c.bank, c.agency, c.operation, c.account, c.contact, c.email, c.createdAt, c.updatedAt, n.id, n.nationality, m.id," +
            " m.maritalStatus, a.id, a.accountType, ) FROM Claimant c")
    ClaimantResponseDto findClaimantById(Long id);

}
