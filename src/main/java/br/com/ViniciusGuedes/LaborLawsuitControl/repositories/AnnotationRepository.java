package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Annotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnotationRepository extends JpaRepository<Annotation, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto(" +
            "an.annotationId, an.annotationDate, an.description, an.createdAt, an.updatedAt) " +
            "FROM Annotation an " +
            "WHERE an.lawsuit.lawsuitNumber = :lawsuitNumber")
    List<AnnotationResponseDto> findAnnotationsByLawsuitNumber(@Param("lawsuitNumber") String lawsuitNumber);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto(" +
            "an.annotationId, an.annotationDate, an.description, an.createdAt, an.updatedAt) " +
            "FROM Annotation an " +
            "WHERE an.lawsuit.lawsuitId = :lawsuitId")
    List<AnnotationResponseDto> findAnnotationsByLawsuitId(@Param("lawsuitId") Long lawsuitId);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationResponseDto(" +
            "an.annotationId, an.annotationDate, an.description, an.createdAt, an.updatedAt) " +
            "FROM Annotation an " +
            "WHERE an.lawsuit.claimant.claimantName LIKE %:claimantName%")
    List<AnnotationResponseDto> findAnnotationsByClaimantName(@Param("claimantName") String claimantName);
}
