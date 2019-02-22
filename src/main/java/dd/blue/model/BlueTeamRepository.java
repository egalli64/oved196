package dd.blue.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlueTeamRepository extends CrudRepository<BlueTeam, Integer> {
	
	public List<BlueTeam> findAllByOrderByName();
	
}
