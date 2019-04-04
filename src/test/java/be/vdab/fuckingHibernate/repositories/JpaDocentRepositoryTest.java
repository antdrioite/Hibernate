package be.vdab.fuckingHibernate.repositories;

import be.vdab.fuckingHibernate.entities.Docent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/insertDocent.sql")
@Import(JpaDocentRepository.class)
public class JpaDocentRepository extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private JpaDocentRepository repository;
    private long idVanTestMan() {
        return super.jdbcTemplate.queryForObject("select id from docenten where voornaam = 'testM'", Long.class);
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
}
