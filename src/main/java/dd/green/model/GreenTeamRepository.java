package dd.green.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenTeamRepository extends CrudRepository<GreenTeam, Long> {
}
