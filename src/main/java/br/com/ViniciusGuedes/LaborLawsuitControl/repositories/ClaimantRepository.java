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

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto(c.claimantId, c.claimantName," +
            " c.rg, c.orgaoRg, c.cpf, c.address, c.city, c.neighborhood, c.uf, c.cep) FROM Claimant c")
    List<OnlyClaimantResponseDto> findAllClaimants();

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto(c.claimantId, c.claimantName," +
            "c.birthDate, c.occupation, c.ctps, c.serieCtps, c.rg, c.orgaoRg, c.cpf, c.pis, c.address, c.city, c.neighborhood, c.uf," +
            " c.cep, c.bank, c.agency, c.operation, c.account, c.contact, c.email, c.createdAt, c.updatedAt, n.nationalityId, n.nationality, m.maritalStatusId," +
            " m.maritalStatus, a.accountTypeId, a.accountType, ls.lawsuitId, ls.lawsuitNumber, ls.civilCourt, ls.distributionDate, ls.valueCase, ls.createdAt," +
            "ls.updatedAt, ls.lawsuitPhaseId, ls.statusId, ls.locationId, p.progressId, p.progressDate, p.expectedDate, p.expectedTime, p.description," +
            "p.createdAt, p.updatedAt, p.lawsuitPhaseId, p.lawsuitPhaseId, p.lawsuitId, an.annotationId, an.annotationDate, an.description, an.createdAt, " +
            "an.updatedAt, an.lawsuitId, def.defendantId, def.defendantName, def.personType, def.cpfCnpj, at.attorneyId, at.attorneyName, at.cpf, at.oabNumber )" +
            " FROM Claimant c " +
            "LEFT JOIN Nationality n" +
            "LEFT JOIN MaritalStatus m" +
            "LEFT JOIN AccountType a" +
            "LEFT JOIN Lawsuit ls" +
            "LEFT JOIN Progress p" +
            "LEFT JOIN Annotation an" +
            "LEFT JOIN Defendant def" +
            "LEFT JOIN Attorney at" +
            "WHERE c.claimantId = :claimantId")
    ClaimantResponseDto findClaimantById(Long claimantId);

}
