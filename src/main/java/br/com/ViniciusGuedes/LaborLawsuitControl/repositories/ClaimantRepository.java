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
            " c.rg, c.orgaoRg, c.cpf, c.address, c.city, c.neighborhood, c.uf, c.cep) FROM Claimant c")
    List<OnlyClaimantResponseDto> findAllClaimants();

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto(" +
            "c.claimantId, c.claimantName, c.birthDate, c.occupation, c.ctps, c.serieCtps, c.rg, c.orgaoRg, c.cpf, c.pis, c.address, c.city, c.neighborhood, c.uf, " +
            "c.cep, c.bank, c.agency, c.operation, c.account, c.contact, c.email, c.createdAt, c.updatedAt, " +
            "n.nationalityId, n.nationality, m.maritalStatusId, m.maritalStatus, a.accountTypeId, a.accountType), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitClaimantResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto(" +
            "p.progressId, p.progressDate, p.expectedDate, p.expectedTime, p.description, p.createdAt, p.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto(" +
            "an.annotationId, an.annotationDate, an.description, an.createdAt, an.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto(" +
            "at.attorneyId, at.attorneyName, at.cpf, at.oabNumber) " +

            "FROM Claimant c " +
            "LEFT JOIN c.nationality n " +
            "LEFT JOIN c.maritalStatus m " +
            "LEFT JOIN c.accountType a " +
            "LEFT JOIN c.lawsuits ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.progress p " +
            "LEFT JOIN ls.annotations an " +
            "LEFT JOIN ls.defendants def " +
            "LEFT JOIN ls.attorneys at " +
            "WHERE c.claimantId = :claimantId")
    Optional<ClaimantResponseDto> findClaimantById(@Param("claimantId") Long claimantId);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto(" +
            "c.claimantId, c.claimantName, c.birthDate, c.occupation, c.ctps, c.serieCtps, c.rg, c.orgaoRg, c.cpf, c.pis, c.address, c.city, c.neighborhood, c.uf, " +
            "c.cep, c.bank, c.agency, c.operation, c.account, c.contact, c.email, c.createdAt, c.updatedAt, " +
            "n.nationalityId, n.nationality, m.maritalStatusId, m.maritalStatus, a.accountTypeId, a.accountType), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitClaimantResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto(" +
            "p.progressId, p.progressDate, p.expectedDate, p.expectedTime, p.description, p.createdAt, p.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto(" +
            "an.annotationId, an.annotationDate, an.description, an.createdAt, an.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto(" +
            "at.attorneyId, at.attorneyName, at.cpf, at.oabNumber) " +

            "FROM Claimant c " +
            "LEFT JOIN c.nationality n " +
            "LEFT JOIN c.maritalStatus m " +
            "LEFT JOIN c.accountType a " +
            "LEFT JOIN c.lawsuits ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.progress p " +
            "LEFT JOIN ls.annotations an " +
            "LEFT JOIN ls.defendants def " +
            "LEFT JOIN ls.attorneys at " +
            "WHERE c.cpf = :cpf")
    Optional<ClaimantResponseDto> findClaimantByCpf(@Param("cpf") String cpf);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto(" +
            "c.claimantId, c.claimantName, c.birthDate, c.occupation, c.ctps, c.serieCtps, c.rg, c.orgaoRg, c.cpf, c.pis, c.address, c.city, c.neighborhood, c.uf, " +
            "c.cep, c.bank, c.agency, c.operation, c.account, c.contact, c.email, c.createdAt, c.updatedAt, " +
            "n.nationalityId, n.nationality, m.maritalStatusId, m.maritalStatus, a.accountTypeId, a.accountType), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitClaimantResponseDto(" +
            "ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt, ls.updatedAt, " +
            "lsp.lawsuitPhaseId, lsp.phase, lss.lawsuitStatusId, lss.status, l.locationId, l.location), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto(" +
            "p.progressId, p.progressDate, p.expectedDate, p.expectedTime, p.description, p.createdAt, p.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto(" +
            "an.annotationId, an.annotationDate, an.description, an.createdAt, an.updatedAt), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj), " +

            "new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto(" +
            "at.attorneyId, at.attorneyName, at.cpf, at.oabNumber) " +

            "FROM Claimant c " +
            "LEFT JOIN c.nationality n " +
            "LEFT JOIN c.maritalStatus m " +
            "LEFT JOIN c.accountType a " +
            "LEFT JOIN c.lawsuits ls " +
            "LEFT JOIN ls.lawsuitPhase lsp " +
            "LEFT JOIN ls.lawsuitStatus lss " +
            "LEFT JOIN ls.location l " +
            "LEFT JOIN ls.progress p " +
            "LEFT JOIN ls.annotations an " +
            "LEFT JOIN ls.defendants def " +
            "LEFT JOIN ls.attorneys at " +
            "WHERE c.claimantName LIKE %:claimantName%")
    List<ClaimantResponseDto> findClaimantByNameContains(@Param("claimantName") String claimantName);

    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
    boolean existsByBankAndAgencyAndAccount(String bank, String agency, String account);
    Claimant findByCpfEquals(String cpf);
    Claimant findByRgEquals(String rg);
    Claimant findByBankAndAgencyAndAccountEquals(String bank, String agency, String account);
}
