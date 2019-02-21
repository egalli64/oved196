package dd.red.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;





@Entity
@Table(name = "RED_ROLES")
public class RedRole {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private long id;

	@Column(name = "ROLE_NAME")
	private String name;
	
	@ManyToMany (fetch=FetchType.EAGER)
	@JoinTable(name = "RED_CODERS_ROLES", //
			joinColumns = @JoinColumn(name = "ROLE_ID"), //
			inverseJoinColumns = @JoinColumn(name = "CODER_ID"))
	List<RedCoder> coders;


	public RedRole() {
	}

	public RedRole( String name) {
		
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RedRole [id=" + id + ", name=" + name + "]";
	}
}


