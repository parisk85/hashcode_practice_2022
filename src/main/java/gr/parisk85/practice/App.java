package gr.parisk85.practice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class App {

    private static final String A = "a_an_example.in.txt";
    private static final String B = "b_basic";
    private static final String C = "c_coarse.in.txt";
    private static final String D = "d_difficult.in.txt";
    private static final String E = "e_elaborate.in.txt";

    private final InputReader reader;
    private final Algorithm algorithm;

    public static void main(String[] args) {
        var app = new App(new InputReader(), new Algorithm());
        var data = app.reader.read(A);
        app.algorithm.run(data);
    }
}
