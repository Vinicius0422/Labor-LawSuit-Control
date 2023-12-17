package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitPhase.LawsuitPhaseResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitStatus.LawsuitStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.LawsuitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawsuitStatusRepository extends JpaRepository<LawsuitStatus, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitStatus.LawsuitStatusResponseDto(ls.id, ls.status) FROM LawsuitStatus ls")
    List<LawsuitStatusResponseDto> findAllLawsuitStatus();

    boolean existsByLawsuitStatusId(Long lawsuitStatusId);
}
