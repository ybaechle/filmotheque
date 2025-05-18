package fr.eni.filmotheque.bll;

import java.util.List;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.exceptions.GenreNotFound;
import fr.eni.filmotheque.exceptions.SuppressionGenreImpossible;

public interface GenreService {
	List<Genre> findAllGenres();
	Genre findGenreById(int idGenre) throws GenreNotFound;
	void saveGenre(Genre genre);
	void deleteGenre(int idGenre) throws SuppressionGenreImpossible;
	
}
