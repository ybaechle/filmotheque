package fr.eni.filmotheque.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.dal.MembreRepository;


@Component
public class FilmothequeUserDetailsService implements UserDetailsService {

	private MembreRepository membreRepo;

	public FilmothequeUserDetailsService(MembreRepository membreRepo) {
		this.membreRepo = membreRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Charger l'utilisateur depuis la base de données
		
			Optional<Membre> optMembre = membreRepo.findMembreByPseudo(username);
		
			if(optMembre.isEmpty()) {
				throw new UsernameNotFoundException(username);
			}
			Membre membre = optMembre.get();
			UserBuilder userBuilder = User.builder().username(membre.getPseudo())
					.password(membre.getMotDePasse());
			
			if(membre.isAdmin()) {
				userBuilder = userBuilder.roles("ADMIN", "MEMBRE");
			}else {
				userBuilder = userBuilder.roles( "MEMBRE");
			}
				

			return userBuilder.build();

	}

	
}