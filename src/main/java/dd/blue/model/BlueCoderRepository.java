package dd.blue.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlueCoderRepository extends CrudRepository<BlueCoder, Integer> {
}
