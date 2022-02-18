package gr.parisk85.practice.service;

import gr.parisk85.practice.model.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParisTestAlgorithm implements Algorithm {
    @Override
    public List<String> run(Data data) {
        var map = new HashMap<String, ArrayList<Integer>>();
        for (var ing : data.getIngredients()) {
            var list = new ArrayList<Integer>();
            for (var client : data.getClients()) {
                var res = 0;
                if (client.getLikeList().contains(ing)) {
                    res = 1;
                } else if (client.getDislikeList().contains(ing)) {
                    res = -1;
                }
                list.add(res);
            }
            map.put(ing, list);
        }

        var res = map.entrySet().stream()
                .filter(e -> e.getValue().stream().filter(i -> i == 1).count()
                        - e.getValue().stream().filter(i -> i == -1).count()
                        >= 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        var potentialClients = data.getClients().stream()
                .map(client -> res.containsAll(client.getLikeList()) && Collections.disjoint(client.getDislikeList(), res))
                .mapToInt(b -> b ? 1 : 0)
                .sum();

        System.out.println(potentialClients);

        return res;
    }
}
