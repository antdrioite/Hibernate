package be.vdab.fuckingHibernate.repositories;

import be.vdab.fuckingHibernate.entities.Docent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaDocentRepository implements DocentRepository {
    private final EntityManager manager;

    public JpaDocentRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Docent> read(long id) {
        return Optional.ofNullable(manager.find(Docent.class, id));
    }
}
