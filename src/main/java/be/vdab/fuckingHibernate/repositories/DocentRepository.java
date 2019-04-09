package be.vdab.fuckingHibernate.repositories;

import be.vdab.fuckingHibernate.entities.Docent;
import be.vdab.fuckingHibernate.queryresult.AantalDocentenPerWedde;
import be.vdab.fuckingHibernate.queryresult.IdEnEmailAdres;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DocentRepository {
    Optional<Docent> read(long id);
    void create(Docent docent);
    void delete(long id);
    List<Docent> findAll();
    List<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot);
    List<String> findEmailAdressen();
    List<IdEnEmailAdres> findIdsEnEmailAdressen();
    BigDecimal findGrootsteWedde();
    List<AantalDocentenPerWedde> findAantalDocentenPerWedde();
    int algemeneOpslag(BigDecimal percentage);
}
