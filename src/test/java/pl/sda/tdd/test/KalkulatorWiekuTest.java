package pl.sda.tdd.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pl.sda.tdd.KalkulatorWieku;

public class KalkulatorWiekuTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void poprawnyFormatDatyUrodzenia(){
        exception.expect(RuntimeException.class);
        exception.expectMessage("Data urodzenia");
        KalkulatorWieku.obliczWiek("2001-10-10", "10-10-2010");
    }

    @Test
    public void poprawnyFormatWybranejDaty(){
        exception.expect(RuntimeException.class);
        exception.expectMessage("Wybrana data");
        KalkulatorWieku.obliczWiek("10-10-2001", "2010-10-10");
    }

    @Test
    public void dataUrodzeniaMusiBycNieWiekszaOdWybranejDaty(){
        exception.expect(RuntimeException.class);
        exception.expectMessage("Data urodzenia nie może być później niż ");
        KalkulatorWieku.obliczWiek("10-10-2001", "10-10-1990");
    }

    @Test
    public void dzienPrzedUrodzinamiOsobaJestRokMlodsza(){
        Assert.assertEquals(10, KalkulatorWieku.obliczWiek("02-05-2000", "01-05-2011"));
    }

    @Test
    public void osobaNieMozeMiecWiecejNiz120Lat(){
        exception.expect(RuntimeException.class);
        exception.expectMessage("120 lat");
        KalkulatorWieku.obliczWiek("01-01-1900", "01-01-2021");
    }

}
