package br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "defendant")
public class Defendant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long defendantId;

    @Column(length = 150, nullable = false, name = "defendant_name")
    private String defendantName;

    @Column(name = "person_type", columnDefinition = "ENUM('Fisica', 'Juridica')", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @Column(length = 14, nullable = false, unique = true, name = "cpf_cnpj")
    private String cpfCnpj;

    private String address;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String neighborhood;

    @Column(length = 2)
    private String uf;

    @Column(length = 8)
    private String cep;

    private String contact;

    @Column(length = 150)
    private String email;

    @Column(nullable = false, name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "defendants")
    private List<Lawsuit> lawsuits = new ArrayList<>();

    public Defendant() {
    }

    public Defendant(String defendantName, PersonType personType, String cpfCnpj, String address, String city, String neighborhood, String uf, String cep,
                     String contact, String email, LocalDateTime createdAt, LocalDateTime updatedAt, List<Lawsuit> lawsuits) {
        this.defendantName = defendantName;
        this.personType = personType;
        this.cpfCnpj = cpfCnpj;
        this.address = address;
        this.city = city;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.cep = cep;
        this.contact = contact;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lawsuits = lawsuits;
    }

    public Long getDefendantId() {
        return defendantId;
    }

    public void setDefendantId(Long defendantId) {
        this.defendantId = defendantId;
    }

    public String getDefendantName() {
        return defendantName;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof Defendant)) return false;

        Defendant defendant = (Defendant) o;

        if (defendantId != null ? !defendantId.equals(defendant.defendantId) : defendant.defendantId != null)
            return false;
        if (defendantName != null ? !defendantName.equals(defendant.defendantName) : defendant.defendantName != null)
            return false;
        if (personType != defendant.personType) return false;
        if (cpfCnpj != null ? !cpfCnpj.equals(defendant.cpfCnpj) : defendant.cpfCnpj != null) return false;
        if (address != null ? !address.equals(defendant.address) : defendant.address != null) return false;
        if (city != null ? !city.equals(defendant.city) : defendant.city != null) return false;
        if (neighborhood != null ? !neighborhood.equals(defendant.neighborhood) : defendant.neighborhood != null)
            return false;
        if (uf != null ? !uf.equals(defendant.uf) : defendant.uf != null) return false;
        if (cep != null ? !cep.equals(defendant.cep) : defendant.cep != null) return false;
        if (contact != null ? !contact.equals(defendant.contact) : defendant.contact != null) return false;
        if (email != null ? !email.equals(defendant.email) : defendant.email != null) return false;
        if (createdAt != null ? !createdAt.equals(defendant.createdAt) : defendant.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(defendant.updatedAt) : defendant.updatedAt != null) return false;
        return lawsuits != null ? lawsuits.equals(defendant.lawsuits) : defendant.lawsuits == null;
    }

    @Override
    public int hashCode() {
        int result = defendantId != null ? defendantId.hashCode() : 0;
        result = 31 * result + (defendantName != null ? defendantName.hashCode() : 0);
        result = 31 * result + (personType != null ? personType.hashCode() : 0);
        result = 31 * result + (cpfCnpj != null ? cpfCnpj.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (neighborhood != null ? neighborhood.hashCode() : 0);
        result = 31 * result + (uf != null ? uf.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (lawsuits != null ? lawsuits.hashCode() : 0);
        return result;
    }
}
