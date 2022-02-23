package gr.parisk85.practice.helper;

import gr.parisk85.practice.model.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

public class InputReader {

    public Data read(String filename) {
        var input = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filename)))).lines().collect(Collectors.toList());

        var dataBuilder = Data.builder();
        //TODO: get input to create data model object

        return dataBuilder.build();
    }
}
