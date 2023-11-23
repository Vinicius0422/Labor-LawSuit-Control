package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.ResponseDefault;
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
    public ResponseDefault saveProgress(ProgressRequestDto progressRequestDto) {
        var validation = validateProgressRequest(progressRequestDto);
        if(!validation.isEmpty()){
            return new ResponseDefault(HttpStatus.BAD_REQUEST, validation);
        }
        var newProgress = formatProgressDtoToEntity(progressRequestDto);
        newProgress.setCreatedAt(LocalDateTime.now());
        progressRepository.save(newProgress);
        return new ResponseDefault(HttpStatus.CREATED, Collections.singletonList("Saved successfully!"));
    }

    @Override
    public ResponseDefault updateProgress(Long id, ProgressRequestDto progressRequestDto) {
        Progress progressToUpdate = progressRepository.findById(id).orElse(null);
        if(progressToUpdate == null){
            return new ResponseDefault(HttpStatus.NOT_FOUND, Collections.singletonList("No results for this ID!"));
        }
        var validation = validateProgressRequest(progressRequestDto);
        if(!validation.isEmpty()){
            return new ResponseDefault(HttpStatus.BAD_REQUEST, validation);
        }
        var progress = formatProgressDtoToEntity(progressRequestDto);
        progressToUpdate.setProgressDate(progress.getProgressDate());
        progressToUpdate.setExpectedDate(progress.getExpectedDate());
        progressToUpdate.setExpectedTime(progress.getExpectedTime());
        progressToUpdate.setDescription(progress.getDescription());
        progressToUpdate.setUpdatedAt(LocalDateTime.now());
        progressRepository.save(progressToUpdate);
        return new ResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }


    public List<String> validateProgressRequest(ProgressRequestDto progressRequestDto){
        List validation = new ArrayList<>();
        Long lawsuitId = progressRequestDto.getLawsuitId();
        Long lawsuitPhaseId = progressRequestDto.getLawsuitPhaseId();
        if(lawsuitId == null){
            validation.add("Lawsuit Id is required!");
        }
        if(lawsuitPhaseId == null){
            validation.add("LawsuitPhase Id is required!");
        }
        return validation;
    }

    public Progress formatProgressDtoToEntity(ProgressRequestDto progressRequestDto){
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        Lawsuit lawsuit = lawsuitRepository.findById(progressRequestDto.getLawsuitId()).orElse(null);
        LawsuitPhase lawsuitPhase = lawsuitPhaseRepository.findById(progressRequestDto.getLawsuitPhaseId()).orElse(null);
        if(lawsuit == null || lawsuitPhase == null){
            return null;
        }
        Progress progress = new Progress();
        progress.setProgressDate(LocalDate.parse(progressRequestDto.getProgressDate(), formatterDate));
        progress.setExpectedDate(LocalDate.parse(progressRequestDto.getExpectedDate(), formatterDate));
        progress.setExpectedTime(LocalTime.parse(progressRequestDto.getExpectedTime(), formatterTime));
        progress.setDescription(progressRequestDto.getDescription());
        progress.setLawsuitId(lawsuit);
        progress.setLawsuitPhaseId(lawsuitPhase);

        return progress;
    }
}
