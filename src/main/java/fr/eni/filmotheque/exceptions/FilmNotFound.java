package fr.eni.filmotheque.exceptions;

public class FilmNotFound extends Exception {
	private int idFilm;
	
	public FilmNotFound(int idFilm) {
		this.idFilm = idFilm;
	}
	
	@Override
	public String getMessage() {
		
		return "Le film " + idFilm + " n'a pas été trouvé.";
	}
}
