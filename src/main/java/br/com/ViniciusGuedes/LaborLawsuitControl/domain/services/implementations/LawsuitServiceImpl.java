package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Lawsuit;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitService;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.utils.InputCleaner;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations.DateFormatValidator;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.validations.LawsuitValidator;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LawsuitServiceImpl implements LawsuitService {

    private LawsuitRepository lawsuitRepository;
    private ClaimantRepository claimantRepository;
    private DefendantRepository defendantRepository;
    private LawsuitPhaseRepository lawsuitPhaseRepository;
    private LawsuitStatusRepository lawsuitStatusRepository;
    private LocationRepository locationRepository;
    private AttorneyRepository attorneyRepository;
    private ProgressRepository progressRepository;
    private AnnotationRepository annotationRepository;
    private DateFormatValidator dateFormatValidator;
    private InputCleaner inputCleaner;
    private LawsuitValidator lawsuitValidator;

    public LawsuitServiceImpl(LawsuitRepository lawsuitRepository, ClaimantRepository claimantRepository,
                              DefendantRepository defendantRepository, LawsuitPhaseRepository lawsuitPhaseRepository,
                              LawsuitStatusRepository lawsuitStatusRepository, LocationRepository locationRepository,
                              AttorneyRepository attorneyRepository, ProgressRepository progressRepository,
                              AnnotationRepository annotationRepository, DateFormatValidator dateFormatValidator,
                              InputCleaner inputCleaner, LawsuitValidator lawsuitValidator) {
        this.lawsuitRepository = lawsuitRepository;
        this.claimantRepository = claimantRepository;
        this.defendantRepository = defendantRepository;
        this.lawsuitPhaseRepository = lawsuitPhaseRepository;
        this.lawsuitStatusRepository = lawsuitStatusRepository;
        this.locationRepository = locationRepository;
        this.attorneyRepository = attorneyRepository;
        this.progressRepository = progressRepository;
        this.annotationRepository = annotationRepository;
        this.dateFormatValidator = dateFormatValidator;
        this.inputCleaner = inputCleaner;
        this.lawsuitValidator = lawsuitValidator;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getAllLawsuits(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var lawsuits = lawsuitRepository.findAllLawsuits(pageable);
        if(lawsuits.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No results found!", null);
        }
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", lawsuits);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getLawsuitById(Long lawsuitId) {
        var lawsuitDto = lawsuitRepository.findLawsuitById(lawsuitId).orElse(null);

        if(lawsuitDto == null){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", null);
        }
        var progress = progressRepository.findProgressByLawsuitId(lawsuitId);
        var annotations = annotationRepository.findAnnotationsByLawsuitId(lawsuitId);
        var defendants = defendantRepository.findDefendantsByLawsuitId(lawsuitId);
        var attorneys = attorneyRepository.findAttorneysByLawsuitId(lawsuitId);

        lawsuitDto.setProgress(progress);
        lawsuitDto.setAnnotations(annotations);
        lawsuitDto.setDefendants(defendants);
        lawsuitDto.setAttorneys(attorneys);

        return new ResponseDefault(HttpStatus.OK, "Search carried out!", lawsuitDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDefault getLawsuitByNumber(String lawsuitNumber) {
        var lawsuitNumberFormatted = inputCleaner.cleanseNumericInput(lawsuitNumber);
        if(lawsuitNumberFormatted.length() < 16){
            return new ResponseDefault(HttpStatus.BAD_REQUEST,"Enter a lawsuit number with at least 16 characters!", null);
        }
        var lawsuitDto = lawsuitRepository.findLawsuitByNumber(inputCleaner.cleanseNumericInput(lawsuitNumber)).orElse(null);

        if (lawsuitDto == null) {
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found!", null);
        }
//        var paramter = inputCleaner.cleanseNumericInput(lawsuitNumber);
//        var progress = progressRepository.findProgressByLawsuitNumber(paramter);
//        var annotations = annotationRepository.findAnnotationsByLawsuitNumber(paramter);
//        var defendants = defendantRepository.findDefendantsByLawsuitNumber(paramter);
//        var attorneys = attorneyRepository.findAttorneysByLawsuitNumber(paramter);
//
//        lawsuitDto.setProgress(progress);
//        lawsuitDto.setAnnotations(annotations);
//        lawsuitDto.setDefendants(defendants);
//        lawsuitDto.setAttorneys(attorneys);

        return new ResponseDefault(HttpStatus.OK, "Search carried out!", lawsuitDto);
    }


//    @Override
//    @Transactional(readOnly = true)
   public ResponseDefault getLawsuitByClaimantName(String claimantName) {
//        var claimantNameFormatted = inputCleaner.removeAccents(claimantName);
//        if(claimantNameFormatted.length() < 3){
//            return new ResponseDefault(HttpStatus.BAD_REQUEST,"Enter a name with at least 3 characters!", null);
//        }
//        var lawsuitsDto = lawsuitRepository.findLawsuitByClaimantName(claimantNameFormatted);
//        if(lawsuitsDto.isEmpty()){
//            return new ResponseDefault(HttpStatus.NOT_FOUND, "No results found!", null);
//        }
//
//////        for (int i = 0; i < lawsuitsDto.size(); i++) {
//////            var progress = progressRepository.findProgressByLawsuitId(lawsuitsDto.get(i).getLawsuitId());
//////            var annotations = annotationRepository.findAnnotationsByLawsuitId(lawsuitsDto.get(i).getLawsuitId());
//////            var defendants = defendantRepository.findDefendantsByLawsuitId(lawsuitsDto.get(i).getLawsuitId());
//////            var attorneys = attorneyRepository.findAttorneysByLawsuitId(lawsuitsDto.get(i).getLawsuitId());
//////            lawsuitsDto.get(i).setProgress(progress);
//////            lawsuitsDto.get(i).setAnnotations(annotations);
//////            lawsuitsDto.get(i).setDefendants(defendants);
//////            lawsuitsDto.get(i).setAttorneys(attorneys);
////        }
       return new ResponseDefault(HttpStatus.OK, "Search carried out!", "lawsuitsDto");
    }

    @Override
    @Transactional
    public SaveOrUpdateResponseDefault saveLawsuit(LawsuitRequestDto lawsuitRequestDto) {
//        var validation = lawsuitValidator.saveLawsuitValidation(lawsuitRequestDto);
//        if(!validation.isEmpty()){
//            return new SaveOrUpdateResponseDefault(HttpStatus.UNPROCESSABLE_ENTITY, validation);
//        }
        Lawsuit lawsuit = createLawsuitFromDto(lawsuitRequestDto);
        lawsuit.setDistributionDate(LocalDate.now());
        lawsuit.setCreatedAt(LocalDateTime.now());
        lawsuitRepository.save(lawsuit);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Successfully created!"));
    }

    @Override
    @Transactional
    public SaveOrUpdateResponseDefault updateLawsuit(Long id, LawsuitRequestDto lawsuitRequestDto) {
        var lawsuitToUpdate = lawsuitRepository.findById(id).orElse(null);
        if(lawsuitToUpdate == null){
            return new SaveOrUpdateResponseDefault(HttpStatus.NOT_FOUND, Collections.singletonList("No records found for this ID!"));
        }
//        var validation = lawsuitValidator.updateLawsuitValidation(id, lawsuitRequestDto);
//        if(!validation.isEmpty()){
//            return new SaveOrUpdateResponseDefault(HttpStatus.UNPROCESSABLE_ENTITY, validation);
//        }
        Lawsuit existingLawsuit = lawsuitToUpdate;
        updateLawsuitFields(existingLawsuit, lawsuitRequestDto);
        existingLawsuit.setUpdatedAt(LocalDateTime.now());
        lawsuitRepository.save(existingLawsuit);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }

    public Lawsuit createLawsuitFromDto(LawsuitRequestDto lawsuitRequestDto) {
        Lawsuit lawsuit = new Lawsuit();
        lawsuit.setLawsuitNumber(lawsuitRequestDto.lawsuitNumber());
        lawsuit.setCivilCourt(lawsuitRequestDto.lawsuitNumber().substring(lawsuitRequestDto.lawsuitNumber().length() - 4));
//        lawsuit.setDistributionDate(dateFormatValidator.parseLocalDate(lawsuitRequestDto.distributionDate()));
        lawsuit.setValueCase(lawsuitRequestDto.valueCase());

        if(lawsuitRequestDto.lawsuitPhaseId() == null || !lawsuitPhaseRepository.existsByLawsuitPhaseId(lawsuitRequestDto.lawsuitPhaseId())){
            var lawsuitPhase = lawsuitPhaseRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Lawsuit Phase not found"));
            lawsuit.setLawsuitPhase(lawsuitPhase);
        } else {
            var lawsuitPhase = lawsuitPhaseRepository.findById(lawsuitRequestDto.lawsuitPhaseId())
                    .orElseThrow(() -> new EntityNotFoundException("Lawsuit Phase not found"));
            lawsuit.setLawsuitPhase(lawsuitPhase);
        }

        if(lawsuitRequestDto.lawsuitStatusId() == null || !lawsuitStatusRepository.existsByLawsuitStatusId(lawsuitRequestDto.lawsuitStatusId())){
            var lawsuitStatus = lawsuitStatusRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Lawsuit Status not found"));
            lawsuit.setLawsuitStatus(lawsuitStatus);
        } else {
            var lawsuitStatus = lawsuitStatusRepository.findById(lawsuitRequestDto.lawsuitStatusId())
                    .orElseThrow(() -> new EntityNotFoundException("Lawsuit Status not found"));
            lawsuit.setLawsuitStatus(lawsuitStatus);
        }

        if(lawsuitRequestDto.locationId() == null || !locationRepository.existsByLocationId(lawsuitRequestDto.locationId())){
            var location = locationRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException("Location not found"));
            lawsuit.setLocation(location);
        } else {
            var location = locationRepository.findById(lawsuitRequestDto.locationId()).orElseThrow(() -> new EntityNotFoundException("Location not found"));
            lawsuit.setLocation(location);
        }

        var claimant = claimantRepository.findByCpfEquals(inputCleaner.cleanseNumericInput(lawsuitRequestDto.cpfClaimant()));
        lawsuit.setClaimant(claimant);

        List defendants = new ArrayList<>();
        for (int i = 0; i < lawsuitRequestDto.cpfCnpjDefendants().size(); i++) {
            var defendant = defendantRepository.findByCpfCnpjEquals(inputCleaner.cleanseNumericInput(lawsuitRequestDto.cpfCnpjDefendants().get(i)));
            defendants.add(defendant);
        }
        lawsuit.setDefendants(defendants);

        List attorneys = new ArrayList<>();
        for (int i = 0; i < lawsuitRequestDto.attorneys().size(); i++) {
            var attorney = attorneyRepository.findById(lawsuitRequestDto.attorneys().get(i)).orElse(null);
            attorneys.add(attorney);
        }
        lawsuit.setAttorneys(attorneys);

        return lawsuit;
    }

    public void updateLawsuitFields(Lawsuit existingLawsuit, LawsuitRequestDto lawsuitRequestDto){
    }
//        existingLawsuit.setLawsuitNumber(lawsuitRequestDto.getLawsuitNumber());
//        existingLawsuit.setCivilCourt(lawsuitRequestDto.getLawsuitNumber().substring(lawsuitRequestDto.getLawsuitNumber().length() - 4));
//        existingLawsuit.setDistributionDate(dateFormatValidator.parseLocalDate(lawsuitRequestDto.getDistributionDate()));
//        existingLawsuit.setValueCase(lawsuitRequestDto.getValueCase());
//        if(lawsuitRequestDto.getLawsuitPhaseId() == null || lawsuitPhaseRepository.existsByLawsuitPhaseId(lawsuitRequestDto.getLawsuitPhaseId()) == false){
//            var lawsuitPhase = lawsuitPhaseRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Lawsuit Phase not found"));
//            existingLawsuit.setLawsuitPhase(lawsuitPhase);
//        } else {
//            var lawsuitPhase = lawsuitPhaseRepository.findById(lawsuitRequestDto.getLawsuitPhaseId())
//                    .orElseThrow(() -> new EntityNotFoundException("Lawsuit Phase not found"));
//            existingLawsuit.setLawsuitPhase(lawsuitPhase);
//        }
//
//        if(lawsuitRequestDto.getLawsuitStatusId() == null || lawsuitStatusRepository.existsByLawsuitStatusId(lawsuitRequestDto.getLawsuitPhaseId()) == false){
//            var lawsuitStatus = lawsuitStatusRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Lawsuit Status not found"));
//            existingLawsuit.setLawsuitStatus(lawsuitStatus);
//        } else {
//            var lawsuitStatus = lawsuitStatusRepository.findById(lawsuitRequestDto.getLawsuitStatusId())
//                    .orElseThrow(() -> new EntityNotFoundException("Lawsuit Status not found"));
//            existingLawsuit.setLawsuitStatus(lawsuitStatus);
//        }
//
//        if(lawsuitRequestDto.getLocationId() == null || locationRepository.existsByLocationId(lawsuitRequestDto.getLocationId()) == false){
//            var location = locationRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException("Location not found"));
//            existingLawsuit.setLocation(location);
//        } else {
//            var location = locationRepository.findById(lawsuitRequestDto.getLocationId()).orElseThrow(() -> new EntityNotFoundException("Location not found"));
//            existingLawsuit.setLocation(location);
//        }
//        var claimantId = claimantRepository.findByCpfEquals(inputCleaner.cleanseNumericInput(lawsuitRequestDto.getCpfClaimant())).getClaimantId();
//        var claimant = claimantRepository.findById(claimantId).orElseThrow(() -> new EntityNotFoundException("Claimant not found"));
//        existingLawsuit.setClaimant(claimant);
//
//        List defendants = new ArrayList<>();
//        for (int i = 0; i < lawsuitRequestDto.getCpfCnpjDefendants().size(); i++) {
//            var cpfCnpjFormatted = inputCleaner.cleanseNumericInput(lawsuitRequestDto.getCpfCnpjDefendants().get(i));
//            var defendant = defendantRepository.findByCpfCnpjEquals(cpfCnpjFormatted);
//            defendants.add(defendant);
//        }
//        existingLawsuit.setDefendants(defendants);
//
//        List attorneys = new ArrayList<>();
//        for (int i = 0; i < lawsuitRequestDto.getAttorneys().size(); i++) {
//            var attorney = attorneyRepository.findById(lawsuitRequestDto.getAttorneys().get(i)).orElseThrow(() -> new EntityNotFoundException("Attorney not found"));
//            attorneys.add(attorney);
//        }
//        existingLawsuit.setAttorneys(attorneys);
//    }
}
