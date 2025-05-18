package fr.eni.filmotheque.dal;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import fr.eni.filmotheque.bo.Genre;

@SpringBootTest
public class GenreRepositoryImplTest {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public GenreRepository genreRepository;
	

	@BeforeEach
	public void initTest() {
		jdbcTemplate.execute("truncate table genres");		
		jdbcTemplate.execute("ALTER SEQUENCE genres_id_seq RESTART WITH 1");
		jdbcTemplate.update("insert into genres ( libelle) values ('Animation')");
		jdbcTemplate.update("insert into genres ( libelle) values ('Aventure')");
		jdbcTemplate.update("insert into genres ( libelle) values ('Comédie')");
		jdbcTemplate.update("insert into genres ( libelle) values ('Horreur')");
	}

	@Test
	@DisplayName("Test findAllGenres")
	public void testFindAllGenresCasNominal() {
		//Arrange
		
		//Act 
		List<Genre> genres = genreRepository.findAllGenres();
		
		//Assert
		Assertions.assertNotNull(genres);
		Assertions.assertEquals(4, genres.size());
	
	}

	
	@Test
	@DisplayName("Test ajout Genre")
	public void testAjoutGenreCasNominal() {
		//Arrange
		Genre genre = new Genre("nouveau");
		
		//Act 
		genreRepository.save(genre);
		
		//Assert
		Assertions.assertNotNull(genre);
		Assertions.assertNotNull(genre.getId());

	
	}

	@Test
	@DisplayName("Test suppression Genre cas nominal : aucun film n'utilise le genre à supprimer")
	public void testSuppressionGenreCasNominal() {
		//Arrange
		
		//Act 
		genreRepository.delete(4);
		
		//Assert
		Optional<Genre> opt = genreRepository.findGenreById(4);
		
		Assertions.assertTrue(opt.isEmpty());

	
	}

	
}
