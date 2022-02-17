package gr.parisk85.practice.service;

import gr.parisk85.practice.model.Client;
import gr.parisk85.practice.model.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ParisAlgorithmForDDataSet implements Algorithm {
    @Override
    public List<String> run(Data data) {
        data.getIngredients().stream()
                .sorted(Comparator.comparing(ing -> getIngredientRating(ing, data.getClients()), Comparator.reverseOrder()));

        var result = data.getIngredients().subList(0, data.getIngredients().size() * 9 / 10);

        var potentialClients = data.getClients().stream()
                .map(client -> result.containsAll(client.getLikeList()) && Collections.disjoint(client.getDislikeList(), result))
                .mapToInt(b -> b ? 1 : 0)
                .sum();

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
