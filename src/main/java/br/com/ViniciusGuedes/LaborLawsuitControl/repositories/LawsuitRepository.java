package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitSomeFieldsResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Lawsuit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LawsuitRepository extends JpaRepository<Lawsuit, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitSomeFieldsResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.valueCase," +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location) " +
            "FROM Lawsuit ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l")
    Page<LawsuitSomeFieldsResponseDto> findAllLawsuits(Pageable pageable);

@Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto(" +
        "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
        "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location, " +
        "c.claimantId, c.claimantName, c.rg, c.orgaoRg, c.cpf, c.address, s.stateName, ci.cityName, ci.uf, c.neighborhood, c.cep) " +

        "FROM Lawsuit ls " +
        "LEFT JOIN ls.lawsuitPhase lsp " +
        "LEFT JOIN ls.lawsuitStatus lss " +
        "LEFT JOIN ls.location l " +
        "LEFT JOIN ls.claimant c " +
        "LEFT JOIN c.state s " +
        "LEFT JOIN c.city ci " +
        "WHERE ls.lawsuitId = :lawsuitId")
Optional<LawsuitResponseDto> findLawsuitById(@Param("lawsuitId") Long lawsuitId);


    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location, " +
            "c.claimantId, c.claimantName, c.rg, c.orgaoRg, c.cpf, c.address, s.stateName, ci.cityName, ci.uf, c.neighborhood, c.cep) " +

            "FROM Lawsuit ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.claimant c " +
            "LEFT JOIN c.state s " +
            "LEFT JOIN c.city ci " +
            "WHERE ls.lawsuitNumber = :lawsuitNumber")
    Optional<LawsuitResponseDto> findLawsuitByNumber(@Param("lawsuitNumber") String lawsuitNumber);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location, " +
            "c.claimantId, c.claimantName, c.rg, c.orgaoRg, c.cpf, c.address, s.stateName, ci.cityName, ci.uf, c.neighborhood, c.cep) " +

            "FROM Lawsuit ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.claimant c " +
            "LEFT JOIN c.state s " +
            "LEFT JOIN c.city ci " +
            "WHERE c.claimantName LIKE %:claimantName%")
    List<LawsuitResponseDto> findLawsuitByClaimantName(@Param("claimantName") String claimantName);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitSomeFieldsResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.valueCase, lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location) " +
            "FROM Lawsuit ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.claimant c " +
            "WHERE c.claimantId = :claimantId")
    List<LawsuitSomeFieldsResponseDto> findLawsuitByClaimantId(@Param("claimantId") Long claimantId);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitSomeFieldsResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.valueCase, lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location) " +
            "FROM Lawsuit ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "JOIN ls.defendants def " +
            "WHERE def.defendantId = :defendantId")
    List<LawsuitSomeFieldsResponseDto> findLawsuitByDefendatId(@Param("defendantId") Long defendantId);

    boolean existsByLawsuitNumber(String lawsuitNumber);
    Lawsuit findByLawsuitNumberEquals(String lawsuitNumber);
    boolean existsByLawsuitId(Long id);
}
