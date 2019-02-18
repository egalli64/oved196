package dd.blue.model;

import javax.persistence.*;

@Entity
@Table (name = "ROLES_CODERS")
public class BlueRoleCoder {
	@Id
	@ManyToMany
	@JoinColumn(name = "ROLE_ID")
	long idRole;
	
	@Id
	@ManyToMany
	@JoinColumn(name = "CODER_ID")
	long idCoder;

	public BlueRoleCoder() {
		super();
	}

	public BlueRoleCoder(long idRole, long idCoder) {
		super();
		this.idRole = idRole;
		this.idCoder = idCoder;
	}

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public long getIdCoder() {
		return idCoder;
	}

	public void setIdCoder(long idCoder) {
		this.idCoder = idCoder;
	}
	
	

}
