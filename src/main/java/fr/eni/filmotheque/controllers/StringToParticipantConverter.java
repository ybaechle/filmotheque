package fr.eni.filmotheque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.dal.ParticipantRepository;

@Component
public class StringToParticipantConverter implements Converter<String, Participant>{

	@Autowired
	private ParticipantRepository participantRepo;
	
	@Override
	public Participant convert(String idParticipant) {
		int id = 0;
		
		try {
			id= Integer.parseInt(idParticipant);
		}catch(NumberFormatException nfe) {			
			//TODO : cas idGenre pas entier
			nfe.printStackTrace();
		}
		
		return participantRepo.findParticipantById(id).orElse(null);
	}

}
