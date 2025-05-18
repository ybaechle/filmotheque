package fr.eni.filmotheque.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.filmotheque.bo.Participant;

@Repository
@Primary
public class ParticipantRepositoryImpl implements ParticipantRepository {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public ParticipantRepositoryImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
		this.jdbcTemplate = namedJdbcTemplate.getJdbcTemplate();
	}

	@Override
	public List<Participant> findAllParticipants() {
		String sql = "select id, nom, prenom from participants";

		List<Participant> participants = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Participant>(Participant.class));
		return participants;

	}

	@Override
	public Optional<Participant> findParticipantById(int id) {
		String sql = "select id, nom, prenom from participants where id=?";

		Optional<Participant> optParticipant;

		try {
			Participant participant = jdbcTemplate.queryForObject(sql,
					new BeanPropertyRowMapper<Participant>(Participant.class), id);
			optParticipant = Optional.of(participant);
		} catch (EmptyResultDataAccessException exc) {
			optParticipant = Optional.empty();
		}

		return optParticipant				;
	}


}
