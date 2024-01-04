package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Claimant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaimantRepository extends JpaRepository<Claimant, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto(c.claimantId, c.claimantName," +
            " c.rg, c.orgaoRg, c.cpf, c.address, c.neighborhood, c.cep, s.stateName, ci.cityName, ci.uf)" +
            " FROM Claimant c " +
            "LEFT JOIN c.state s " +
            "LEFT JOIN c.city ci")
    List<OnlyClaimantResponseDto> findAllClaimants();

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto(" +
            "c.claimantId, c.claimantName, c.birthDate, c.occupation, c.ctps, c.serieCtps, c.rg, c.orgaoRg, c.cpf, c.pis, c.address, s.stateName, ci.cityName, ci.uf, c.neighborhood, " +
            "c.cep, c.bank, c.agency, c.operation, c.account, c.contact, c.email, c.createdAt, c.updatedAt, " +
            "n.nationalityId, n.nationality, m.maritalStatusId, m.maritalStatus, a.accountTypeId, a.accountType) " +
            "FROM Claimant c " +
            "LEFT JOIN c.state s " +
            "LEFT JOIN c.city ci " +
            "LEFT JOIN c.nationality n " +
            "LEFT JOIN c.maritalStatus m " +
            "LEFT JOIN c.accountType a " +
            "WHERE c.claimantId = :claimantId")
    Optional<ClaimantResponseDto> findClaimantById(@Param("claimantId") Long claimantId);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto(" +
            "c.claimantId, c.claimantName, c.birthDate, c.occupation, c.ctps, c.serieCtps, c.rg, c.orgaoRg, c.cpf, c.pis, c.address, s.stateName, ci.cityName, ci.uf, c.neighborhood, " +
            "c.cep, c.bank, c.agency, c.operation, c.account, c.contact, c.email, c.createdAt, c.updatedAt, " +
            "n.nationalityId, n.nationality, m.maritalStatusId, m.maritalStatus, a.accountTypeId, a.accountType) " +
            "FROM Claimant c " +
            "LEFT JOIN c.state s " +
            "LEFT JOIN c.city ci " +
            "LEFT JOIN c.nationality n " +
            "LEFT JOIN c.maritalStatus m " +
            "LEFT JOIN c.accountType a " +
            "WHERE c.cpf = :cpf")
    Optional<ClaimantResponseDto> findClaimantByCpf(@Param("cpf") String cpf);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto(" +
            "c.claimantId, c.claimantName, c.birthDate, c.occupation, c.ctps, c.serieCtps, c.rg, c.orgaoRg, c.cpf, c.pis, c.address, s.stateName, ci.cityName, ci.uf, c.neighborhood, " +
            "c.cep, c.bank, c.agency, c.operation, c.account, c.contact, c.email, c.createdAt, c.updatedAt, " +
            "n.nationalityId, n.nationality, m.maritalStatusId, m.maritalStatus, a.accountTypeId, a.accountType) " +
            "FROM Claimant c " +
            "LEFT JOIN c.state s " +
            "LEFT JOIN c.city ci " +
            "LEFT JOIN c.nationality n " +
            "LEFT JOIN c.maritalStatus m " +
            "LEFT JOIN c.accountType a " +
            "WHERE c.claimantName LIKE %:claimantName%")
    List<ClaimantResponseDto> findClaimantByNameContains(@Param("claimantName") String claimantName);

    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
    boolean existsByBankAndAgencyAndAccount(String bank, String agency, String account);
    Claimant findByCpfEquals(String cpf);
    Claimant findByRgEquals(String rg);
    Claimant findByBankAndAgencyAndAccountEquals(String bank, String agency, String account);
}
