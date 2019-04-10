package be.vdab.fuckingHibernate.repositories;

import be.vdab.fuckingHibernate.entities.Campus;

import java.util.Optional;

public interface CampusRepository {
    void create(Campus campus);
    Optional<Campus> read(long id);
}
