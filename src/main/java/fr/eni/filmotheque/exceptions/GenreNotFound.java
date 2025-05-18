package fr.eni.filmotheque.exceptions;

public class GenreNotFound extends Exception {
	private int idGenre;
	
	public GenreNotFound(int idGenre) {
		this.idGenre = idGenre;
	}
	
	@Override
	public String getMessage() {
		
		return "Le genre " + idGenre + " n'a pas été trouvé.";
	}
}
