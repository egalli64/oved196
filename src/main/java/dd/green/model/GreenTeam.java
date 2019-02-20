package dd.green.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GREEN_TEAMS")
public class GreenTeam {
    @Id
    @Column(name = "TEAM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TEAM_NAME")
    private String name;
    
    

    public GreenTeam(long id) {
		super();
		this.id = id;
	}

	public GreenTeam(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
        return id;
    }

    protected GreenTeam() {
    }

    public GreenTeam(String name) {
        this.name = name;
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
        return "GreenTeam [id=" + id + ", name=" + name + "]";
    }
}
