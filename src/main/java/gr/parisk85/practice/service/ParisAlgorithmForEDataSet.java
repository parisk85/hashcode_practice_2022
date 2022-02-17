package gr.parisk85.practice.service;

import gr.parisk85.practice.model.Client;
import gr.parisk85.practice.model.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParisAlgorithmForEDataSet implements Algorithm {
    @Override
    public List<String> run(Data data) {
        var notDisliked = data.getIngredients().stream()
                .map(ing -> Pair.of(ing, data.getClients().stream().map(Client::getDislikeList).filter(l -> l.contains(ing)).count()))
                .filter(p -> p.getRight() > 0)
                .map(Pair::getKey)
                .collect(Collectors.toList());

        var notLiked = data.getIngredients().stream()
                .map(ing -> Pair.of(ing, data.getClients().stream().map(Client::getLikeList).filter(l -> l.contains(ing)).count()))
                .filter(p -> p.getRight() < 8)
                .map(Pair::getKey)
                .collect(Collectors.toList());

        var result = Stream.concat(notDisliked.stream(), notLiked.stream()).collect(Collectors.toList());

        var potentialClients = data.getClients().stream()
                .map(client -> result.containsAll(client.getLikeList()) && Collections.disjoint(client.getDislikeList(), result))
                .mapToInt(b -> b ? 1 : 0)
                .sum();

        data.getIngredients().stream()
                .sorted(Comparator.comparing(ing -> getIngredientRating(ing, data.getClients()), Comparator.reverseOrder()))
                .forEach(ing -> System.out.printf("%s %d\n", ing, getIngredientRating(ing, data.getClients())));

        System.out.printf("Potential Clients %d%n", potentialClients);

        return result;
    }

    private int getIngredientRating(String ingredient, List<Client> clients) {
        return clients.stream()
                .map(c -> c.getLikeList().contains(ingredient) && !c.getDislikeList().contains(ingredient))
                .mapToInt(b -> b ? 1 : 0)
                .sum();
    }
}
