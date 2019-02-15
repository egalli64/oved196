package dd.blue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BLUE_TEAMS")
public class BlueTeam {
	@Id
	@Column(name="TEAM_ID")
	private long id;

	@Column(name="TEAM_NAME")
	private String name;

	public long getId() {
		return id;
	}

	protected BlueTeam() {
	}

	public BlueTeam(long id, String name) {
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
		return "BlueTeam [id=" + id + ", name=" + name + "]";
	}
}
