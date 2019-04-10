package be.vdab.fuckingHibernate.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cursus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String naam;

    public Cursus(String naam) {
        this.naam = naam;
        id = UUID.randomUUID().toString();
    }

    protected Cursus() {
    }

    public String getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
