package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Attorney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttorneyRepository extends JpaRepository<Attorney, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto(" +
            "at.attorneyId, at.attorneyName, at.cpf, at.oabNumber) " +
            "FROM Attorney at " +
            "JOIN at.lawsuits ls " +
            "WHERE ls.lawsuitNumber = :lawsuitNumber")
    List<AttorneyResponseDto> findAttorneysByLawsuitNumber(@Param("lawsuitNumber") String lawsuitNumber);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.attorney.AttorneyResponseDto(" +
            "at.attorneyId, at.attorneyName, at.cpf, at.oabNumber) " +
            "FROM Attorney at " +
            "JOIN at.lawsuits ls " +
            "WHERE ls.lawsuitId = :lawsuitId")
    List<AttorneyResponseDto> findAttorneysByLawsuitId(@Param("lawsuitId") Long lawsuitId);

}
