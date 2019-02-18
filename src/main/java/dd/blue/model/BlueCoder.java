package dd.blue.model;

import java.util.Set;

import javax.persistence.*;

@Table(name = "BLUE_CODERS")
public class BlueCoder {

	@Column(name = "CODER_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCoder;

	@Column(name = "CODER_FIRSTNAME")
	private String firstname;

	@Column(name = "CODER_LASTNAME")
	private String lastname;

	@OneToMany
	@JoinColumn(name = "TEAM_ID")
	private long idTeam;

	@ManyToMany
	@JoinTable(name = "ROLES_CODERS", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "CODER_ID"))
	Set<BlueRole> role;

	public BlueCoder() {
		super();
	}

	public BlueCoder(long idCoder, String firstname, String lastname, long idTeam) {
		super();
		this.idCoder = idCoder;
		this.firstname = firstname;
		this.lastname = lastname;
		this.idTeam = idTeam;
	}

	public long getIdCoder() {
		return idCoder;
	}

	public void setIdCoder(long idCoder) {
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

	public long getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(long idTeam) {
		this.idTeam = idTeam;
	}

	public Set<BlueRole> getRole() {
		return role;
	}

	public void setRole(Set<BlueRole> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "BlueCoder [idCoder=" + idCoder + ", firstname=" + firstname + ", lastname=" + lastname + ", idTeam="
				+ idTeam + "]";
	}

}


