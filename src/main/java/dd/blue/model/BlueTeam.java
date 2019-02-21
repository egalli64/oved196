package dd.blue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BLUE_TEAMS")
public class BlueTeam implements Comparable<BlueTeam>{
	@Id
	@Column(name="TEAM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="TEAM_NAME")
	private String name;

	public int getId() {
		return id;
	}

	protected BlueTeam() {
	}

	public BlueTeam(String name) {
		this.name = name;
	}

	public void setId(int idTeam) {
		this.id = idTeam;
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

	@Override
	public int compareTo(BlueTeam o) {
		if(this.name.compareTo(o.getName()) == 0)
			return this.name.compareTo(o.getName());
		if(this.name.compareTo(o.getName()) > 0)
			return this.name.compareTo(o.getName());
		if(this.name.compareTo(o.getName()) < 0)
			return this.name.compareTo(o.getName());
		return 0;
	}
	
}
