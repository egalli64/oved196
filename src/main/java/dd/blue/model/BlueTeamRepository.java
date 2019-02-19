package dd.blue.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import antlr.collections.List;

@Repository
public interface BlueTeamRepository extends CrudRepository<BlueTeam, Integer> {
	
	
}
