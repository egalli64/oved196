package dd.green.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GREEN_CODERS")
public class GreenCoder {
	@Id
	@Column(name = "CODER_ID")
	private Long id;

	@Column(name = "FIRST_NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private GreenTeam team;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "COD_ROLE", //
			joinColumns = @JoinColumn(name = "CODER_ID"), //
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	Set<GreenRole> roles;

	public Set<GreenRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<GreenRole> roles) {
		this.roles = roles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	protected GreenCoder() {
	}

	public GreenCoder(Long id, String name, GreenTeam team, Set<GreenRole> roles) {
		this.id = id;
		this.name = name;
		this.team = team;
		this.roles = roles;
	}
	
	public GreenCoder(String name) {
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

	public GreenTeam getTeam() {
		return team;
	}

	public void setTeam(GreenTeam team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "GreenCoder [id=" + id + ", name=" + name + ", team=" + team + ", roles=" + roles + "]";
	}
}
