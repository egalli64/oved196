package dd.red.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RED_TEAMS")
public class RedTeam {
	@Id
	@Column(name="TEAM_ID")
	private long id;

	@Column(name="TEAM_NAME")
	private String name;

	public long getId() {
		return id;
	}

	protected RedTeam() {
	}

	public RedTeam(long id, String name) {
		this.id = id;
		this.name = name;
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
		return "RedTeam [id=" + id + ", name=" + name + "]";
	}
}
