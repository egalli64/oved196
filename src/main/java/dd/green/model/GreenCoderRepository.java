package dd.green.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenCoderRepository extends CrudRepository<GreenCoder, Long> {

	Optional<GreenCoder> findByName(String id_name);
}
