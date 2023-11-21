package br.com.ViniciusGuedes.LaborLawsuitControl.domain.servicesTest.implementationsTest;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.lawsuitStatus.LawsuitStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.nationality.NationalityResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.LawsuitStatusServiceImpl;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LawsuitStatusRepository;
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

class LawsuitStatusServiceImplTest {

    @Mock
    private LawsuitStatusRepository lawsuitStatusRepository;

    @Autowired
    @InjectMocks
    private LawsuitStatusServiceImpl lawsuitStatusService;
    private LawsuitStatusResponseDto lawsuitStatusResponseDto;
    @Test
    private void startLawsuitStatus(){ lawsuitStatusResponseDto = new LawsuitStatusResponseDto(1L, "In progress"); }
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startLawsuitStatus();
    }

    @Test
    void whenFindAllThenReturnAnListOfLocationResponseDto() {
        when(lawsuitStatusRepository.findAllLawsuitStatus()).thenReturn(List.of(lawsuitStatusResponseDto));
        List<LawsuitStatusResponseDto> response = lawsuitStatusService.getAllLawsuitStatus();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(lawsuitStatusResponseDto, response.get(0));
        assertEquals(LawsuitStatusResponseDto.class, response.get(0).getClass());
        assertEquals(1L, response.get(0).getId());
        assertEquals("In progress", response.get(0).getStatus());
    }

    @Test
    void whenFindAllThenReturnAnListEmpty() {
        when(lawsuitStatusRepository.findAllLawsuitStatus()).thenReturn(new ArrayList<>());
        List<LawsuitStatusResponseDto> response = lawsuitStatusService.getAllLawsuitStatus();

        assertTrue(response.isEmpty());
    }
}