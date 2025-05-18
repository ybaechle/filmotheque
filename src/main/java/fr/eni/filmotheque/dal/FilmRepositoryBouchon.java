package fr.eni.filmotheque.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;

@Repository
public class FilmRepositoryBouchon implements FilmRepository{

	private GenreRepository genreRepo;
	private ParticipantRepository participantRepo;
	
	private static int indexFilm=1;
	
	private List<Film> films ;
	
	public FilmRepositoryBouchon(GenreRepository genreRepo,
			ParticipantRepository participantRepo) {
		this.genreRepo = genreRepo;
		this.participantRepo = participantRepo;
		
		films = new ArrayList();
		Film film = new Film(indexFilm++, "Some like it hot",1959, 
				"Lorsque deux musiciens sont témoins d'un succès populaire, ils fuient l'État dans un groupe entièrement féminin, déguisés en femmes, mais d'autres complications s'ensuivent."
				//,genreRepo.findGenreById(GenresEnum.COMEDIE.getId()).get()
				,new Genre(3, "Comedie")
				,137
				,participantRepo.findParticipantById(10).get());
		film.addActeur(participantRepo.findParticipantById(11).get());
		film.addActeur(participantRepo.findParticipantById(13).get());
		film.addActeur(participantRepo.findParticipantById(12).get());
		
		
		films.add(film);
		film = new Film(indexFilm++,"La garçonnière", 1960
				,"Un homme essaie de monter dans son entreprise en laissant ses dirigeants utiliser son appartement pour des rendez-vous galants, mais des complications s'ensuivent ainsi que sa propre relation amoureuse."
				//,genreRepo.findGenreById(GenresEnum.COMEDIE.getId()).get()
				,new Genre(3, "Comedie")
				,140
				,participantRepo.findParticipantById(10).get());
		film.addActeur(participantRepo.findParticipantById(12).get());
		film.addActeur(participantRepo.findParticipantById(14).get());
		
		
		films.add(film);
		film = new Film(indexFilm++, "Sept ans de réflexion", 1955
				,"Un bon père de famille se retrouve célibataire pour un été quand une blonde ingénue très craquante vient occuper l'appartement voisin. Pêchera ti ou pêchera ti pas ? En sortant du cinéma, une bouche de métro souffle un vent coquin sous la jupe de Marilyn"
				//,genreRepo.findGenreById(GenresEnum.COMEDIE.getId()).get()
				,new Genre(3, "Comedie")
				,105
				,participantRepo.findParticipantById(10).get());
		film.addActeur(participantRepo.findParticipantById(12).get());
		film.addActeur(participantRepo.findParticipantById(11).get());

		films.add(film);
		film = new Film(indexFilm++,"ET", 1982
				,"Un enfant prend son courage à deux mains et décide d'aider un ami extra-terrestre à s'échapper de la Terre pour rentrer sur sa planète natale."
				//,genreRepo.findGenreById(GenresEnum.AVENTURE.getId()).get()
				,new Genre(2, "Aventure")
				,115
				,participantRepo.findParticipantById(1).get());
		films.add(film);
		film = new Film(indexFilm++,"Moi, moche et méchant", 2010,
				"Lorsqu'un cerveau criminel utilise un trio d'orphelines comme pions pour un gros coup, il découvre que leur amour le chamboule profondément pour le mieux."
				//,genreRepo.findGenreById(GenresEnum.ANIMATION.getId()).get()
				,new Genre(1, "Animation")
				,95
				,participantRepo.findParticipantById(15).get());
		film.addActeur(participantRepo.findParticipantById(15).get());

		films.add(film);
		
	}
	
	@Override
	public List<Film> findAllFilms() {
		List<Film> lesFilms = films.stream().toList();
		return lesFilms;
	}

	@Override
	public Optional<Film> findFilmById(int idFilm) {
		return films.stream().filter(film->film.getId()==idFilm).findAny();
	}

	@Override
	public void saveFilm(Film film) {
		film.setId(indexFilm++);
		films.add(film);
		
	}

	@Override
	public int countFilmWithGenre(int idGenre) {
		
		return (int) films.stream()
				.filter(film-> film.getGenre() != null && film.getGenre().getId()==idGenre)
				.count()				
				;
	}

	@Override
	public List<Participant> findActeursByFilm(int idFilm) {
		// TODO Auto-generated method stub
		return null;
	}

}
