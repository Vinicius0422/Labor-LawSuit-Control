package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.defendant.DefendantSomeFieldsResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitSomeFieldsResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Defendant;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.DefendantService;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils.InputCleaner;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations.DefendantValidator;

import br.com.ViniciusGuedes.LaborLawsuitControl.exceptions.ResourceNotFoundException;
import br.com.ViniciusGuedes.LaborLawsuitControl.mappers.DefendantMapper;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.DefendantRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LawsuitRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class DefendantServiceImpl implements DefendantService {

    private DefendantMapper defendantMapper;
    private DefendantRepository defendantRepository;
    private DefendantValidator defendantValidator;
    private LawsuitRepository lawsuitRepository;
    private InputCleaner inputCleaner;

    public DefendantServiceImpl(DefendantMapper defendantMapper,
                                DefendantRepository defendantRepository,
                                DefendantValidator defendantValidator,
                                LawsuitRepository lawsuitRepository,
                                InputCleaner inputCleaner) {
        this.defendantMapper = defendantMapper;
        this.defendantRepository = defendantRepository;
        this.defendantValidator = defendantValidator;
        this.lawsuitRepository = lawsuitRepository;
        this.inputCleaner = inputCleaner;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DefendantSomeFieldsResponseDto> getAllDefendants(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DefendantSomeFieldsResponseDto> defendants = defendantRepository.findAllDefendants(pageable);
        return defendants;
    }

    @Override
    @Transactional(readOnly = true)
    public DefendantResponseDto getDefendantById(Long defendantId) throws ResourceNotFoundException{
        DefendantResponseDto defendant = defendantRepository.findDefendantById(defendantId)
                .orElseThrow(() -> new ResourceNotFoundException("Defendant is not exists for given id!"));
        List<LawsuitSomeFieldsResponseDto> lawsuitsDto = lawsuitRepository.findLawsuitByDefendatId(defendantId);
        defendant.setLawsuits(lawsuitsDto);
        return defendant;
    }

    @Override
    @Transactional(readOnly = true)
    public DefendantResponseDto getDefendantByCpfOrCnpj(String cpfCnpj) {
        String cpfCnpjFormatted = inputCleaner.cleanseNumericInput(cpfCnpj);
        if(cpfCnpjFormatted.length() != 11 && cpfCnpjFormatted.length() != 14){
            throw new ConstraintViolationException("The CPF/CNPJ provided does not meet the requirements", null);
        }
        DefendantResponseDto defendant = defendantRepository.findDefendantByCpfOrCnpj(cpfCnpjFormatted)
                .orElseThrow(() -> new ResourceNotFoundException("Defendant is not exists for given CPF/CNPJ!"));
        List<LawsuitSomeFieldsResponseDto> lawsuitsDto = lawsuitRepository.findLawsuitByDefendatId(defendant.getDefendantId());
        defendant.setLawsuits(lawsuitsDto);
        return defendant;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DefendantResponseDto> getDefendantByName(String defendantName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if(defendantName.isBlank() || defendantName.length() < 3){
            throw new IllegalArgumentException("Enter a defendant name with at least 3 characters!");
        }
        Page<DefendantResponseDto> defendants = defendantRepository.findDefendantByNameContains(defendantName, pageable)
                .map((defendant) -> {
                    List<LawsuitSomeFieldsResponseDto> lawsuitsDto = lawsuitRepository.findLawsuitByDefendatId(defendant.getDefendantId());
                    defendant.setLawsuits(lawsuitsDto);
                    return defendant;
                });
        return defendants;
    }

    @Override
    @Transactional
    public SaveOrUpdateResponseDefault saveDefendant(DefendantRequestDto defendantRequestDto) {
        List<String> errors = defendantValidator.validationSaveDefendantRequestDto(defendantRequestDto);
        if(!errors.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.BAD_REQUEST, errors);
        }
        Defendant defendant = defendantMapper.defendantDtoToEntity(defendantRequestDto);
        defendantRepository.save(defendant);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Successfully created!"));
    }

    @Override
    @Transactional
    public SaveOrUpdateResponseDefault updateDefendant(Long id, DefendantRequestDto defendantRequestDto) {
        Defendant defendantToUpdate = defendantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Defedant is not exists for given id!"));
        List<String> errors = defendantValidator.validationUpdateDefendantRequestDto(id, defendantRequestDto);
        if(!errors.isEmpty()) {
            return  new SaveOrUpdateResponseDefault(HttpStatus.BAD_REQUEST, errors);
        }
        Defendant existingDefendant = defendantToUpdate;
        defendantMapper.updateDefendantFields(existingDefendant, defendantRequestDto);
        existingDefendant.setUpdatedAt(LocalDateTime.now());
        defendantRepository.save(existingDefendant);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }
}
