package com.example.java.file.reader;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FileReaderTest {
    public static String loadFile(String url) {
        try (InputStream in = new URL(url).openStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("load File error", e);
        }
        return null;
    }

    public static void main(String[] args) {
        // это с файла в проекте
        byFileReader();

        // это с интернета
        byLoader();
    }

    public static void byLoader() {
        System.out.println(loadFile("https://yandex.ru/"));
    }

    private static void byFileReader() {
        try {
            // посимвольно
            FileReader fileReader = new FileReader("src/main/resources/files/test.txt");

            int i;
            while ((i = fileReader.read()) != -1) {
                System.out.println((char) i);
            }

            // массив
            char[] charArr = new char[10];
            fileReader.read(charArr);
            System.out.println(charArr);

            fileReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
