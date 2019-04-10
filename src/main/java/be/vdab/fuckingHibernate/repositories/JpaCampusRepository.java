package be.vdab.fuckingHibernate.repositories;

import be.vdab.fuckingHibernate.entities.Campus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaCampusRepository implements CampusRepository{
    private final EntityManager manager;

    public JpaCampusRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Campus campus) {
        manager.persist(campus);
    }

    @Override
    public Optional<Campus> read(long id) {
        return Optional.ofNullable(manager.find(Campus.class, id));
    }
}
