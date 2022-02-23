package gr.parisk85.practice.helper;

import gr.parisk85.practice.model.Output;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputWriter {

    @SneakyThrows
    public void write(Output output, String filename) {
        String outputFilename = String.format("src/main/resources/%s-output.txt", filename);
        File file = new File(outputFilename);
        file.delete();
        Path path = Paths.get(file.getPath());
        Files.createFile(path);
        Files.writeString(path, formatOutput(output), StandardCharsets.US_ASCII);
    }

    private String formatOutput(Output output) {
        StringBuilder builder = new StringBuilder();

        //TODO: append output as string

        return builder.toString();
    }
}
