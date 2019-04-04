package be.vdab.fuckingHibernate.repositories;

import be.vdab.fuckingHibernate.entities.Docent;

import java.util.Optional;

public interface DocentRepository {
    Optional<Docent> read(long id);
}
