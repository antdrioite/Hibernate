package be.vdab.fuckingHibernate.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("I")
public class IndividueleCursus extends Cursus {
    private static final long serialVersionUID = 1L;
    private int duurtijd;

    public IndividueleCursus(String naam, int duurtijd) {
        super(naam);
        this.duurtijd = duurtijd;
    }

    public IndividueleCursus() {
    }

    public int getDuurtijd() {
        return duurtijd;
    }
}
