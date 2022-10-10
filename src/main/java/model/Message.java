package model;

import tools.TransCoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Message {
    private final Boolean isEncoded;
    private List<String> msgClear;
    private List<String> msgEncoded;
    private final String msgClearPath;
    private final String msgEncodedPath;
    private final String keyPath;
    private final String key;
    private final TransCoder transCoder;

    public Message(Boolean isEncoded, String msgClearPath, String msgEncodedPath, String keyPath) throws FileNotFoundException {
        this.isEncoded = isEncoded;
        this.msgClearPath = msgClearPath;
        this.msgEncodedPath = msgEncodedPath;
        this.keyPath = keyPath;

        key = new Scanner(new File(keyPath)).nextLine();
        transCoder = new TransCoder(key);
    }

    public void readAndWrite() throws IOException {
        msgClear = new ArrayList<>();
        msgEncoded = new ArrayList<>();

        String readPath;
        String writePath;
        List<String> readLines;
        List<String> writeLines;
        Function<String, String> transCoderFun;
        if (isEncoded) {
            readPath = msgEncodedPath;
            writePath = msgClearPath;
            readLines = msgEncoded;
            writeLines = msgClear;
            transCoderFun = transCoder::decode;
        } else {
            readPath = msgClearPath;
            writePath = msgEncodedPath;
            readLines = msgClear;
            writeLines = msgEncoded;
            transCoderFun = transCoder::encode;
        }

        Scanner scanner;
        scanner = new Scanner(new File(readPath));
        while (scanner.hasNextLine()) {
            readLines.add(scanner.nextLine());
        }
        scanner.close();

        for (String line : readLines) {
            writeLines.add(transCoderFun.apply(line));
        }

        try (PrintStream fileWriter = new PrintStream(writePath)) {
            for (String line : writeLines) {
                fileWriter.println(line);
            }
        }
    }
}
