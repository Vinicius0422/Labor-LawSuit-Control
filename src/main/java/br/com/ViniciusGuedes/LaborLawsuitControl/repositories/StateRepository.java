package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.state.StateResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository <State, Long> {

    @Query("SELECT NEW br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.state.StateResponseDto( " +
            "s.stateId, s.stateName) FROM State s")
    List<StateResponseDto> findAllStates();

    boolean existsById(Long stateId);
}
