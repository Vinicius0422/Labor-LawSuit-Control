package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.annotation;

public class AnnotationRequestDto {

    private String annotationDate;
    private String description;
    private Long lawsuitId;

    public AnnotationRequestDto() {
    }

    public AnnotationRequestDto(String annotationDate, String description, Long lawsuitId) {
        this.annotationDate = annotationDate;
        this.description = description;
        this.lawsuitId = lawsuitId;
    }

    public String getAnnotationDate() {
        return annotationDate;
    }

    public void setAnnotationDate(String annotationDate) {
        this.annotationDate = annotationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLawsuitId() {
        return lawsuitId;
    }

    public void setLawsuitId(Long lawsuitId) {
        this.lawsuitId = lawsuitId;
    }
}
