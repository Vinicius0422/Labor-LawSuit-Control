package br.com.ViniciusGuedes.LaborLawsuitControl.domain.servicesTest.implementationsTest;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitPhase.LawsuitPhaseResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.nationality.NationalityResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.LawsuitPhaseServiceImpl;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.NationalityServiceImpl;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LawsuitPhaseRepository;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.NationalityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LawsuitPhaseServiceImplTest {

    @Mock
    private LawsuitPhaseRepository lawsuitPhaseRepository;

    @Autowired
    @InjectMocks
    private LawsuitPhaseServiceImpl lawsuitPhaseService;
    private LawsuitPhaseResponseDto lawsuitPhaseResponseDto;
    @Test
    private void startLawsuitPhase(){ lawsuitPhaseResponseDto = new LawsuitPhaseResponseDto(1L, "Awaiting trial"); }
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startLawsuitPhase();
    }

    @Test
    void whenFindAllThenReturnAnListOfLocationResponseDto() {
        when(lawsuitPhaseRepository.findAllLawsuitPhases()).thenReturn(List.of(lawsuitPhaseResponseDto));
        List<LawsuitPhaseResponseDto> response = lawsuitPhaseService.getAllLawsuitPhases();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(lawsuitPhaseResponseDto, response.get(0));
        assertEquals(LawsuitPhaseResponseDto.class, response.get(0).getClass());
        assertEquals(1L, response.get(0).getId());
        assertEquals("Awaiting trial", response.get(0).getPhase());
    }

    @Test
    void whenFindAllThenReturnAnListEmpty() {
        when(lawsuitPhaseRepository.findAllLawsuitPhases()).thenReturn(new ArrayList<>());
        List<LawsuitPhaseResponseDto> response = lawsuitPhaseService.getAllLawsuitPhases();

        assertTrue(response.isEmpty());
    }
}