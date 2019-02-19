package dd.green.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GREEN_CODERS")
public class GreenCoder {
    @Id
    @Column(name = "CODER_ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private GreenTeam team;

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
        return "GreenCoder [id=" + id + ", name=" + name + ", team=" + team + "]";
    }
}
