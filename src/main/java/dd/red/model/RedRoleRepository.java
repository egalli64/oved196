package dd.red.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedRoleRepository extends CrudRepository<RedRole, Long> {
	public Optional<RedRole> findByName(String name);
}
