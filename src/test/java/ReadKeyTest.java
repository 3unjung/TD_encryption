import org.germain.tool.ManaBox;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReadKeyTest {
    @Test
    public void readKeyTest() {
        // La clé cryptée donnée plus haut
        String keyEncrypted = "6Qe0IsEEH1utWRe7UKzGMiDTytOB3HS1dEfIB4imna3IRHXHRn5ZrvKFEcPjmPgKYGuytG+gDAl1m2DdHalJQg==";
        // La clé décryptée que nous devrions obtenir
        String keyDecrypted = "CFfrkowl.aDzyS:eHjsGPZgMApWvRYVmtnK!BuU IQiEXTxbqhLdNJO,'c";
        // Le test d'égalité entre la clé attendue et la sortie de la méthode de la librairie.
        // Si le décryptage ne fonctionne pas le message d'erreur nous expliquera pourquoi.
        Assert.assertEquals(ManaBox.decrypt(keyEncrypted), keyDecrypted,
                "La librairie de décryptage est mal installée");
    }
}
