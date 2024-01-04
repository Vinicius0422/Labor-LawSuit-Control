package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationRequestDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Annotation;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities.Lawsuit;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces.AnnotationService;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.AnnotationRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LawsuitRepository;
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
public class AnnotationServiceImpl implements AnnotationService {

    @Autowired
    private AnnotationRepository annotationRepository;
    @Autowired
    private LawsuitRepository lawsuitRepository;

    @Override
    public SaveOrUpdateResponseDefault saveAnnotation(AnnotationRequestDto annotationRequestDto) {
        var validation = validateAnnotationRequest(annotationRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.BAD_REQUEST, validation);
        }
        var annotation = formatAnnotationDtoToEntity(annotationRequestDto);
        annotation.setCreatedAt(LocalDateTime.now());
        annotationRepository.save(annotation);
        return new SaveOrUpdateResponseDefault(HttpStatus.CREATED, Collections.singletonList("Saved successfully!"));
    }

    @Override
    public SaveOrUpdateResponseDefault updateAnnotation(Long id, AnnotationRequestDto annotationRequestDto){
        Annotation annotationToUpdate = annotationRepository.findById(id).orElse(null);
        if(annotationToUpdate == null){
            return new SaveOrUpdateResponseDefault(HttpStatus.NOT_FOUND, Collections.singletonList("No results for this ID!"));
        }
        var validation = validateAnnotationRequest(annotationRequestDto);
        if(!validation.isEmpty()){
            return new SaveOrUpdateResponseDefault(HttpStatus.BAD_REQUEST, validation);
        }
        var annotation = formatAnnotationDtoToEntity(annotationRequestDto);
        annotationToUpdate.setAnnotationDate(annotation.getAnnotationDate());
        annotationToUpdate.setDescription(annotation.getDescription());
        annotationToUpdate.setUpdatedAt(LocalDateTime.now());
        annotationRepository.save(annotationToUpdate);
        return new SaveOrUpdateResponseDefault(HttpStatus.OK, Collections.singletonList("Updated successfully!"));
    }

    public List<String> validateAnnotationRequest(AnnotationRequestDto annotationRequestDto){
        List errors = new ArrayList();
        if(annotationRequestDto.getAnnotationDate().isBlank() || annotationRequestDto.getAnnotationDate() == null){
            errors.add("The field Annotation Date is required!");
        }
        if(annotationRequestDto.getDescription().isBlank() || annotationRequestDto.getDescription() == null){
            errors.add("The field description is required!");
        }
        if(annotationRequestDto.getLawsuitId().equals("") || annotationRequestDto.getLawsuitId() == null){
            errors.add("Please inform which lawsuit this note is for!");
        }
        if(!lawsuitRepository.existsByLawsuitId(annotationRequestDto.getLawsuitId())){
            errors.add("This lawsuit phase not exists!");
        }
        return errors;
    }

    public Annotation formatAnnotationDtoToEntity(AnnotationRequestDto annotationRequestDto){
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Lawsuit lawsuit = lawsuitRepository.findById(annotationRequestDto.getLawsuitId()).orElse(null);
        Annotation annotation = new Annotation();
        annotation.setAnnotationDate(LocalDate.parse(annotationRequestDto.getAnnotationDate(), formatterDate));
        annotation.setDescription(annotationRequestDto.getDescription());
        annotation.setLawsuit(lawsuit);

        return annotation;
    }
}