package be.vdab.fuckingHibernate.repositories;

import be.vdab.fuckingHibernate.entities.Cursus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;
@Repository
public class JpaCursusRepository implements CursusRepository {
    private final EntityManager manager;

    public JpaCursusRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Cursus> read(String id) {
        return Optional.ofNullable(manager.find(Cursus.class, id));
    }

    @Override
    public void create(Cursus cursus) {
        manager.persist(cursus);
    }
}
