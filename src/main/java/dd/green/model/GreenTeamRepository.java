package dd.green.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenTeamRepository extends CrudRepository<GreenTeam, Long> {
	public Optional<GreenCoder> findByName(String name);
	public Optional<GreenCoder> deleteByName (String name);
	
}
