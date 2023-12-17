package br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "annotation")
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long annotationId;

    @Column(name = "annotation_date", nullable = false)
    private LocalDate annotationDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "lawsuit_id")
    private Lawsuit lawsuit;

    public Annotation() {
    }

    public Annotation(LocalDate annotationDate, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Lawsuit lawsuit) {
        this.annotationDate = annotationDate;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lawsuit = lawsuit;
    }

    public Long getId() {
        return annotationId;
    }

    public void setAnnotationId(Long annotationId) {
        this.annotationId = annotationId;
    }

    public LocalDate getAnnotationDate() {
        return annotationDate;
    }

    public void setAnnotationDate(LocalDate annotationDate) {
        this.annotationDate = annotationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Lawsuit getLawsuit() {
        return lawsuit;
    }

    public void setLawsuit(Lawsuit lawsuit) {
        this.lawsuit = lawsuit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Annotation)) return false;

        Annotation that = (Annotation) o;

        if (annotationId != null ? !annotationId.equals(that.annotationId) : that.annotationId != null) return false;
        if (annotationDate != null ? !annotationDate.equals(that.annotationDate) : that.annotationDate != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        return lawsuit != null ? lawsuit.equals(that.lawsuit) : that.lawsuit == null;
    }

    @Override
    public int hashCode() {
        int result = annotationId != null ? annotationId.hashCode() : 0;
        result = 31 * result + (annotationDate != null ? annotationDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (lawsuit != null ? lawsuit.hashCode() : 0);
        return result;
    }
}
