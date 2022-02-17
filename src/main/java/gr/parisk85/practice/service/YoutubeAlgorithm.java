package gr.parisk85.practice.service;

import gr.parisk85.practice.model.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class YoutubeAlgorithm implements Algorithm {
    @Override
    public List<String> run(Data data) {
        data.getClients().sort(Comparator.comparing(cl -> cl.getDislikeList().size()));

        var result = new ArrayList<String>();

        for (var client : data.getClients()) {
            for (var ing : client.getLikeList()) {
                if (!result.contains(ing) && Collections.disjoint(client.getDislikeList(), result)) {
                    result.add(ing);
                }
            }
        }

        var potentialClients = data.getClients().stream()
                .map(client -> result.containsAll(client.getLikeList()) && Collections.disjoint(client.getDislikeList(), result))
                .mapToInt(b -> b ? 1 : 0)
                .sum();

        System.out.println(potentialClients);

        return result;
    }
}
