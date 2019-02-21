package dd.green.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenTeamRepository extends CrudRepository<GreenTeam, Long> {
	
	Optional<GreenTeam> findByName(String name_team);
}
