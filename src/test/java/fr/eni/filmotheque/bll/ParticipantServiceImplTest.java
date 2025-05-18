package fr.eni.filmotheque.bll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.dal.ParticipantRepositoryImpl;
import fr.eni.filmotheque.exceptions.FilmNotFound;
import fr.eni.filmotheque.exceptions.ParticipantNotFound;

@ExtendWith(MockitoExtension.class)
class ParticipantServiceImplTest {

	@InjectMocks
	private ParticipantServiceImpl service;
	
	@Mock
	private ParticipantRepositoryImpl participantRepo;
	
	@Test
	@DisplayName("Test findParticipantById cas id existe")
	void testFindParticipantById() {
		
		//Arrange
		Participant mockParticipant = new Participant();
		mockParticipant.setId(5);
		Optional<Participant> optParticipant = Optional.of(mockParticipant);
		
		when(participantRepo.findParticipantById(5)).thenReturn( optParticipant);
		
		//Act
		Participant participant = null;
		
		try {
			participant = service.findParticipantById(5);
			
		} catch (ParticipantNotFound e) {			
			fail("Le participant n'a pas été trouvé");
			return;
		}

		//Assert
		assertNotNull(participant);
		assertEquals(5, participant.getId());

	}
	
	@Test
	@DisplayName("Test findParticipantById cas id non trouvé")
	void testFindFilmByIdCasIdNonTrouve() {
		
		//AAA
		//Arrange : préparation du test
		when(participantRepo.findParticipantById(99)).thenReturn(Optional.empty());
		
		//Act + Assert		
		assertThrows(ParticipantNotFound.class, ()->service.findParticipantById(99));
		
	}


}
