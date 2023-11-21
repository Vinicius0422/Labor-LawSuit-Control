package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitPhase;

public class LawsuitPhaseResponseDto {

    private Long id;
    private String phase;

    public LawsuitPhaseResponseDto() {
    }

    public LawsuitPhaseResponseDto(Long id, String phase) {
        this.id = id;
        this.phase = phase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
