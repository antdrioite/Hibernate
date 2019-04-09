package be.vdab.fuckingHibernate.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("G")
public class GroepsCursus extends Cursus {
    private static final long serialVersionUID = 1L;
    private LocalDate van;
    private LocalDate tot;

    public GroepsCursus(String naam, LocalDate van, LocalDate tot) {
        super(naam);
        this.van = van;
        this.tot = tot;
    }

    public GroepsCursus() {
    }

    public LocalDate getVan() {
        return van;
    }

    public LocalDate getTot() {
        return tot;
    }
}
