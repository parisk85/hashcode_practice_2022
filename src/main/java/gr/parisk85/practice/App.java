package gr.parisk85.practice;

import gr.parisk85.practice.helper.InputReader;
import gr.parisk85.practice.helper.OutputWriter;
import gr.parisk85.practice.service.Algorithm;
import gr.parisk85.practice.service.ParisTestAlgorithm;
import gr.parisk85.practice.service.YoutubeAlgorithm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

@RequiredArgsConstructor
public class App {

    private static final String A = "a_an_example.in.txt";
    private static final String B = "b_basic.in.txt";
    private static final String C = "c_coarse.in.txt";
    private static final String D = "d_difficult.in.txt";
    private static final String E = "e_elaborate.in.txt";

    private static final Map<String, Algorithm> INPUTS = Map.of(
            A, new ParisTestAlgorithm(),
            B, new ParisTestAlgorithm(),
            C, new ParisTestAlgorithm(),
            D, new YoutubeAlgorithm()
    );

    private final InputReader reader;
    private final Algorithm algorithm;
    private final OutputWriter writer;

    public static void main(String[] args) {
        INPUTS.entrySet().stream()
                .map(input -> Pair.of(input.getKey(), new App(new InputReader(), input.getValue(), new OutputWriter())))
                .forEach(pair -> {
                    var filename = pair.getKey();
                    var data = pair.getValue().reader.read(filename);
                    var output = pair.getValue().algorithm.run(data);
                    pair.getValue().writer.write(output, filename);
                });
    }
}
