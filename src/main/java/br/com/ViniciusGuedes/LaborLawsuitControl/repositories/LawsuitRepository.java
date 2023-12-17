package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitSomeFieldsResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Lawsuit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LawsuitRepository extends JpaRepository<Lawsuit, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitSomeFieldsResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.valueCase," +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location) " +
            "FROM Lawsuit ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l")
    List<LawsuitSomeFieldsResponseDto> findAllLawsuits();

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location, " +
            "c.claimantId, c.claimantName, c.rg, c.orgaoRg, c.cpf, c.address, c.city, c.neighborhood, c.uf, c.cep) " +
            "FROM Lawsuit ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.claimant c " +
            "WHERE ls.lawsuitId = :lawsuitId")
    Optional<LawsuitResponseDto> findLawsuitById(@Param("lawsuitId") Long lawsuitId);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location, " +
            "c.claimantId, c.claimantName, c.rg, c.orgaoRg, c.cpf, c.address, c.city, c.neighborhood, c.uf, c.cep) " +
            "FROM Lawsuit ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.claimant c " +
            "WHERE ls.lawsuitNumber = :lawsuitNumber")
    Optional<LawsuitResponseDto> findLawsuitByNumber(@Param("lawsuitNumber") String lawsuitNumber);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location, " +
            "c.claimantId, c.claimantName, c.rg, c.orgaoRg, c.cpf, c.address, c.city, c.neighborhood, c.uf, c.cep) " +
            "FROM Lawsuit ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.claimant c " +
            "WHERE c.claimantName LIKE %:claimantName%")
    List<LawsuitResponseDto> findLawsuitByClaimantName(@Param("claimantName") String claimantName);

    boolean existsByLawsuitNumber(String lawsuitNumber);
    Lawsuit findByLawsuitNumberEquals(String lawsuitNumber);
}
