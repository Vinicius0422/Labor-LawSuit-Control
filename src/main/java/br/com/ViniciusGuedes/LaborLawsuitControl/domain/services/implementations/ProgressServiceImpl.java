package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress.ProgressRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Lawsuit;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.LawsuitPhase;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Progress;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.ProgressService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LawsuitPhaseRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LawsuitRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private LawsuitRepository lawsuitRepository;

    @Autowired
    private LawsuitPhaseRepository lawsuitPhaseRepository;

    @Override
    @Transactional
    public SaveOrUpdateResponseDefault saveProgress(ProgressRequestDto progressRequestDto) {
        var validation = validateProgressRequest(progressRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.UNPROCESSABLE_ENTITY, validation);
        }
        var newProgress = formatProgressDtoToEntity(progressRequestDto);
        newProgress.setCreatedAt(LocalDateTime.now());
        progressRepository.save(newProgress);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Saved successfully!"));
    }

    @Override
    @Transactional
    public SaveOrUpdateResponseDefault updateProgress(Long id, ProgressRequestDto progressRequestDto) {
        Progress progressToUpdate = progressRepository.findById(id).orElse(null);
        if(progressToUpdate == null){
            return new SaveOrUpdateResponseDefault(HttpStatus.NOT_FOUND, Collections.singletonList("No results for this ID!"));
        }
        var validation = validateProgressRequest(progressRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.UNPROCESSABLE_ENTITY, validation);
        }
        var progress = formatProgressDtoToEntity(progressRequestDto);
        progressToUpdate.setProgressDate(progress.getProgressDate());
        progressToUpdate.setExpectedDate(progress.getExpectedDate());
        progressToUpdate.setExpectedTime(progress.getExpectedTime());
        progressToUpdate.setDescription(progress.getDescription());
        progressToUpdate.setUpdatedAt(LocalDateTime.now());
        progressRepository.save(progressToUpdate);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }


    public List<String> validateProgressRequest(ProgressRequestDto progressRequestDto){
        List errors = new ArrayList<>();
        Long lawsuitId = progressRequestDto.getLawsuitId();
        Long lawsuitPhaseId = progressRequestDto.getLawsuitPhaseId();
        if(lawsuitId == null || lawsuitId.equals("")){
            errors.add("Lawsuit Id is required!");
        }
        if(!lawsuitRepository.existsByLawsuitId(lawsuitId)){
            errors.add("This lawsuit not exists!");
        }
        if(lawsuitPhaseId == null || lawsuitPhaseId.equals("")){
            errors.add("LawsuitPhase Id is required!");
        }
        if(!lawsuitPhaseRepository.existsByLawsuitPhaseId(lawsuitPhaseId)){
            errors.add("This lawsuit phase not exists!");
        }
        return errors;
    }

    public Progress formatProgressDtoToEntity(ProgressRequestDto progressRequestDto){
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        Lawsuit lawsuit = lawsuitRepository.findById(progressRequestDto.getLawsuitId()).orElse(null);
        LawsuitPhase lawsuitPhase = lawsuitPhaseRepository.findById(progressRequestDto.getLawsuitPhaseId()).orElse(null);
        Progress progress = new Progress();
        progress.setProgressDate(LocalDate.parse(progressRequestDto.getProgressDate(), formatterDate));
        progress.setExpectedDate(LocalDate.parse(progressRequestDto.getExpectedDate(), formatterDate));
        progress.setExpectedTime(LocalTime.parse(progressRequestDto.getExpectedTime(), formatterTime));
        progress.setDescription(progressRequestDto.getDescription());
        progress.setLawsuit(lawsuit);
        progress.setLawsuitPhase(lawsuitPhase);

        return progress;
    }
}
