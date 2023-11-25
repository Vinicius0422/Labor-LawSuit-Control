package br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "progress")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long progressId;

    @Column(nullable = false, name = "progress_date", columnDefinition = "DATE")
    private LocalDate progressDate;

    @Column(name = "expected_date", columnDefinition = "DATE")
    private LocalDate expectedDate;

    @Column(name = "expected_time")
    private LocalTime expectedTime;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "lawsuitphase_id")
    private LawsuitPhase lawsuitPhaseId;

    @ManyToOne
    @JoinColumn(name = "lawsuit_id")
    private Lawsuit lawsuitId;

    public Progress() {
    }

    public Progress(LocalDate progressDate, LocalDate expectedDate, LocalTime expectedTime, String description,
                    LocalDateTime createdAt, LocalDateTime updatedAt, LawsuitPhase lawsuitPhaseId, Lawsuit lawsuitId) {
        this.progressDate = progressDate;
        this.expectedDate = expectedDate;
        this.expectedTime = expectedTime;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lawsuitPhaseId = lawsuitPhaseId;
        this.lawsuitId = lawsuitId;
    }

    public Long getId() {
        return progressId;
    }

    public void setId(Long progressId) {
        this.progressId = progressId;
    }

    public LocalDate getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(LocalDate progressDate) {
        this.progressDate = progressDate;
    }

    public LocalDate getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(LocalDate expectedDate) {
        this.expectedDate = expectedDate;
    }

    public LocalTime getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(LocalTime expectedTime) {
        this.expectedTime = expectedTime;
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

    public LawsuitPhase getLawsuitPhaseId() {
        return lawsuitPhaseId;
    }

    public void setLawsuitPhaseId(LawsuitPhase lawsuitPhaseId) {
        this.lawsuitPhaseId = lawsuitPhaseId;
    }

    public Lawsuit getLawsuitId() {
        return lawsuitId;
    }

    public void setLawsuitId(Lawsuit lawsuitId) {
        this.lawsuitId = lawsuitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Progress)) return false;

        Progress progress = (Progress) o;

        if (progressId != null ? !progressId.equals(progress.progressId) : progress.progressId != null) return false;
        if (progressDate != null ? !progressDate.equals(progress.progressDate) : progress.progressDate != null)
            return false;
        if (expectedDate != null ? !expectedDate.equals(progress.expectedDate) : progress.expectedDate != null)
            return false;
        if (expectedTime != null ? !expectedTime.equals(progress.expectedTime) : progress.expectedTime != null)
            return false;
        if (description != null ? !description.equals(progress.description) : progress.description != null)
            return false;
        if (createdAt != null ? !createdAt.equals(progress.createdAt) : progress.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(progress.updatedAt) : progress.updatedAt != null) return false;
        if (lawsuitPhaseId != null ? !lawsuitPhaseId.equals(progress.lawsuitPhaseId) : progress.lawsuitPhaseId != null)
            return false;
        return lawsuitId != null ? lawsuitId.equals(progress.lawsuitId) : progress.lawsuitId == null;
    }

    @Override
    public int hashCode() {
        int result = progressId != null ? progressId.hashCode() : 0;
        result = 31 * result + (progressDate != null ? progressDate.hashCode() : 0);
        result = 31 * result + (expectedDate != null ? expectedDate.hashCode() : 0);
        result = 31 * result + (expectedTime != null ? expectedTime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (lawsuitPhaseId != null ? lawsuitPhaseId.hashCode() : 0);
        result = 31 * result + (lawsuitId != null ? lawsuitId.hashCode() : 0);
        return result;
    }
}
