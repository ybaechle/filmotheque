package fr.eni.filmotheque.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.eni.filmotheque.bo.Participant;

@Repository
public class ParticipantRepositoryBouchon implements ParticipantRepository{
	
	private List<Participant> participants = new ArrayList<>();
	
	public ParticipantRepositoryBouchon() {
		participants = new ArrayList<>();
		participants.add(new Participant(1, "Spielberg", "Steven"));
		participants.add(new Participant(2, "Cronenberg", "David"));
		participants.add(new Participant(3, "Boon", "Dany"));
		participants.add(new Participant(4, "Attenborough", "Richard"));
		participants.add(new Participant(5, "Goldblum", "Jeff"));
		participants.add(new Participant(6, "Davis", "Geena"));
		participants.add(new Participant(7, "Rylance", "Mark"));
		participants.add(new Participant(8, "Barnhill", "Ruby"));
		participants.add(new Participant(9, "Merad", "Kad"));
		participants.add(new Participant(10, "Wilder", "Billy"));
		participants.add(new Participant(11, "Monroe", "Marylin"));
		participants.add(new Participant(12, "Lemmon", "Jack"));
		participants.add(new Participant(13, "Curtis", "Tony"));
		participants.add(new Participant(14, "Mac Laine", "Shirley"));
		participants.add(new Participant(15, "Coffin", "Pierre"));

	}
	
	@Override
	public List<Participant> findAllParticipants() {
		return participants.stream().toList();
	}

	@Override
	public Optional<Participant> findParticipantById(int id) {
		return participants.stream().filter(participant -> participant.getId() == id).findFirst();
	}

}
