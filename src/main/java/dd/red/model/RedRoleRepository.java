package dd.red.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dd.green.model.GreenRole;

@Repository
public interface RedRoleRepository extends CrudRepository<RedRole, Long> {

}
