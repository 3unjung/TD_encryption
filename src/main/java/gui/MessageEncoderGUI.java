package gui;

import model.Message;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class MessageEncoderGUI {
    public static void main(String[] args) throws IOException {
        PrintStream output = System.out;
        Scanner input = new Scanner(System.in);
        final String welcome =
                """
                        ╔══════════════════════════════════════╗
                        ║   Système d'encodage et de décodage  ║
                        ║             de messages              ║
                        ╠══════════════════════════════════════╣
                        ║ 1) Décoder un message                ║
                        ║ 2) Encode un message                 ║
                        ║ 3) Quitter                           ║
                        ╚══════════════════════════════════════╝""";

        while (true) {
            output.println(welcome);

            String encodedPath;
            String keyPath;
            String clearPath;
            Message message;
            int choice = input.nextInt();
            input.nextLine();
            //cleanInputStream(input);
            switch (choice) {
                case 1 -> {
                    output.println("Entrez le nom du fichier à décoder (sans extension)");
                    encodedPath = input.nextLine() + ".txt";
                    //cleanInputStream(input);
                    keyPath = askForKey(input, output);
                    clearPath = "user/clear.txt";
                    message = new Message(true, clearPath, encodedPath, keyPath);
                    output.println(
                            """
                                    la clé a été enregistrée
                                    ====== Décodage =====""");
                    message.readAndWrite();
                    output.println("Le message décodé se trouve : " + clearPath);
                }
                case 2 -> {
                    output.println("Entrez le nom du fichier à encoder (sans extension)");
                    clearPath = input.nextLine() + ".txt";
                    //cleanInputStream(input);
                    keyPath = askForKey(input, output);
                    encodedPath = "user/encoded.txt";
                    message = new Message(false, clearPath, encodedPath, keyPath);
                    output.println(
                            """
                                    la clé a été enregistrée
                                    ====== Encodage =====""");
                    message.readAndWrite();
                    output.println("Le message encodé se trouve : " + encodedPath);
                }
                case 3 -> System.exit(0);
                default -> {
                    output.println("Veuillez entrer un choix valide");
                }
            }
        }
    }

    private static void cleanInputStream(Scanner input) {
        input.skip(".");
        /*System.in.available()
        while (input.hasNextLine()) {
            input.nextLine();
        }*/
    }

    private static String askForKey(Scanner input, PrintStream output) {
        output.println("Entrez le nom du fichier contenant la clé de décodage (sans extension)");
        String key = input.nextLine() + ".txt";
        //cleanInputStream(input);
        return key;
    }
}
