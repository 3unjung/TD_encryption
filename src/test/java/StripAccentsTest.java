import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StripAccentsTest {
    @Test
    public void stripAccentsTest() {
        String strWithAccents = "Quineunjung, ça c'est un nom non accentué !";
        String strippedExpected = "Quineunjung, ca c'est un nom non accentue !";
        String strippedActual = StringUtils.stripAccents(strWithAccents);

        Assert.assertEquals(strippedActual, strippedExpected, "La librairie de stripping est mal installée");
    }
}
