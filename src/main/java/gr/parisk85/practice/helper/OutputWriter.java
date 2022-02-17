package gr.parisk85.practice.helper;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OutputWriter {

    @SneakyThrows
    public void write(List<String> output, String filename) {
        String outputFilename = String.format("src/main/resources/%s-output.txt", filename);
        File file = new File(outputFilename);
        file.delete();
        Path path = Paths.get(file.getPath());
        Files.createFile(path);

        StringBuilder builder = new StringBuilder();
        builder.append(output.size());
        builder.append(" ");
        builder.append(String.join(" ", output));

        Files.writeString(path, builder.toString(), StandardCharsets.US_ASCII);
    }
}
