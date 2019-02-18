package dd.red.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RedCoderRepository extends CrudRepository<RedCoder, Long> {

}
