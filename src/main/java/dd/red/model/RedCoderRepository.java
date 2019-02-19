package dd.red.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RedCoderRepository extends CrudRepository<RedCoder, Long> {
	public Optional<RedCoder> findByName(String name);
	public Optional<RedCoder> deleteByName (String name);

}
