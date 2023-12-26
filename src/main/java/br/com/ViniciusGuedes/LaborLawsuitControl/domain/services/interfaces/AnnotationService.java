package br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.interfaces;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.SaveOrUpdateResponseDefault;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation.AnnotationRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AnnotationService {

    SaveOrUpdateResponseDefault saveAnnotation(AnnotationRequestDto annotationRequestDto);

    SaveOrUpdateResponseDefault updateAnnotation(Long id, AnnotationRequestDto annotationRequestDto);
}
