package dd.green.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GREEN_ROLES")
public class GreenRole {
    @Id
    @Column(name = "ROLE_ID")
    private long id;

    @Column(name = "ROLE_NAME")
    private String name;
    @ManyToMany
  	@JoinTable(name = "COD_ROLE", //
  			joinColumns = @JoinColumn(name = "CODER_ID"), //
  			inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
  	Set<GreenCoder> coders;

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
        return "GreenRole [id=" + id + ", name=" + name + "]";
    }
}
