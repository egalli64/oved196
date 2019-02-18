package dd.blue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BLUE_TEAMS")
public class BlueTeam {
	@Id
	@Column(name="TEAM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTeam;

	@Column(name="TEAM_NAME")
	private String name;

	public int getId() {
		return idTeam;
	}

	protected BlueTeam() {
	}

	public BlueTeam(String name) {
		this.name = name;
	}

	public void setId(int idTeam) {
		this.idTeam = idTeam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BlueTeam [id=" + idTeam + ", name=" + name + "]";
	}
}
