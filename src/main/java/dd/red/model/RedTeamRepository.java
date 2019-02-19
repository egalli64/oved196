package dd.red.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedTeamRepository extends CrudRepository<RedTeam, Long> {
	
	public Optional<RedTeam> findByName(String name);
	public Optional<RedTeam> deleteByName (String name);
}
