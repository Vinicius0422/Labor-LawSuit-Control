package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuit.LawsuitRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Lawsuit;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.LawsuitService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LawsuitServiceImpl implements LawsuitService {

    @Autowired
    private LawsuitRepository lawsuitRepository;
    @Autowired
    private ClaimantRepository claimantRepository;
    @Autowired
    private DefendantRepository defendantRepository;
    @Autowired
    private LawsuitPhaseRepository lawsuitPhaseRepository;
    @Autowired
    private LawsuitStatusRepository lawsuitStatusRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AttorneyRepository attorneyRepository;
    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private AnnotationRepository annotationRepository;

    @Override
    public ResponseDefault getAllLawsuits() {
        var lawsuits = lawsuitRepository.findAllLawsuits();
        if(lawsuits.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No results found!", null);
        }
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", lawsuits);
    }

    @Override
    public ResponseDefault getLawsuitById(Long lawsuitId) {
        var lawsuitDto = lawsuitRepository.findLawsuitById(lawsuitId).orElse(null);
        if(lawsuitDto == null){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found for this ID!", null);
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
    public ResponseDefault getLawsuitByNumber(String lawsuitNumber) {
        var lawsuitDto = lawsuitRepository.findLawsuitByNumber(cleanseNumericInput(lawsuitNumber)).orElse(null);

        if (lawsuitDto == null) {
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No records found for this Lawsuit Number!", null);
        }

        var progress = progressRepository.findProgressByLawsuitNumber(cleanseNumericInput(lawsuitNumber));
        var annotations = annotationRepository.findAnnotationsByLawsuitNumber(cleanseNumericInput(lawsuitNumber));
        var defendants = defendantRepository.findDefendantsByLawsuitNumber(cleanseNumericInput(lawsuitNumber));
        var attorneys = attorneyRepository.findAttorneysByLawsuitNumber(cleanseNumericInput(lawsuitNumber));

        lawsuitDto.setProgress(progress);
        lawsuitDto.setAnnotations(annotations);
        lawsuitDto.setDefendants(defendants);
        lawsuitDto.setAttorneys(attorneys);

        return new ResponseDefault(HttpStatus.OK, "Search carried out!", lawsuitDto);
    }


    @Override
    public ResponseDefault getLawsuitByClaimantName(String claimantName) {
        if(claimantName.length() < 3){
            return new ResponseDefault(HttpStatus.BAD_REQUEST,"Enter a name with at least 3 characters!", null);
        }
        var lawsuitsDto = lawsuitRepository.findLawsuitByClaimantName(claimantName);
        if(lawsuitsDto.isEmpty()){
            return new ResponseDefault(HttpStatus.NOT_FOUND, "No results found!", null);
        }
        var progress = progressRepository.findProgressByClaimantName(claimantName);
        var annotations = annotationRepository.findAnnotationsByClaimantName(claimantName);
        var defendants = defendantRepository.findDefendantsByClaimantName(claimantName);
        var attorneys = attorneyRepository.findAttorneysByClaimantName(claimantName);

        for (int i = 0; i < lawsuitsDto.size(); i++) {
            lawsuitsDto.get(i).setProgress(progress);
            lawsuitsDto.get(i).setAnnotations(annotations);
            lawsuitsDto.get(i).setDefendants(defendants);
            lawsuitsDto.get(i).setAttorneys(attorneys);
        }
        return new ResponseDefault(HttpStatus.OK, "Search carried out!", lawsuitsDto);
    }

    @Override
    public SaveOrUpdateResponseDefault saveLawsuit(LawsuitRequestDto lawsuitRequestDto) {
        var validation = saveLawsuitValidation(lawsuitRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, validation);
        }
        Lawsuit lawsuit = createLawsuitFromDto(lawsuitRequestDto);
        lawsuit.setCreatedAt(LocalDateTime.now());
        lawsuitRepository.save(lawsuit);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Successfully created!"));
    }

    @Override
    public SaveOrUpdateResponseDefault updateLawsuit(Long id, LawsuitRequestDto lawsuitRequestDto) {
        var lawsuitToUpdate = lawsuitRepository.findById(id).orElse(null);
        if(lawsuitToUpdate == null){
            return new SaveOrUpdateResponseDefault(HttpStatus.NOT_FOUND, Collections.singletonList("No records found for this ID!"));
        }
        var validation = updateLawsuitValidation(id, lawsuitRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.CONFLICT, validation);
        }
        Lawsuit existingLawsuit = lawsuitToUpdate;
        updateLawsuitFields(existingLawsuit, lawsuitRequestDto);
        existingLawsuit.setUpdatedAt(LocalDateTime.now());
        lawsuitRepository.save(existingLawsuit);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }

    public List<String> saveLawsuitValidation(LawsuitRequestDto lawsuitRequestDto){
        List errors = new ArrayList<>();
        if(cleanseNumericInput(lawsuitRequestDto.getLawsuitNumber()).length() > 22){
            errors.add("The lawsuit number must have a maximum of 22 characters!");
        }
        if(lawsuitRepository.existsByLawsuitNumber(cleanseNumericInput(lawsuitRequestDto.getLawsuitNumber()))){
            errors.add("This lawsuit number is already registered!");
        }
        if(!claimantRepository.existsByCpf(cleanseNumericInput(lawsuitRequestDto.getCpfClaimant()))){
            errors.add("CPF: " + lawsuitRequestDto.getCpfClaimant() + " not registered for any claimant");
        }
        for (int i = 0; i < lawsuitRequestDto.getCpfCnpjDefendants().size(); i++) {
            if(!defendantRepository.existsByCpfCnpj(cleanseNumericInput(lawsuitRequestDto.getCpfCnpjDefendants().get(i)))){
                errors.add("CPF/CNPJ: "+ lawsuitRequestDto.getCpfCnpjDefendants().get(i) + " not registered for any defendant");
            }
        }
        return errors;
    }

    public List<String> updateLawsuitValidation(Long id, LawsuitRequestDto lawsuitRequestDto){
        List<String> errors = new ArrayList<>();
        if(cleanseNumericInput(lawsuitRequestDto.getLawsuitNumber()).length() > 22){
            errors.add("The lawsuit number must have a maximum of 22 characters!");
        }
        if(!claimantRepository.existsByCpf(cleanseNumericInput(lawsuitRequestDto.getCpfClaimant()))){
            errors.add("CPF: " + lawsuitRequestDto.getCpfClaimant() + " not registered for any claimant");
        }
        for (int i = 0; i < lawsuitRequestDto.getCpfCnpjDefendants().size(); i++) {
            if(!defendantRepository.existsByCpfCnpj(cleanseNumericInput(lawsuitRequestDto.getCpfCnpjDefendants().get(i)))){
                errors.add("CPF/CNPJ: "+ lawsuitRequestDto.getCpfCnpjDefendants().get(i) + " not registered for any defendant");
            }
        }
        var lawsuitFormatted = cleanseNumericInput(lawsuitRequestDto.getLawsuitNumber());
        var lawsuitNumberExists = lawsuitRepository.existsByLawsuitNumber(lawsuitFormatted);
        if(lawsuitNumberExists){
            Long lawsuitExistentId = lawsuitRepository.findByLawsuitNumberEquals(lawsuitFormatted).getId();
            if(id != lawsuitExistentId){
                errors.add("This Lawsuit Number is already registered!");
            }
        }
        return errors;
    }

    public String cleanseNumericInput(String input){
        return input.replaceAll("[^0-9]", "");
    }

    public Lawsuit createLawsuitFromDto(LawsuitRequestDto lawsuitRequestDto) {
        Lawsuit lawsuit = new Lawsuit();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lawsuit.setLawsuitNumber(cleanseNumericInput(lawsuitRequestDto.getLawsuitNumber()));
        lawsuit.setCivilCourt(cleanseNumericInput(lawsuitRequestDto.getCivilCourt()));
        lawsuit.setDistributionDate(LocalDate.parse(lawsuitRequestDto.getDistributionDate(), formatterDate));
        lawsuit.setValueCase(lawsuitRequestDto.getValueCase());

        if(lawsuitRequestDto.getLawsuitPhaseId() == null || lawsuitPhaseRepository.existsByLawsuitPhaseId(lawsuitRequestDto.getLawsuitPhaseId()) == false){
            var lawsuitPhase = lawsuitPhaseRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Lawsuit Phase not found"));
            lawsuit.setLawsuitPhase(lawsuitPhase);
        } else {
            var lawsuitPhase = lawsuitPhaseRepository.findById(lawsuitRequestDto.getLawsuitPhaseId())
                    .orElseThrow(() -> new EntityNotFoundException("Lawsuit Phase not found"));
            lawsuit.setLawsuitPhase(lawsuitPhase);
        }

        if(lawsuitRequestDto.getLawsuitStatusId() == null || lawsuitStatusRepository.existsByLawsuitStatusId(lawsuitRequestDto.getLawsuitPhaseId()) == false){
            var lawsuitStatus = lawsuitStatusRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Lawsuit Status not found"));
            lawsuit.setLawsuitStatus(lawsuitStatus);
        } else {
            var lawsuitStatus = lawsuitStatusRepository.findById(lawsuitRequestDto.getLawsuitStatusId())
                    .orElseThrow(() -> new EntityNotFoundException("Lawsuit Status not found"));
            lawsuit.setLawsuitStatus(lawsuitStatus);
        }

        if(lawsuitRequestDto.getLocationId() == null || locationRepository.existsByLocationId(lawsuitRequestDto.getLocationId()) == false){
            var location = locationRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException("Location not found"));
            lawsuit.setLocation(location);
        } else {
            var location = locationRepository.findById(lawsuitRequestDto.getLocationId()).orElseThrow(() -> new EntityNotFoundException("Location not found"));
            lawsuit.setLocation(location);
        }

        var claimant = claimantRepository.findByCpfEquals(cleanseNumericInput(lawsuitRequestDto.getCpfClaimant()));
        lawsuit.setClaimant(claimant);

        List defendants = new ArrayList<>();
        for (int i = 0; i < lawsuitRequestDto.getCpfCnpjDefendants().size(); i++) {
            var defendant = defendantRepository.findByCpfCnpjEquals(cleanseNumericInput(lawsuitRequestDto.getCpfCnpjDefendants().get(i)));
            defendants.add(defendant);
        }
        lawsuit.setDefendants(defendants);

        List attorneys = new ArrayList<>();
        for (int i = 0; i < lawsuitRequestDto.getAttorneys().size(); i++) {
            var attorney = attorneyRepository.findById(lawsuitRequestDto.getAttorneys().get(i)).orElse(null);
            attorneys.add(attorney);
        }
        lawsuit.setAttorneys(attorneys);

        return lawsuit;
    }

    private LocalDate parseLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void updateLawsuitFields(Lawsuit existingLawsuit, LawsuitRequestDto lawsuitRequestDto){
        existingLawsuit.setLawsuitNumber(cleanseNumericInput(lawsuitRequestDto.getLawsuitNumber()));
        existingLawsuit.setCivilCourt(lawsuitRequestDto.getCivilCourt());
        existingLawsuit.setDistributionDate(parseLocalDate(lawsuitRequestDto.getDistributionDate()));
        existingLawsuit.setValueCase(lawsuitRequestDto.getValueCase());
        if(lawsuitRequestDto.getLawsuitPhaseId() == null || lawsuitPhaseRepository.existsByLawsuitPhaseId(lawsuitRequestDto.getLawsuitPhaseId()) == false){
            var lawsuitPhase = lawsuitPhaseRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Lawsuit Phase not found"));
            existingLawsuit.setLawsuitPhase(lawsuitPhase);
        } else {
            var lawsuitPhase = lawsuitPhaseRepository.findById(lawsuitRequestDto.getLawsuitPhaseId())
                    .orElseThrow(() -> new EntityNotFoundException("Lawsuit Phase not found"));
            existingLawsuit.setLawsuitPhase(lawsuitPhase);
        }

        if(lawsuitRequestDto.getLawsuitStatusId() == null || lawsuitStatusRepository.existsByLawsuitStatusId(lawsuitRequestDto.getLawsuitPhaseId()) == false){
            var lawsuitStatus = lawsuitStatusRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Lawsuit Status not found"));
            existingLawsuit.setLawsuitStatus(lawsuitStatus);
        } else {
            var lawsuitStatus = lawsuitStatusRepository.findById(lawsuitRequestDto.getLawsuitStatusId())
                    .orElseThrow(() -> new EntityNotFoundException("Lawsuit Status not found"));
            existingLawsuit.setLawsuitStatus(lawsuitStatus);
        }

        if(lawsuitRequestDto.getLocationId() == null || locationRepository.existsByLocationId(lawsuitRequestDto.getLocationId()) == false){
            var location = locationRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException("Location not found"));
            existingLawsuit.setLocation(location);
        } else {
            var location = locationRepository.findById(lawsuitRequestDto.getLocationId()).orElseThrow(() -> new EntityNotFoundException("Location not found"));
            existingLawsuit.setLocation(location);
        }
        var claimantId = claimantRepository.findClaimantByCpf(cleanseNumericInput(lawsuitRequestDto.getCpfClaimant())).get().getClaimantId();
        var claimant = claimantRepository.findById(claimantId).orElseThrow(() -> new EntityNotFoundException("Claimant not found"));
        existingLawsuit.setClaimant(claimant);

        List defendants = new ArrayList<>();
        for (int i = 0; i < lawsuitRequestDto.getCpfCnpjDefendants().size(); i++) {
            var cpfCnpjFormatted = lawsuitRequestDto.getCpfCnpjDefendants().get(i);
            var defendant = defendantRepository.findByCpfCnpjEquals(cpfCnpjFormatted);
            defendants.add(defendant);
        }
        existingLawsuit.setDefendants(defendants);

        List attorneys = new ArrayList<>();
        for (int i = 0; i < lawsuitRequestDto.getAttorneys().size(); i++) {
            var attorney = attorneyRepository.findById(lawsuitRequestDto.getAttorneys().get(i)).orElseThrow(() -> new EntityNotFoundException("Attorney not found"));
            attorneys.add(attorney);
        }
        existingLawsuit.setAttorneys(attorneys);
    }
}
