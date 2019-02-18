package dd.red.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedRoleRepository extends CrudRepository<RedRole, Long> {
}
