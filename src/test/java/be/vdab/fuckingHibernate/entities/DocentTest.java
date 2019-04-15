package be.vdab.fuckingHibernate.entities;

import be.vdab.fuckingHibernate.enums.Geslacht;
import be.vdab.fuckingHibernate.valueobjects.Adres;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DocentTest {
    private final static BigDecimal ORIGINELE_WEDDE = BigDecimal.valueOf(200);
    private Campus campus1;
    private Docent docent1;
    @Before
    public void before() {
        campus1 = new Campus("test", new Adres("test", "test", "test", "test"));
        docent1 = new Docent("test", "test", ORIGINELE_WEDDE, "test@fietsacademy.be", Geslacht.MAN/*, campus1*/);
    }

    @Test
    public void opslag() {
        docent1.opslag(BigDecimal.TEN);
        assertEquals(0, BigDecimal.valueOf(220).compareTo(docent1.getWedde()));
    }
    @Test(expected = NullPointerException.class)
    public void opslagMetNullKanNiet() {
        docent1.opslag(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void opslagMet0KanNiet() {
        docent1.opslag(BigDecimal.ZERO);
        assertEquals(0, ORIGINELE_WEDDE.compareTo(docent1.getWedde()));
    }
    @Test(expected = IllegalArgumentException.class)
    public void negatieveOpslagKanNiet() {
        docent1.opslag(BigDecimal.valueOf(-1));
        assertEquals(0, ORIGINELE_WEDDE.compareTo(docent1.getWedde()));
    }
    @Test
    public void eenNieuweDocentHeeftGeenBijnamen() {
        assertTrue(docent1.getBijnamen().isEmpty());
    }
    @Test
    public void bijnaamToevoegen() {
        assertTrue(docent1.addBijnaam("test"));
        assertEquals(1, docent1.getBijnamen().size());
        assertTrue(docent1.getBijnamen().contains("test"));
    }
    @Test
    public void tweeKeerDezelfdeBijnaamToevoegenKanNiet() {
        docent1.addBijnaam("test");
        assertFalse(docent1.addBijnaam("test"));
        assertEquals(1, docent1.getBijnamen().size());
    }
    @Test(expected = NullPointerException.class)
    public void nullAlsBijnaamToevoegenKanNiet() {
        docent1.addBijnaam(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void eenLegeBijnaamToevoegenKanNiet() {
        docent1.addBijnaam("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void eenBijnaamMetEnkelSpatiesToevoegenKanNiet() {
        docent1.addBijnaam(" ");
    }
    @Test
    public void bijnaamVerwijderen() {
        docent1.addBijnaam("test");
        assertTrue(docent1.removeBijnaam("test"));
        assertTrue(docent1.getBijnamen().isEmpty());
    }
    @Test
    public void eenBijnaamVerwijderenDieJeNietToevoegdeKanNiet() {
        docent1.addBijnaam("test");
        assertFalse(docent1.removeBijnaam("test2"));
        assertEquals(1, docent1.getBijnamen().size());
        assertTrue(docent1.getBijnamen().contains("test"));
    }


}
