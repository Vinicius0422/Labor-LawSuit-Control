package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.ClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.claimant.OnlyClaimantResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitSomeFieldsResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Claimant;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.ClaimantService;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils.InputCleaner;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations.ClaimantValidator;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations.DateFormatValidator;
import br.com.ViniciusGuedes.LaborLawsuitControl.exceptions.ResourceNotFoundException;
import br.com.ViniciusGuedes.LaborLawsuitControl.mappers.ClaimantMapper;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.ClaimantRepository;
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
public class ClaimantServiceImpl implements ClaimantService {

    private ClaimantRepository claimantRepository;
    private LawsuitRepository lawsuitRepository;
    private InputCleaner inputCleaner;
    private ClaimantValidator claimantValidator;
    private ClaimantMapper claimantMapper;
    private DateFormatValidator dateFormatValidator;

    public ClaimantServiceImpl(ClaimantRepository claimantRepository,
                               LawsuitRepository lawsuitRepository,
                               InputCleaner inputCleaner,
                               ClaimantValidator claimantValidator,
                               ClaimantMapper claimantMapper,
                               DateFormatValidator dateFormatValidator) {
        this.claimantRepository = claimantRepository;
        this.lawsuitRepository = lawsuitRepository;
        this.inputCleaner = inputCleaner;
        this.claimantValidator = claimantValidator;
        this.claimantMapper = claimantMapper;
        this.dateFormatValidator = dateFormatValidator;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<OnlyClaimantResponseDto> getAllClaimants(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OnlyClaimantResponseDto> claimants = claimantRepository.findAllClaimants(pageable);
        return claimants;
    }

    @Override
    @Transactional(readOnly = true)
    public ClaimantResponseDto getClaimantById(Long claimantId) {
        ClaimantResponseDto claimant = claimantRepository.findClaimantById(claimantId)
                .orElseThrow(() -> new ResourceNotFoundException("Claimant is not exists for given id!"));
        List<LawsuitSomeFieldsResponseDto> lawsuitsDto = lawsuitRepository.findLawsuitByClaimantId(claimantId);
        claimant.setLawsuits(lawsuitsDto);
        return claimant;
    }

    @Override
    @Transactional(readOnly = true)
    public ClaimantResponseDto getByCpf(String cpf) {
        String cpfFormatted = inputCleaner.cleanseNumericInput(cpf);
        if(cpfFormatted.length() != 11){
            throw new ConstraintViolationException("The CPF provided does not meet the requirements", null);
        }
        ClaimantResponseDto claimant = claimantRepository.findClaimantByCpf(cpfFormatted)
                .orElseThrow(() -> new ResourceNotFoundException("Claimant is not exists for given cpf!"));
        List<LawsuitSomeFieldsResponseDto> lawsuitsDto = lawsuitRepository.findLawsuitByClaimantId(claimant.getClaimantId());
        claimant.setLawsuits(lawsuitsDto);
        return claimant;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClaimantResponseDto> getByName(String claimantName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (claimantName.isBlank() || claimantName.length() < 3) {
            throw new IllegalArgumentException("Enter a claimant name with at least 3 characters!");
        }
        Page<ClaimantResponseDto> claimantsDto = claimantRepository.findClaimantByNameContains(claimantName, pageable).map((claimant) -> {
            List<LawsuitSomeFieldsResponseDto> lawsuitsDto = lawsuitRepository.findLawsuitByClaimantId(claimant.getClaimantId());
            claimant.setLawsuits(lawsuitsDto);
            return claimant;
        });
        return claimantsDto;
    }


    @Override
    @Transactional
    public SaveOrUpdateResponseDefault saveClaimant(ClaimantRequestDto claimantRequestDto) {
        List<String> errors = claimantValidator.validationSaveClaimantRequestDto(claimantRequestDto);
        if(!errors.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.BAD_REQUEST, errors);
        }
        Claimant claimant = claimantMapper.createClaimantFromDto(claimantRequestDto);
        claimantRepository.save(claimant);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Successfully created!"));
    }

    @Override
    @Transactional
    public SaveOrUpdateResponseDefault updateClaimant(Long id, ClaimantRequestDto claimantRequestDto) {
        Claimant claimantToUpdate = claimantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claimant is not exists for given id!"));
        List<String> errors = claimantValidator.validationUpdateClaimantRequestDto(id, claimantRequestDto);
        if(!errors.isEmpty()) {
            return new SaveOrUpdateResponseDefault(HttpStatus.BAD_REQUEST, errors);
        }
        Claimant existingClaimant = claimantToUpdate;
        claimantMapper.updateClaimantFields(existingClaimant, claimantRequestDto);
        existingClaimant.setUpdatedAt(LocalDateTime.now());
        claimantRepository.save(existingClaimant);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }
}
