package dd.green.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GREEN_TEAMS")
public class GreenTeam {
	@Id
	@Column(name="TEAM_ID")
	private long id;

	@Column(name="TEAM_NAME")
	private String name;

	public long getId() {
		return id;
	}

	protected GreenTeam() {
	}

	public GreenTeam(long id, String name) {
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
		return "GreenTeam [id=" + id + ", name=" + name + "]";
	}
}
