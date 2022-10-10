import model.Message;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

// On teste seulement que les fichiers soient créés et non vides car on a déjà tester encode et decode dans une autre
// classe
public class MessageTest {
    @Test
    public void testReadAndWriteClear() throws IOException {
        String clearPath = "src/test/files/clear.txt";
        String encodedPathStr = "src/test/files/encoded.txt";
        try (PrintStream fileWriter = new PrintStream(clearPath)) {
            fileWriter.println("Tester c'est important à ce qu'on m'a dit");
            fileWriter.println("On m'a aussi dit qu'il fallait plusieurs lignes");
        }

        new File(encodedPathStr).delete();

        new Message(false, clearPath, encodedPathStr, "user/key.txt")
                .readAndWrite();

        Path encodedPath = Path.of(encodedPathStr);
        Assert.assertTrue(Files.exists(encodedPath));
        Assert.assertNotEquals(Files.size(encodedPath), 0);
    }

    @Test
    public void testReadAndWriteEncoded() throws IOException {
        String clearPathStr = "src/test/files/clear.txt";
        String encodedPath = "src/test/files/encoded.txt";
        try (PrintStream fileWriter = new PrintStream(encodedPath)) {
            fileWriter.println("BTAPASBGAPADBNCFCEAPASBGBNBQBFAZAFADBGAJBHBGBNAJBNCFAPBNBWBLCEAFBHBNBFCEAJBNBZBQBG");
            fileWriter.println("CCBHBNBFCEAJBNAJBLASASBQBNBZBQBGBNBWBLCEBQAHBNACAJAHAHAJBQBGBNAZAHBLASBQAPBLADASBNAHBQAWBHAPAS");
        }

        new File(clearPathStr).delete();

        new Message(true, clearPathStr, encodedPath, "user/key.txt")
                .readAndWrite();

        Path clearPath = Path.of(clearPathStr);
        Assert.assertTrue(Files.exists(clearPath));
        Assert.assertNotEquals(Files.size(clearPath), 0);
    }
}
