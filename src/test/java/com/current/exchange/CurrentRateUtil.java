package com.current.exchange;

import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class CurrentRateUtil {
    private static final String PATH_TO_TEST_FILE = "src/test/java/resources/test_file.txt";

    public String getCurrentRateInfoFromTestFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(PATH_TO_TEST_FILE), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return stringBuilder.toString();
    }
}
