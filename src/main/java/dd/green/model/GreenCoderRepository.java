package dd.green.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenCoderRepository extends CrudRepository<GreenCoder, Long> {
}
