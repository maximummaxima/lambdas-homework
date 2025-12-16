package ru.itpark.sb;

import ru.itpark.sb.json.JsonPractice;
import ru.itpark.sb.stream.StreamApiPractice;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        var streamPractice = new StreamApiPractice();
        streamPractice.run();

        try {
            var jsonPractice = new JsonPractice();
            jsonPractice.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}