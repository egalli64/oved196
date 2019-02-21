package dd.blue.model;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlueCoderRepository extends CrudRepository<BlueCoder, Integer> {
	
	public List<BlueCoder> findAllByOrderByIdCoder();

	public List<BlueCoder> findAllByOrderByFirstname();

	public List<BlueCoder> findAllByOrderByLastname();

//	public List<BlueCoder> findAllByOrderById();
//
//	public List<BlueCoder> findAllOrderByRole();

	//public List<BlueCoder> findAll(Sort sort);

}
