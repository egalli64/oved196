package dd.blue.model;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "BLUE_CODERS")
public class BlueCoder {

	@Column(name = "CODER_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCoder;

	@Column(name = "CODER_FIRSTNAME")
	private String firstname;

	@Column(name = "CODER_LASTNAME")
	private String lastname;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private BlueTeam team;

	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(name = "BLUE_ROLES_CODERS", joinColumns = @JoinColumn(name = "CODER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<BlueRole> role;

	public BlueCoder() {
		super();
	}

	public BlueCoder(int idCoder, String firstname, String lastname, BlueTeam team) {
		super();
		this.idCoder = idCoder;
		this.firstname = firstname;
		this.lastname = lastname;
		this.team = team;
	}

	public long getIdCoder() {
		return idCoder;
	}

	public void setIdCoder(int idCoder) {
		this.idCoder = idCoder;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public BlueTeam getTeam() {
		return team;
	}

	public void setTeam(BlueTeam team) {
		this.team = team;
	}

	public Set<BlueRole> getRole() {
		return role;
	}

	public void setRole(Set<BlueRole> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "BlueCoder [idCoder=" + idCoder + ", firstname=" + firstname + ", lastname=" + lastname + ", team="
				+ team + ", role=" + role + "]";
	}



}


