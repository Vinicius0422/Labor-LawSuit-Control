package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.maritalStatus.MaritalStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Long> {

    @Query(value = "SELECT br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.maritalStatus.MaritalStatusResponseDto(m.id, m.maritalStatus) FROM MaritalStatus m")
    List<MaritalStatusResponseDto> findAllMaritalStatus();
}
