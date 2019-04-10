package be.vdab.fuckingHibernate.repositories;

import be.vdab.fuckingHibernate.entities.Cursus;
import be.vdab.fuckingHibernate.entities.GroepsCursus;
import be.vdab.fuckingHibernate.entities.IndividueleCursus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaCursusRepository.class)
@Sql("/insertCursus.sql")
public class JpaCursusRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GROEPS_CURSUSSEN = "groepscursussen";
    private static final String INDIVIDUELE_CURSUSSEN = "individuelecursussen";
    private static final LocalDate EEN_DATUM = LocalDate.of(2019, 1, 1);
    @Autowired
    private JpaCursusRepository repository;
    @Autowired
    private EntityManager manager;
    private String idVanTestGroepCursus() {
        return super.jdbcTemplate.queryForObject(
                "select id from cursussen where naam='testGroep'", String.class
        );
    }
    private String idVanTestIndividueleCursus() {
        return super.jdbcTemplate.queryForObject(
                "select id from cursussen where naam='testIndividueel'", String.class
        );
    }
    @Test
    public void readGroepsCursus() {
        Optional<Cursus> optionalCursus = repository.read(idVanTestGroepCursus());
        assertEquals("testGroep", ((GroepsCursus) optionalCursus.get()).getNaam());
    }
    @Test
    public void readIndividueleCursus() {
        Optional<Cursus> optionalCursus = repository.read(idVanTestIndividueleCursus());
        assertEquals("testIndividueel", ((IndividueleCursus) optionalCursus.get()).getNaam());
    }
    @Test
    public void createGroepsCursus() {
        int aantalGroepsCursussen = super.countRowsInTable(GROEPS_CURSUSSEN);
        GroepsCursus cursus = new GroepsCursus("testGroep2", EEN_DATUM, EEN_DATUM);
        repository.create(cursus);
        manager.flush();
        assertEquals(aantalGroepsCursussen + 1, super.countRowsInTable(GROEPS_CURSUSSEN));
        assertEquals(1, super.countRowsInTableWhere(GROEPS_CURSUSSEN,  "id='" + cursus.getId() + "'"));
    }
    @Test
    public void createIndividueleCursus() {
        int aantalIndividueleCursussen = super.countRowsInTable(INDIVIDUELE_CURSUSSEN);
        IndividueleCursus cursus = new IndividueleCursus("testIndividueel2", 7);
        repository.create(cursus);
        manager.flush();
        assertEquals(aantalIndividueleCursussen + 1, super.countRowsInTable(INDIVIDUELE_CURSUSSEN));
        assertEquals(1, super.countRowsInTableWhere(INDIVIDUELE_CURSUSSEN,  "id='" + cursus.getId() + "'"));
    }

}
