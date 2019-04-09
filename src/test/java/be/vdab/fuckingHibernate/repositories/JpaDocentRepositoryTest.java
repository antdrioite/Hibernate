package be.vdab.fuckingHibernate.repositories;

import be.vdab.fuckingHibernate.entities.Docent;
import be.vdab.fuckingHibernate.enums.Geslacht;
import org.junit.Before;
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
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/insertDocent.sql")
@Import(JpaDocentRepository.class)
public class JpaDocentRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private JpaDocentRepository repository;
    @Autowired
    private EntityManager manager;
    private static final String DOCENTEN = "docenten";
    private Docent docent;
    private long idVanTestMan() {
        return super.jdbcTemplate.queryForObject("select id from docenten where voornaam = 'testM'", Long.class);
    }
    private long idVanTestVrouw() {
        return super.jdbcTemplate.queryForObject("select id from docenten where voornaam='testV'", Long.class);
    }

    @Before
    public void before() {
        docent = new Docent("test", "test", BigDecimal.TEN, "test@fietsacademy.be", Geslacht.MAN);
    }
    @Test
    public void create() {
        int aantalDocenten = super.countRowsInTable(DOCENTEN);
        repository.create(docent);
        assertEquals(aantalDocenten + 1, super.countRowsInTable("docenten"));
        assertNotEquals(0, docent.getId());
        assertEquals(1, super.countRowsInTableWhere(DOCENTEN, "id=" + docent.getId()));
    }
    @Test
    public void read() {
        Docent docent = repository.read(idVanTestMan()).get();
        assertEquals("testM", docent.getVoornaam());
    }
    @Test
    public void readOnbestaandeDocent() {
        assertFalse(repository.read(-1).isPresent());
    }

    @Test
    public void man() {
        assertEquals(Geslacht.MAN, repository.read(idVanTestMan()).get().getGeslacht());
    }
    @Test
    public void vrouw() {
        assertEquals(Geslacht.VROUW, repository.read(idVanTestVrouw()).get().getGeslacht());
    }
    @Test
    public void delete() {
        long id = idVanTestMan();
        int aantalDocenten = super.countRowsInTable(DOCENTEN);
        repository.delete(id);
        manager.flush();
        assertEquals(aantalDocenten - 1, super.countRowsInTable(DOCENTEN));
        assertEquals(0, super.countRowsInTableWhere(DOCENTEN, "id=" + id));
    }
    @Test
    public void findAll() {
        List<Docent> docenten = repository.findAll();
        assertEquals(super.countRowsInTable(DOCENTEN), docenten.size());
        BigDecimal vorigeWedde = BigDecimal.ZERO;
        for (Docent docent : docenten) {
            assertTrue(docent.getWedde().compareTo(vorigeWedde) >= 0);
            vorigeWedde = docent.getWedde();
        }
    }

    @Test
    public void findByWeddeBetween() {
        BigDecimal duizend = BigDecimal.valueOf(1_000);
        BigDecimal tweeduizend = BigDecimal.valueOf(2_000);
        List<Docent> docenten = repository.findByWeddeBetween(duizend, tweeduizend);
        long aantalDocenten = super.countRowsInTableWhere(DOCENTEN, "wedde between 1000 and 2000");
        assertEquals(aantalDocenten, docenten.size());
        docenten.forEach(docent -> {
            assertTrue(docent.getWedde().compareTo(duizend) >= 0);
            assertTrue(docent.getWedde().compareTo(tweeduizend) <= 0);
        });
    }

}
