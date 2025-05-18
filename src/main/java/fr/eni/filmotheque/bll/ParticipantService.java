package fr.eni.filmotheque.bll;

import java.util.List;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.exceptions.ParticipantNotFound;

public interface ParticipantService {

	public List<Participant> findAllParticipants();
	
	public Participant findParticipantById(int id) throws ParticipantNotFound; 
	
}
