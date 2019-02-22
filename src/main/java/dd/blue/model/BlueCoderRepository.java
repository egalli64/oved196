package dd.blue.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlueCoderRepository extends CrudRepository<BlueCoder, Integer> {
	
//	@Query("select bc from blue_coders bc inner join blue_teams on bc.team_id= bt.team_id order by bt.team_name")
//	public List<BlueCoder> teamsort();
	
	
	public List<BlueCoder> findAllByOrderByIdCoder();

	public List<BlueCoder> findAllByOrderByFirstname();

	public List<BlueCoder> findAllByOrderByLastname();

	@Query("select bc from BlueCoder bc inner join bc.team bt order by bt.name, bc.lastname")
    public List<BlueCoder> findAllByOrderByTeam();
//	public List<BlueCoder> findAllOrderByRole();

	//public List<BlueCoder> findAll(Sort sort);

}
