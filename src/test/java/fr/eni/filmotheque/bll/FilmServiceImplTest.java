package fr.eni.filmotheque.bll;

import static org.mockito.ArgumentMatchers.any;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.dal.FilmRepositoryImpl;
import fr.eni.filmotheque.exceptions.FilmNotFound;

@ExtendWith(MockitoExtension.class)
public class FilmServiceImplTest {
	
	@InjectMocks
	private FilmServiceImpl service;
	
	@Mock
	private FilmRepositoryImpl filmRepo;
	

	@Test
	@DisplayName("findFilmById cas l'id existe.")
	void testFindFilmByIdCasIdExistant()
	{
		//AAA
		//Arrange : préparation du test
		Film mockFilm = new Film();
		mockFilm.setId(12);
		Optional<Film> optFilm = Optional.of(mockFilm);
		
		when(filmRepo.findFilmById(12)).thenReturn( optFilm);
		when(filmRepo.findActeursByFilm(12)).thenReturn(List.of(new Participant()));
		
		//Act : l'appel de la méthode à tester
		Film film = null;
		try {
			film = service.findFilmById(12);
		} catch (FilmNotFound e) {
			fail("Le film 12 n'a pas été trouvé.");
			return;
		}
		
		//Assert : vérification du résultat de la méthode
		//--Expected : Résultat attendu
		//--Actual : résultat renvoyé par la méthode à tester
		assertNotNull(film);
		assertEquals(12, film.getId());
		assertEquals( 1, film.getActeurs().size(), "Le nombre d'acteurs du film est incorrect");
		
	}
	
	
	@Test
	@DisplayName("Test findFilmById cas id non trouvé")
	void testFindFilmByIdCasIdNonTrouve() {
		
		//AAA
		//Arrange : préparation du test
		when(filmRepo.findFilmById(99)).thenReturn(Optional.empty());
		
		//Act + Assert		
		assertThrows(FilmNotFound.class, ()->service.findFilmById(99));
		
	}
	
	
	@Test
	public void testSaveFilmCasPositif() {
		
		Film film = new Film( "Some like it hot",1959, 
				"Lorsque deux musiciens sont témoins d'un succès populaire, ils fuient l'État dans un groupe entièrement féminin, déguisés en femmes, mais d'autres complications s'ensuivent."
				,new Genre(1, "")
				,137
				,new Participant(12, "Wilder", "Billy"));
		film.addActeurs(new Participant(13, "Monroe", "Marylin")
				,new Participant(14, "Lemmon", "Jack"), new Participant(15, "L", "J")	);
		
		Mockito.doAnswer((invocation) -> {
				
				Film f = invocation.getArgument(0);
				f.setId(12);
				
				return null;
			})
		.when(filmRepo).saveFilm(any(Film.class));
		
		//Act
		service.saveFilm(film);
	
		//
		assertNotNull(film);
		assertEquals(12, film.getId());
	}
}















