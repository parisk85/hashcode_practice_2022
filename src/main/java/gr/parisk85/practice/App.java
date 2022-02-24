package gr.parisk85.practice;

import gr.parisk85.practice.helper.InputReader;
import gr.parisk85.practice.helper.OutputWriter;
import gr.parisk85.practice.service.Algorithm;
import gr.parisk85.practice.service.DummyAlgorithm;
import gr.parisk85.practice.util.Utils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class App {

    //TODO: replace with input filename
    private static final String INPUT_FILENAME = "a_an_example.in.txt";

    private final InputReader reader;
    private final Algorithm algorithm;
    private final OutputWriter writer;

    public static void main(String[] args) {
        var app = new App(new InputReader(), new DummyAlgorithm(), new OutputWriter());
        var data = app.reader.read(INPUT_FILENAME);
        var output = app.algorithm.run(data);
        app.writer.write(output, INPUT_FILENAME);
        //Json for Javascript language speakers
        Utils.produceJson(data, INPUT_FILENAME);
    }

}
