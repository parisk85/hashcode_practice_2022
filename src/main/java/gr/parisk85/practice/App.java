package gr.parisk85.practice;

import gr.parisk85.practice.helper.InputReader;
import gr.parisk85.practice.helper.OutputWriter;
import gr.parisk85.practice.service.Algorithm;
import gr.parisk85.practice.service.YoutubeAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class App {

    private static final String A = "a_an_example.in.txt";
    private static final String B = "b_basic.in.txt";
    private static final String C = "c_coarse.in.txt";
    private static final String D = "d_difficult.in.txt";
    private static final String E = "e_elaborate.in.txt";

    private final InputReader reader;
    private final Algorithm algorithm;
    private final OutputWriter writer;

    public static void main(String[] args) {
        var filename = A;
        var app = new App(new InputReader(), new YoutubeAlgorithm(), new OutputWriter());
        var data = app.reader.read(filename);
        var output = app.algorithm.run(data);
        app.writer.write(output, filename);
    }
}
