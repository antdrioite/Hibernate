package be.vdab.fuckingHibernate.repositories;

import be.vdab.fuckingHibernate.entities.Cursus;

import java.util.Optional;

public interface CursusRepository {
    Optional<Cursus> read(String id);
    void create(Cursus cursus);
}
