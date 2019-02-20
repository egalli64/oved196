package dd.red.model;

import java.util.List;
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
@Table(name = "RED_CODERS")
public class RedCoder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODER_ID")
    private long id;

    @Column(name = "CODER_NAME")
    private String name;

	@ManyToMany (fetch=FetchType.EAGER)
	@JoinTable(name = "RED_CODERS_ROLES", //
			joinColumns = @JoinColumn(name = "CODER_ID"), //
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	Set<RedRole> roles;
    
    
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private RedTeam team;

    public RedCoder() {
    }

    public RedCoder( String name) {
       
        this.name = name;
    }

    public RedCoder( String name, RedTeam team) {
        
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

    public RedTeam getTeam() {
        return team;
    }

    public void setTeam(RedTeam team) {
        this.team = team;
    }
    

    public Set<RedRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<RedRole> roles) {
		this.roles = roles;
	}

	@Override
    public String toString() {
        return " I componenti sono " + name + " nel team" + team ;
    }
}