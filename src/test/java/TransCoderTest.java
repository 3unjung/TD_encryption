import org.testng.Assert;
import org.testng.annotations.Test;
import tools.TransCoder;

public class TransCoderTest {
    @Test
    public void encodeTest() {
        String keyEncrypted = "6Qe0IsEEH1utWRe7UKzGMiDTytOB3HS1dEfIB4imna3IRHXHRn5ZrvKFEcPjmPgKYGuytG+gDAl1m2DdHalJQg==";
        String decodedMsg = "àçdb F Jkl";
        String encodedExpected = "AJCFBZBVBNABBNCBAEAH";
        String encodedActual = new TransCoder(keyEncrypted).encode(decodedMsg);
        Assert.assertEquals(encodedActual, encodedExpected,
                "L'encodage 1 ne s'est pas déroulé correctement");

        decodedMsg = "Les tests sont là pour essayer !!!";
        encodedExpected = "BYAPASBNBGAPASBGASBNASAFBHBGBNAHAJBNAZAFBLADBNAPASASAJAMAPADBNBJBJBJ";
        encodedActual = new TransCoder(keyEncrypted).encode(decodedMsg);
        Assert.assertEquals(encodedActual, encodedExpected,
                "L'encodage 2 ne s'est pas déroulé correctement");
    }

    @Test
    public void decodeTest() {
        String keyEncrypted = "6Qe0IsEEH1utWRe7UKzGMiDTytOB3HS1dEfIB4imna3IRHXHRn5ZrvKFEcPjmPgKYGuytG+gDAl1m2DdHalJQg==";
        String encodedMsg = "AJCFBZBVBNABBNCBAEAH";
        String decodedExpected = "acdb F Jkl";
        String decodedActual = new TransCoder(keyEncrypted).decode(encodedMsg);
        Assert.assertEquals(decodedActual, decodedExpected, "Le décodage 1 ne s'est pas déroulé correctement");

        encodedMsg = "BYAPASBNBGAPASBGASBNASAFBHBGBNAHAJBNAZAFBLADBNAPASASAJAMAPADBNBJBJBJ";
        decodedExpected = "Les tests sont la pour essayer !!!";
        decodedActual = new TransCoder(keyEncrypted).decode(encodedMsg);
        Assert.assertEquals(decodedActual, decodedExpected, "Le décodage 2 ne s'est pas déroulé correctement");
    }
}
