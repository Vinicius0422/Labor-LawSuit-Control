package br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lawsuit")
public class Lawsuit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Long lawsuitId;

    @Column(length = 22, nullable = false, unique = true, name = "lawsuit_number")
    private String lawsuitNumber;

    @Column(length = 5, nullable = false, name = "civil_court")
    private String civilCourt;

    @Column(nullable = false, name = "distribution_date", columnDefinition = "DATE")
    private LocalDate distributionDate;

    @Column(length = 10, name = "value_case", columnDefinition = "DECIMAL(10,2)")
    private Double valueCase;

    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "lawsuitphase_id")
    private LawsuitPhase lawsuitPhase;

    @ManyToOne
    @JoinColumn(name = "lawsuitstatus_id")
    private LawsuitStatus lawsuitStatus;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "claimant_id")
    private Claimant claimant;

    @JsonIgnore
    @OneToMany(mappedBy = "lawsuit", fetch = FetchType.EAGER)
    private List<Progress> progress = new ArrayList<>();

    @OneToMany(mappedBy = "lawsuit", fetch = FetchType.EAGER)
    private List<Annotation> annotations = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "lawsuit_defendant",
            joinColumns = @JoinColumn(name = "lawsuit_id"),
            inverseJoinColumns = @JoinColumn(name = "defendant_id"))
    private List<Defendant> defendants = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "lawsuit_attorney",
            joinColumns = @JoinColumn(name = "lawsuit_id"),
            inverseJoinColumns = @JoinColumn(name = "attorney_id"))
    private List<Attorney> attorneys = new ArrayList<>();

    public Lawsuit() {
    }

    public Lawsuit(String lawsuitNumber, String civilCourt, LocalDate distributionDate, Double valueCase, LocalDateTime createdAt, LocalDateTime updatedAt,
                   LawsuitPhase lawsuitPhase, LawsuitStatus lawsuitStatus, Location location, Claimant claimant, List<Progress> progress, List<Annotation> annotations,
                   List<Defendant> defendants, List<Attorney> attorneys) {
        this.lawsuitNumber = lawsuitNumber;
        this.civilCourt = civilCourt;
        this.distributionDate = distributionDate;
        this.valueCase = valueCase;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lawsuitPhase = lawsuitPhase;
        this.lawsuitStatus = lawsuitStatus;
        this.location = location;
        this.claimant = claimant;
        this.progress = progress;
        this.annotations = annotations;
        this.defendants = defendants;
        this.attorneys = attorneys;
    }

    public Long getId() {
        return lawsuitId;
    }

    public void setId(Long lawsuitId) {
        this.lawsuitId = lawsuitId;
    }

    public String getLawsuitNumber() {
        return lawsuitNumber;
    }

    public void setLawsuitNumber(String lawsuitNumber) {
        this.lawsuitNumber = lawsuitNumber;
    }

    public String getCivilCourt() {
        return civilCourt;
    }

    public void setCivilCourt(String civilCourt) {
        this.civilCourt = civilCourt;
    }

    public LocalDate getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(LocalDate distributionDate) {
        this.distributionDate = distributionDate;
    }

    public Double getValueCase() {
        return valueCase;
    }

    public void setValueCase(Double valueCase) {
        this.valueCase = valueCase;
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

    public LawsuitPhase getLawsuitPhase() {
        return lawsuitPhase;
    }

    public void setLawsuitPhase(LawsuitPhase lawsuitPhase) {
        this.lawsuitPhase = lawsuitPhase;
    }

    public LawsuitStatus getLawsuitStatus() {
        return lawsuitStatus;
    }

    public void setLawsuitStatus(LawsuitStatus lawsuitStatus) {
        this.lawsuitStatus = lawsuitStatus;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Claimant getClaimant() {
        return claimant;
    }

    public void setClaimant(Claimant claimant) {
        this.claimant = claimant;
    }

    public List<Progress> getProgress() {
        return progress;
    }

    public void setProgress(List<Progress> progress) {
        this.progress = progress;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public List<Defendant> getDefendants() {
        return defendants;
    }

    public void setDefendants(List<Defendant> defendants) {
        this.defendants = defendants;
    }

    public List<Attorney> getAttorneys() {
        return attorneys;
    }

    public void setAttorneys(List<Attorney> attorneys) {
        this.attorneys = attorneys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lawsuit)) return false;

        Lawsuit lawsuit = (Lawsuit) o;

        if (lawsuitId != null ? !lawsuitId.equals(lawsuit.lawsuitId) : lawsuit.lawsuitId != null) return false;
        if (lawsuitNumber != null ? !lawsuitNumber.equals(lawsuit.lawsuitNumber) : lawsuit.lawsuitNumber != null)
            return false;
        if (civilCourt != null ? !civilCourt.equals(lawsuit.civilCourt) : lawsuit.civilCourt != null) return false;
        if (distributionDate != null ? !distributionDate.equals(lawsuit.distributionDate) : lawsuit.distributionDate != null)
            return false;
        if (valueCase != null ? !valueCase.equals(lawsuit.valueCase) : lawsuit.valueCase != null) return false;
        if (createdAt != null ? !createdAt.equals(lawsuit.createdAt) : lawsuit.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(lawsuit.updatedAt) : lawsuit.updatedAt != null) return false;
        if (lawsuitPhase != null ? !lawsuitPhase.equals(lawsuit.lawsuitPhase) : lawsuit.lawsuitPhase != null)
            return false;
        if (lawsuitStatus != null ? !lawsuitStatus.equals(lawsuit.lawsuitStatus) : lawsuit.lawsuitStatus != null) return false;
        if (location != null ? !location.equals(lawsuit.location) : lawsuit.location != null) return false;
        if (claimant != null ? !claimant.equals(lawsuit.claimant) : lawsuit.claimant != null) return false;
        if (progress != null ? !progress.equals(lawsuit.progress) : lawsuit.progress != null) return false;
        if (annotations != null ? !annotations.equals(lawsuit.annotations) : lawsuit.annotations != null) return false;
        if (defendants != null ? !defendants.equals(lawsuit.defendants) : lawsuit.defendants != null) return false;
        return attorneys != null ? attorneys.equals(lawsuit.attorneys) : lawsuit.attorneys == null;
    }

    @Override
    public int hashCode() {
        int result = lawsuitId != null ? lawsuitId.hashCode() : 0;
        result = 31 * result + (lawsuitNumber != null ? lawsuitNumber.hashCode() : 0);
        result = 31 * result + (civilCourt != null ? civilCourt.hashCode() : 0);
        result = 31 * result + (distributionDate != null ? distributionDate.hashCode() : 0);
        result = 31 * result + (valueCase != null ? valueCase.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (lawsuitPhase != null ? lawsuitPhase.hashCode() : 0);
        result = 31 * result + (lawsuitStatus != null ? lawsuitStatus.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (claimant != null ? claimant.hashCode() : 0);
        result = 31 * result + (progress != null ? progress.hashCode() : 0);
        result = 31 * result + (annotations != null ? annotations.hashCode() : 0);
        result = 31 * result + (defendants != null ? defendants.hashCode() : 0);
        result = 31 * result + (attorneys != null ? attorneys.hashCode() : 0);
        return result;
    }
}