package gr.parisk85.practice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.parisk85.practice.model.Data;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class Utils {

    @SneakyThrows
    public static void produceJson(Data data, String filename) {
        String outputFilename = String.format("src/main/resources/%s.json", filename);
        File file = new File(outputFilename);
        file.delete();
        Path path = Paths.get(file.getPath());
        Files.createFile(path);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);

        Files.writeString(path, jsonString, StandardCharsets.UTF_8);
    }
}
