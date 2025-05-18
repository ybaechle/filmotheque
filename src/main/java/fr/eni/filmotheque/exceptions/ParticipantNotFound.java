package fr.eni.filmotheque.exceptions;

public class ParticipantNotFound extends Exception {

	public ParticipantNotFound(int id) {
		super("Le participant n'a pas été trouvé - id = "+ id);
	}

}
