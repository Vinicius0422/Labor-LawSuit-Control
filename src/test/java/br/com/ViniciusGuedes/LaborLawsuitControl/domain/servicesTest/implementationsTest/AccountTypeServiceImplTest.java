package br.com.ViniciusGuedes.LaborLawsuitControl.domain.servicesTest.implementationsTest;

import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.accountType.AccountTypeReponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.location.LocationResponseDto;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.AccountTypeServiceImpl;
import br.com.ViniciusGuedes.LaborLawsuitControl.domain.services.implementations.LocationServiceImpl;
import br.com.ViniciusGuedes.LaborLawsuitControl.repositories.AccountTypeRepository;
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

class AccountTypeServiceImplTest {

    @Mock
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    @InjectMocks
    private AccountTypeServiceImpl accountTypeService;
    private AccountTypeReponseDto accountTypeReponseDto;
    @Test
    private void startAccountType(){ accountTypeReponseDto = new AccountTypeReponseDto(1L, "Savings account"); }
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startAccountType();
    }

    @Test
    void whenFindAllThenReturnAnListOfAccountTypeResponseDto() {
        when(accountTypeRepository.findAllAccountsType()).thenReturn(List.of(accountTypeReponseDto));
        List<AccountTypeReponseDto> response = accountTypeService.getAllAccountsType();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(accountTypeReponseDto, response.get(0));
        assertEquals(AccountTypeReponseDto.class, response.get(0).getClass());
        assertEquals(1L, response.get(0).getId());
        assertEquals("Savings account", response.get(0).getAccountType());
    }

    @Test
    void whenFindAllThenReturnAnListEmpty() {
        when(accountTypeRepository.findAllAccountsType()).thenReturn(new ArrayList<>());
        List<AccountTypeReponseDto> response = accountTypeService.getAllAccountsType();

        assertTrue(response.isEmpty());
    }
}