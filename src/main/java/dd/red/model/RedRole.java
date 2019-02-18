package dd.red.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RED_ROLES")
public class RedRole {



	@Id
	@Column(name = "ROLE_ID")
	private long id;

	@Column(name = "ROLE_NAME")
	private String name;

	protected RedRole() {
	}

	public RedRole(long id, String name) {
		this.id = id;
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


