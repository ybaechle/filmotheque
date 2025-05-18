package fr.eni.filmotheque.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.filmotheque.bo.Participant;

public interface ParticipantRepository {
	public List<Participant> findAllParticipants();

	public Optional<Participant> findParticipantById(int id);
}
