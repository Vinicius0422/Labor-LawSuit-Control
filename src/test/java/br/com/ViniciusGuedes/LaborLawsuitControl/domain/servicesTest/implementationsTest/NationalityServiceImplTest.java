package br.com.ViniciusGuedes.LaborLawsuitControl.domain.servicesTest.implementationsTest;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.nationality.NationalityResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.NationalityServiceImpl;
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

class NationalityServiceImplTest {

    @Mock
    private NationalityRepository nationalityRepository;

    @Autowired
    @InjectMocks
    private NationalityServiceImpl nationalityService;
    private NationalityResponseDto nationalityResponseDto;
    @Test
    private void startNationality(){ nationalityResponseDto = new NationalityResponseDto(1L, "Brazilian"); }
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startNationality();
    }

    @Test
    void whenFindAllThenReturnAnListOfLocationResponseDto() {
        when(nationalityRepository.findAllNationalities()).thenReturn(List.of(nationalityResponseDto));
        List<NationalityResponseDto> response = nationalityService.getAllNationalities();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(nationalityResponseDto, response.get(0));
        assertEquals(NationalityResponseDto.class, response.get(0).getClass());
        assertEquals(1L, response.get(0).getId());
        assertEquals("Brazilian", response.get(0).getNationality());
    }

    @Test
    void whenFindAllThenReturnAnListEmpty() {
        when(nationalityRepository.findAllNationalities()).thenReturn(new ArrayList<>());
        List<NationalityResponseDto> response = nationalityService.getAllNationalities();

        assertTrue(response.isEmpty());
    }
}