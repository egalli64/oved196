package dd.green.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenRoleRepository extends CrudRepository<GreenRole, Long> {
}
