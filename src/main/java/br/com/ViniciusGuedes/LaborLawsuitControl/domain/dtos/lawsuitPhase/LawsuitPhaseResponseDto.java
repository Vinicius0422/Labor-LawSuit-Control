package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitPhase;

public class LawsuitPhaseResponseDto {

    private Long lawsuitPhaseId;
    private String phase;

    public LawsuitPhaseResponseDto() {
    }

    public LawsuitPhaseResponseDto(Long lawsuitPhaseId, String phase) {
        this.lawsuitPhaseId = lawsuitPhaseId;
        this.phase = phase;
    }

    public Long getId() {
        return lawsuitPhaseId;
    }

    public void setId(Long lawsuitPhaseId) {
        this.lawsuitPhaseId = lawsuitPhaseId;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
