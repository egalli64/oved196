package dd.green.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GREEN_ROLES")
public class GreenRole {
	@Id
	@Column(name = "ROLE_ID")
	private long id;


	@Column(name = "ROLE_NAME")
	private String name;
	
	@Column(name = "CODER_ID")
	private long id_cod;	


	public GreenRole(long id, long id_cod) {
		super();
		this.id = id;
		this.id_cod = id_cod;
	}

	public GreenRole(long id, String name, long id_cod) {
		super();
		this.id = id;
		this.name = name;
		this.id_cod = id_cod;
	}

	public long getId_cod() {
		return id_cod;
	}

	public void setId_cod(long id_cod) {
		this.id_cod = id_cod;
	}

	public GreenRole(String name) {
		super();
		this.name = name;
	}

	protected GreenRole() {
	}

	public GreenRole(long id, String name) {
		this.id = id;
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

	@Override
	public String toString() {
		return "GreenRole [id=" + id + ", name=" + name + ", id_cod=" + id_cod + "]";
	}

	}

