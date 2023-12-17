package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Lawsuit;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto(" +
            "p.progressId, p.progressDate, p.expectedDate, p.expectedTime, p.description, p.createdAt, p.updatedAt) " +
            "FROM Progress p " +
            "WHERE p.lawsuit.lawsuitNumber = :lawsuitNumber")
    List<ProgressResponseDto> findProgressByLawsuitNumber(@Param("lawsuitNumber") String lawsuitNumber);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto(" +
            "p.progressId, p.progressDate, p.expectedDate, p.expectedTime, p.description, p.createdAt, p.updatedAt) " +
            "FROM Progress p " +
            "WHERE p.lawsuit.lawsuitId = :lawsuitId")
    List<ProgressResponseDto> findProgressByLawsuitId(@Param("lawsuitId") Long lawsuitId);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressResponseDto(" +
            "p.progressId, p.progressDate, p.expectedDate, p.expectedTime, p.description, p.createdAt, p.updatedAt) " +
            "FROM Progress p " +
            "WHERE p.lawsuit.claimant.claimantName LIKE %:claimantName%")
    List<ProgressResponseDto> findProgressByClaimantName(@Param("claimantName") String claimantName);
}
