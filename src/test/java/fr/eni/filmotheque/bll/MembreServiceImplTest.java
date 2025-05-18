package fr.eni.filmotheque.bll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.dal.MembreRepository;


@ExtendWith(MockitoExtension.class)
public class MembreServiceImplTest {
	
	@InjectMocks
	private  MembreServiceImpl service;
	
	@Mock
	private  MembreRepository membreRepo;
	

	@Test
	@DisplayName("find MembreById cas l'id existe.")
	void testFindMembreByIdCasIdExistant()
	{
		//AAA
		//Arrange : préparation du test
		 Membre mockMembre = new  Membre();
		mockMembre.setId(12);
		Optional< Membre> optMembre = Optional.of(mockMembre);
		
		when(membreRepo.findMembreByPseudo("bob")).thenReturn( optMembre);
		
		//Act : l'appel de la méthode à tester
		Optional<Membre> optActual = service.findMembreByPseudo("bob");
		
		//Assert : vérification du résultat de la méthode
		//--Expected : Résultat attendu
		//--Actual : résultat renvoyé par la méthode à tester
		assertTrue(optActual.isPresent());
		assertEquals(12, optActual.get().getId());
		
	}
	
	
	@Test
	@DisplayName("Test findMembreByPseudo cas pseudo non trouvé")
	void testFindMembreByPseudoCasPseudoNonTrouve() {
		
		//AAA
		//Arrange : préparation du test
		when(membreRepo.findMembreByPseudo("xyz")).thenReturn(Optional.empty());
		
		//Act
		Optional<Membre> optMembre = service.findMembreByPseudo("xyz");
		
		//Act + Assert		
		assertNotNull(optMembre);
		assertTrue(optMembre.isEmpty());
		
	}
	
}















