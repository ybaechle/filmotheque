package fr.eni.filmotheque.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.dal.ParticipantRepository;
import fr.eni.filmotheque.exceptions.ParticipantNotFound;

@Service("participantService")
public class ParticipantServiceImpl implements ParticipantService{

	private ParticipantRepository participantRepo;
	
	
	public ParticipantServiceImpl(ParticipantRepository participantRepo) {
		this.participantRepo = participantRepo;
	}

	@Override
	public List<Participant> findAllParticipants() {
		return participantRepo.findAllParticipants();
	}

	@Override
	public Participant findParticipantById(int id) throws ParticipantNotFound {
		
		Optional<Participant> optParticipant =  participantRepo.findParticipantById(id);
		if(optParticipant.isEmpty()) {
			throw new ParticipantNotFound(id);
		}
		
		return optParticipant.get();
	}

}
