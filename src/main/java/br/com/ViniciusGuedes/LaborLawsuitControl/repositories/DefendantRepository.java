package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Claimant;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Defendant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefendantRepository extends JpaRepository<Defendant, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto("
            + "d.defendantId, d.defendantName, d.personType, d.cpfCnpj) FROM Defendant d")
    List<DefendantSomeFieldsResponseDto> findAllDefendants();

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantResponseDto(" +
            "d.defendantId, d.defendantName, d.personType, d.cpfCnpj, d.address, d.city, d.neighborhood, d.uf, " +
            "d.cep, d.contact, d.email, d.createdAt, d.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto(" +
            "c.claimantId, c.claimantName, c.rg, c.orgaoRg, c.cpf, c.address, c.city, c.neighborhood, c.uf, c.cep), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto(" +
            "p.progressId, p.progressDate, p.expectedDate, p.expectedTime, p.description, p.createdAt, p.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto(" +
            "an.annotationId, an.annotationDate, an.description, an.createdAt, an.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto(" +
            "at.attorneyId, at.attorneyName, at.cpf, at.oabNumber) " +
            "FROM Defendant d " +
            "LEFT JOIN d.lawsuits ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.claimant c " +
            "LEFT JOIN ls.progress p " +
            "LEFT JOIN ls.annotations an " +
            "LEFT JOIN ls.defendants def " +
            "LEFT JOIN ls.attorneys at " +
            "WHERE d.defendantName LIKE %:defendantName%")
    List<DefendantResponseDto> findDefendantByNameContains(@Param("defendantName") String defendantName);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantResponseDto(" +
            "d.defendantId, d.defendantName, d.personType, d.cpfCnpj, d.address, d.city, d.neighborhood, d.uf, " +
            "d.cep, d.contact, d.email, d.createdAt, d.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto(" +
            "c.claimantId, c.claimantName, c.rg, c.orgaoRg, c.cpf, c.address, c.city, c.neighborhood, c.uf, c.cep), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto(" +
            "p.progressId, p.progressDate, p.expectedDate, p.expectedTime, p.description, p.createdAt, p.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto(" +
            "an.annotationId, an.annotationDate, an.description, an.createdAt, an.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto(" +
            "at.attorneyId, at.attorneyName, at.cpf, at.oabNumber) " +
            "FROM Defendant d " +
            "LEFT JOIN d.lawsuits ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.claimant c " +
            "LEFT JOIN ls.progress p " +
            "LEFT JOIN ls.annotations an " +
            "LEFT JOIN ls.defendants def " +
            "LEFT JOIN ls.attorneys at " +
            "WHERE d.defendantId = :defendantId")
    Optional<DefendantResponseDto> findDefendantById(@Param("defendantId") Long defendantId);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantResponseDto(" +
            "d.defendantId, d.defendantName, d.personType, d.cpfCnpj, d.address, d.city, d.neighborhood, d.uf, " +
            "d.cep, d.contact, d.email, d.createdAt, d.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto(" +
            "c.claimantId, c.claimantName, c.rg, c.orgaoRg, c.cpf, c.address, c.city, c.neighborhood, c.uf, c.cep), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto(" +
            "p.progressId, p.progressDate, p.expectedDate, p.expectedTime, p.description, p.createdAt, p.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto(" +
            "an.annotationId, an.annotationDate, an.description, an.createdAt, an.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto(" +
            "at.attorneyId, at.attorneyName, at.cpf, at.oabNumber) " +
            "FROM Defendant d " +
            "LEFT JOIN d.lawsuits ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.claimant c " +
            "LEFT JOIN ls.progress p " +
            "LEFT JOIN ls.annotations an " +
            "LEFT JOIN ls.defendants def " +
            "LEFT JOIN ls.attorneys at " +
            "WHERE d.cpfCnpj = :cpfCnpj")
    Optional<DefendantResponseDto> findDefendantByCpfOrCnpj(@Param("cpfCnpj") String cpfCnpj);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj) " +
            "FROM Defendant def " +
            "JOIN def.lawsuits ls " +
            "WHERE ls.lawsuitNumber = :lawsuitNumber")
    List<DefendantSomeFieldsResponseDto> findDefendantsByLawsuitNumber(@Param("lawsuitNumber") String lawsuitNumber);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj) " +
            "FROM Defendant def " +
            "JOIN def.lawsuits ls " +
            "WHERE ls.lawsuitId = :lawsuitId")
    List<DefendantSomeFieldsResponseDto> findDefendantsByLawsuitId(@Param("lawsuitId") Long lawsuitId);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj) " +
            "FROM Defendant def " +
            "JOIN def.lawsuits ls " +
            "WHERE ls.claimant.claimantName LIKE %:claimantName%")
    List<DefendantSomeFieldsResponseDto> findDefendantsByClaimantName(@Param("claimantName") String claimantName);

    boolean existsByCpfCnpj(String cpfCnpj);
    Defendant findByCpfCnpjEquals(String cpfCnpj);
}
