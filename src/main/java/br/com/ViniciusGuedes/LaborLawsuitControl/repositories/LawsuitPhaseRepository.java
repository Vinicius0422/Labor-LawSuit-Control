package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.accountType.AccountTypeReponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitPhase.LawsuitPhaseResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.LawsuitPhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawsuitPhaseRepository extends JpaRepository<LawsuitPhase, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitPhase.LawsuitPhaseResponseDto(lp.id, lp.phase) FROM LawsuitPhase lp")
    List<LawsuitPhaseResponseDto> findAllLawsuitPhases();
}
