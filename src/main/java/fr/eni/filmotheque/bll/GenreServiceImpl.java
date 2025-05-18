package fr.eni.filmotheque.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.dal.FilmRepository;
import fr.eni.filmotheque.dal.GenreRepository;
import fr.eni.filmotheque.exceptions.GenreNotFound;
import fr.eni.filmotheque.exceptions.SuppressionGenreImpossible;

@Service("genreService")
public class GenreServiceImpl implements GenreService{

	public GenreRepository genreRepository;
	public FilmRepository filmRepository;
	
	public GenreServiceImpl(GenreRepository genreRepository, FilmRepository filmRepository) {
		this.genreRepository = genreRepository;
		this.filmRepository = filmRepository;
	}

	@Override
	public List<Genre> findAllGenres() {
		return genreRepository.findAllGenres();
	}

	@Override
	public Genre findGenreById(int idGenre) throws GenreNotFound {
		Optional<Genre> optGenre = genreRepository.findGenreById(idGenre);
		if (optGenre.isEmpty()) {
			throw new GenreNotFound(idGenre);
		}
		return optGenre.get();
	}

	@Override
	public void saveGenre(Genre genre) {
		
		genreRepository.save(genre);
		
	}

	@Override
	public void deleteGenre(int idGenre) throws SuppressionGenreImpossible {
		// On verifie que le Genre n'est pas utilisé
		if (filmRepository.countFilmWithGenre(idGenre)>0) {
			throw new SuppressionGenreImpossible();
		}
		
		//On supprime le genre
		genreRepository.delete(idGenre);
	}

}
