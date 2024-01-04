package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.city.CityResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository <City, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.city.CityResponseDto(" +
            "c.cityId, c.cityName) " +
            "FROM City c " +
            "JOIN c.state s " +
            "WHERE stateId = :stateId")
    List<CityResponseDto> findAllCitiesByStateId(@Param("stateId") Long stateId);

    boolean existsById(Long cityId);
}
