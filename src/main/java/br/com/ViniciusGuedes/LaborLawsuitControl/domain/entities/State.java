package br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long stateId;

    @Column(name = "code_uf")
    private Long ufCode;

    @Column(length = 100, name = "name")
    private String stateName;

    @Column(length = 2)
    private String uf;

    @Column(length = 50)
    private String region;

    public State() {
    }

    public State(Long stateId, Long ufCode, String stateName, String uf, String region) {
        this.stateId = stateId;
        this.ufCode = ufCode;
        this.stateName = stateName;
        this.uf = uf;
        this.region = region;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getUfCode() {
        return ufCode;
    }

    public void setUfCode(Long ufCode) {
        this.ufCode = ufCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;

        State state = (State) o;

        if (stateId != null ? !stateId.equals(state.stateId) : state.stateId != null) return false;
        if (ufCode != null ? !ufCode.equals(state.ufCode) : state.ufCode != null) return false;
        if (stateName != null ? !stateName.equals(state.stateName) : state.stateName != null) return false;
        if (uf != null ? !uf.equals(state.uf) : state.uf != null) return false;
        return region != null ? region.equals(state.region) : state.region == null;
    }

    @Override
    public int hashCode() {
        int result = stateId != null ? stateId.hashCode() : 0;
        result = 31 * result + (ufCode != null ? ufCode.hashCode() : 0);
        result = 31 * result + (stateName != null ? stateName.hashCode() : 0);
        result = 31 * result + (uf != null ? uf.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }
}
