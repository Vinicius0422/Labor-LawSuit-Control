package br.com.ViniciusGuedes.LaborLawsuitControl.repositories;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Defendant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefendantRepository extends JpaRepository<Defendant, Long> {

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto("
            + "d.defendantId, d.defendantName, d.personType, d.cpfCnpj) FROM Defendant d")
    Page<DefendantSomeFieldsResponseDto> findAllDefendants(Pageable pageable);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantResponseDto(" +
            "d.defendantId, d.defendantName, d.personType, d.cpfCnpj, d.address, s.stateName, ci.cityName, ci.uf, d.neighborhood, " +
            "d.cep, d.contact, d.email, d.createdAt, d.updatedAt) " +
            "FROM Defendant d " +
            "LEFT JOIN d.state s " +
            "LEFT JOIN d.city ci " +
            "WHERE d.defendantName LIKE %:defendantName%")
    Page<DefendantResponseDto> findDefendantByNameContains(@Param("defendantName") String defendantName, Pageable pageable);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantResponseDto(" +
            "d.defendantId, d.defendantName, d.personType, d.cpfCnpj, d.address, s.stateName, ci.cityName, ci.uf, d.neighborhood, " +
            "d.cep, d.contact, d.email, d.createdAt, d.updatedAt) " +
            "FROM Defendant d " +
            "LEFT JOIN d.state s " +
            "LEFT JOIN d.city ci " +
            "WHERE d.defendantId = :defendantId")
    Optional<DefendantResponseDto> findDefendantById(@Param("defendantId") Long defendantId);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantResponseDto(" +
            "d.defendantId, d.defendantName, d.personType, d.cpfCnpj, d.address, s.stateName, ci.cityName, ci.uf, d.neighborhood, " +
            "d.cep, d.contact, d.email, d.createdAt, d.updatedAt) " +
            "FROM Defendant d " +
            "LEFT JOIN d.state s " +
            "LEFT JOIN d.city ci " +
            "WHERE d.cpfCnpj = :cpfCnpj")
    Optional<DefendantResponseDto> findDefendantByCpfOrCnpj(@Param("cpfCnpj") String cpfCnpj);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj) " +
            "FROM Defendant def " +
            "JOIN def.lawsuits ls " +
            "WHERE ls.lawsuitNumber = :lawsuitNumber")
    List<DefendantSomeFieldsResponseDto> findDefendantsByLawsuitNumber(@Param("lawsuitNumber") String lawsuitNumber);

    @Query("SELECT new br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto(" +
            "def.defendantId, def.defendantName, def.personType, def.cpfCnpj) " +
            "FROM Defendant def " +
            "JOIN def.lawsuits ls " +
            "WHERE ls.lawsuitId = :lawsuitId")
    List<DefendantSomeFieldsResponseDto> findDefendantsByLawsuitId(@Param("lawsuitId") Long lawsuitId);

    boolean existsByCpfCnpj(String cpfCnpj);
    Defendant findByCpfCnpjEquals(String cpfCnpj);
}
