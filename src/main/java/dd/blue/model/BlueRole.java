package dd.blue.model;

import javax.persistence.*;

@Entity
@Table(name = "BLUE_ROLES")
public class BlueRole {
	@Column(name = "ROLE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long idRole;
	
	@Column(name = "ROLE_NAME")
	String nomeRole;

	public BlueRole() {
		super();
	}

	public BlueRole(long idRole, String nomeRole) {
		super();
		this.idRole = idRole;
		this.nomeRole = nomeRole;
	}

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}
	
	
}