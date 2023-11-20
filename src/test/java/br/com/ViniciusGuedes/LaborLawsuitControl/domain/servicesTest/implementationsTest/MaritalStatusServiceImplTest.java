package br.com.ViniciusGuedes.LaborLawsuitControl.domain.servicesTest.implementationsTest;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.maritalStatus.MaritalStatusResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.MaritalStatusServiceImpl;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.MaritalStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MaritalStatusServiceImplTest {

    @Mock
    private MaritalStatusRepository maritalStatusRepository;

    @Autowired
    @InjectMocks
    private MaritalStatusServiceImpl maritalStatusService;
    private MaritalStatusResponseDto maritalStatusResponseDto;
    @Test
    private void startMaritalStatusDto(){
        maritalStatusResponseDto = new MaritalStatusResponseDto(1L, "Single");
    }
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startMaritalStatusDto();
    }

    @Test
    void whenFindAllThenReturnAnListOfMaritalStatusDto() {
        when(maritalStatusRepository.findAllMaritalStatus()).thenReturn(List.of(maritalStatusResponseDto));
        List<MaritalStatusResponseDto> response = maritalStatusService.getAllMaritalStatus();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(maritalStatusResponseDto, response.get(0));
        assertEquals(MaritalStatusResponseDto.class, response.get(0).getClass());
        assertEquals(1L, response.get(0).getId());
        assertEquals("Single", response.get(0).getMaritalStatus());
    }

    @Test
    void whenFindAllThenReturnAnListEmpty() {
        when(maritalStatusRepository.findAllMaritalStatus()).thenReturn(new ArrayList<>());
        List<MaritalStatusResponseDto> response = maritalStatusService.getAllMaritalStatus();

        assertTrue(response.isEmpty());
    }
}