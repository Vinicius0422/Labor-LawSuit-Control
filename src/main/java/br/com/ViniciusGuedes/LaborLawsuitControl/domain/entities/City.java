package br.com.ViniciusGuedes.LaborLawsuitControl.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long cityId;

    @Column(name = "county_code")
    private Long countyCode;

    @Column(length = 150, name = "name")
    private String cityName;

    @Column(length = 2)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    public City() {
    }

    public City(Long cityId, Long countyCode, String cityName, String uf, State state) {
        this.cityId = cityId;
        this.countyCode = countyCode;
        this.cityName = cityName;
        this.uf = uf;
        this.state = state;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(Long countyCode) {
        this.countyCode = countyCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (cityId != null ? !cityId.equals(city.cityId) : city.cityId != null) return false;
        if (countyCode != null ? !countyCode.equals(city.countyCode) : city.countyCode != null) return false;
        if (cityName != null ? !cityName.equals(city.cityName) : city.cityName != null) return false;
        if (uf != null ? !uf.equals(city.uf) : city.uf != null) return false;
        return state != null ? state.equals(city.state) : city.state == null;
    }

    @Override
    public int hashCode() {
        int result = cityId != null ? cityId.hashCode() : 0;
        result = 31 * result + (countyCode != null ? countyCode.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (uf != null ? uf.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
