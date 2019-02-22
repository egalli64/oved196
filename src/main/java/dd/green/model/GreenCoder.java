package dd.green.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FIRST_NAME")
    private String name;
    
    @Column(name = "LAST_NAME")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private GreenTeam team;
    
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "GREEN_COD_ROLE", //
			joinColumns = @JoinColumn(name = "CODER_ID"), //
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	Set<GreenRole> roles;
	
	

    public GreenCoder(String name, String surname, GreenTeam team) {
	
		this.name = name;
		this.surname = surname;
		this.team = team;
	}

	public GreenCoder(String name, String surname, GreenTeam team, Set<GreenRole> roles) {
	
		this.name = name;
		this.surname = surname;
		this.team = team;
		this.roles = roles;
	}

	public GreenCoder(String name, String surname, Set<GreenRole> roles) {
	
		this.name = name;
		this.surname = surname;
		this.roles = roles;
	}

	public GreenCoder(String name, String surname) {
	
		this.name = name;
		this.surname = surname;
	}

	public GreenCoder(long id, String name, String surname, Set<GreenRole> roles) {
	
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.roles = roles;
	}

	public GreenCoder(long id, String name, String surname, GreenTeam team, Set<GreenRole> roles) {
	
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.team = team;
		this.roles = roles;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Set<GreenRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<GreenRole> roles) {
		this.roles = roles;
	}

	protected GreenCoder() {
    }

    public GreenCoder(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GreenCoder(long id, String name, GreenTeam team) {
        this.id = id;
        this.name = name;
        this.team = team;
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
		return "GreenCoder [id=" + id + ", name=" + name + ", surname=" + surname + ", team=" + team + ", roles="
				+ roles + "]";
	}



}
