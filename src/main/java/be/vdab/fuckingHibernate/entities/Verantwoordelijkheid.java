package be.vdab.fuckingHibernate.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "verantwoordelijkheden")
public class Verantwoordelijkheid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @ManyToMany
    @JoinTable(name = "docentenVerantwoordelijkheden",
    joinColumns = @JoinColumn(name = "verantwoordelijkheidid"),
    inverseJoinColumns = @JoinColumn(name = "docentid"))
    private Set<Docent> docenten = new LinkedHashSet<>();

    public Verantwoordelijkheid(String naam) {
        this.naam = naam;
    }

    protected Verantwoordelijkheid() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
    public boolean add(Docent docent) {
        boolean toegevoegd = docenten.add(docent);
        if (!docent.getVerantwoordelijkheden().contains(this)) {
            docent.add(this);
        }
        return toegevoegd;
    }
    public boolean remove(Docent docent) {
        boolean verwijderd = docenten.remove(docent);
        if(docent.getVerantwoordelijkheden().contains(this)) {
            docent.remove(this);
        }
        return verwijderd;
    }
    public Set<Docent> getDocenten() {
        return Collections.unmodifiableSet(docenten);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Verantwoordelijkheid)) return false;
        Verantwoordelijkheid that = (Verantwoordelijkheid) o;
        return naam.equals(that.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }
}
