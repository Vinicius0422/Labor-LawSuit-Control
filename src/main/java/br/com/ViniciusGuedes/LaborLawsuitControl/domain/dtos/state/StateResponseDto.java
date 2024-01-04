package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.state;

public class StateResponseDto {

    private Long stateId;
    private String name;

    public StateResponseDto() {
    }

    public StateResponseDto(Long stateId, String name) {
        this.stateId = stateId;
        this.name = name;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
