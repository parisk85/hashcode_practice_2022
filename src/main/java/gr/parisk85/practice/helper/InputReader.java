package gr.parisk85.practice.helper;

import gr.parisk85.practice.model.Client;
import gr.parisk85.practice.model.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InputReader {

    public Data read(String filename) {
        var list = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filename)))).lines().collect(Collectors.toList());

        var dataBuilder = Data.builder().potentialClients(Integer.parseInt(list.get(0)));

        var ingredients = list.stream().map(s -> s.split(" "))
                .map(List::of)
                .map(l -> l.stream().skip(1).collect(Collectors.toList()))
                .filter(l -> !l.isEmpty())
                .flatMap(List::stream)
                .collect(Collectors.toSet());

        var preferences = list.stream().skip(1).map(s -> s.split(" "))
                .map(List::of)
                .map(l -> l.stream().skip(1).collect(Collectors.toList()))
                .collect(Collectors.toList());

        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < preferences.size(); i += 2) {
            var client = Client.builder()
                    .likeList(preferences.get(i))
                    .dislikeList(preferences.get(i + 1))
                    .build();

            clients.add(client);
        }

        return dataBuilder
                .ingredients(ingredients)
                .clients(clients)
                .build();
    }
}
