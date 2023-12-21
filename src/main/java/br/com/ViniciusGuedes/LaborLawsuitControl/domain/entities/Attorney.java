package br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attorney")
public class Attorney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long attorneyId;

    @Column(length = 150, nullable = false, name = "attorney_name")
    private String attorneyName;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(length = 20, nullable = false, unique = true, name = "oab_number")
    private String oabNumber;

    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "attorneys")
    private List<Lawsuit> lawsuits = new ArrayList<>();

    public Attorney() {
    }

    public Attorney(Long attorneyId, String attorneyName, String cpf, String oabNumber, LocalDateTime createdAt, LocalDateTime updatedAt, List<Lawsuit> lawsuits) {
        this.attorneyId = attorneyId;
        this.attorneyName = attorneyName;
        this.cpf = cpf;
        this.oabNumber = oabNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lawsuits = lawsuits;
    }

    public Long getAttorneyId() {
        return attorneyId;
    }

    public void setAttorneyId(Long attorneyId) {
        this.attorneyId = attorneyId;
    }

    public String getAttorneyName() {
        return attorneyName;
    }

    public void setAttorneyName(String attorneyName) {
        this.attorneyName = attorneyName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getOabNumber() {
        return oabNumber;
    }

    public void setOabNumber(String oabNumber) {
        this.oabNumber = oabNumber;
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

    public List<Lawsuit> getLawsuits() {
        return lawsuits;
    }

    public void setLawsuits(List<Lawsuit> lawsuits) {
        this.lawsuits = lawsuits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attorney)) return false;

        Attorney attorney = (Attorney) o;

        if (attorneyId != null ? !attorneyId.equals(attorney.attorneyId) : attorney.attorneyId != null) return false;
        if (attorneyName != null ? !attorneyName.equals(attorney.attorneyName) : attorney.attorneyName != null)
            return false;
        if (cpf != null ? !cpf.equals(attorney.cpf) : attorney.cpf != null) return false;
        if (oabNumber != null ? !oabNumber.equals(attorney.oabNumber) : attorney.oabNumber != null) return false;
        if (createdAt != null ? !createdAt.equals(attorney.createdAt) : attorney.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(attorney.updatedAt) : attorney.updatedAt != null) return false;
        return lawsuits != null ? lawsuits.equals(attorney.lawsuits) : attorney.lawsuits == null;
    }

    @Override
    public int hashCode() {
        int result = attorneyId != null ? attorneyId.hashCode() : 0;
        result = 31 * result + (attorneyName != null ? attorneyName.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (oabNumber != null ? oabNumber.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (lawsuits != null ? lawsuits.hashCode() : 0);
        return result;
    }
}
