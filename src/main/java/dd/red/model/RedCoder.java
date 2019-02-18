package dd.red.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RED_CODERS")
public class RedCoder {
    @Id
    @Column(name = "CODER_ID")
    private long id;

    @Column(name = "CODER_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private RedTeam team;

    protected RedCoder() {
    }

    public RedCoder(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RedCoder(long id, String name, RedTeam team) {
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

    public RedTeam getTeam() {
        return team;
    }

    public void setTeam(RedTeam team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "I componenti sono " + name + " nel team" + team ;
    }
}