package dd.blue.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlueTeamRepository extends CrudRepository<BlueTeam, Integer> {
}
