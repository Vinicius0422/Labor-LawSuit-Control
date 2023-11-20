package br.com.ViniciusGuedes.LaborLawsuitControl.domain.servicesTest.implementationsTest;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.location.LocationResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.LocationServiceImpl;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.LocationRepository;
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

class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @Autowired
    @InjectMocks
    private LocationServiceImpl locationService;
    private LocationResponseDto locationResponseDto;
    @Test
    private void startLocation(){ locationResponseDto = new LocationResponseDto(1L, "Archive"); }
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startLocation();
    }

    @Test
    void whenFindAllThenReturnAnListOfLocationResponseDto() {
        when(locationRepository.findAllLocations()).thenReturn(List.of(locationResponseDto));
        List<LocationResponseDto> response = locationService.getAllLocations();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(locationResponseDto, response.get(0));
        assertEquals(LocationResponseDto.class, response.get(0).getClass());
        assertEquals(1L, response.get(0).getId());
        assertEquals("Archive", response.get(0).getLocation());
    }

    @Test
    void whenFindAllThenReturnAnListEmpty() {
        when(locationRepository.findAllLocations()).thenReturn(new ArrayList<>());
        List<LocationResponseDto> response = locationService.getAllLocations();

        assertTrue(response.isEmpty());
    }
}