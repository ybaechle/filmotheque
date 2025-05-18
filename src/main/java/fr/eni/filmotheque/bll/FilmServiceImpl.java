package fr.eni.filmotheque.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.dal.FilmRepository;
import fr.eni.filmotheque.exceptions.FilmNotFound;

@Service
public class FilmServiceImpl implements FilmService{

	private FilmRepository filmRepository;
	
	public FilmServiceImpl(FilmRepository filmRepository) {
		this.filmRepository = filmRepository;
	}

	@Override
	public List<Film> findAllFilms() {
		return filmRepository.findAllFilms();
	}

	@Override
	public Film findFilmById(int idFilm)  throws FilmNotFound{
		
		Optional<Film> optFilm = filmRepository.findFilmById(idFilm);
		
		if(optFilm.isEmpty()) {
			throw new FilmNotFound(idFilm);
		}
		
		Film film = optFilm.get();
		
		//Lazy Loading
		film.setActeurs(filmRepository.findActeursByFilm(idFilm));
		
		return film;
	}

	@Override
	@Transactional
	public void saveFilm(Film film) {
		filmRepository.saveFilm(film);
		
	}

}
