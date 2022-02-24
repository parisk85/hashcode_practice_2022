package gr.parisk85.practice.service;

import gr.parisk85.practice.model.Data;
import gr.parisk85.practice.model.Output;

import java.util.Comparator;

public class DummyAlgorithm implements Algorithm {
    @Override
    public Output run(Data data) {
        System.out.println(data);

        data.getProjects().stream()
                .sorted(Comparator.comparing(p -> p.getScore()))

        return null;
    }
}
