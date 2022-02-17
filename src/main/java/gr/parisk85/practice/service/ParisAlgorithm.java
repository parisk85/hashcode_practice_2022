package gr.parisk85.practice.service;

import gr.parisk85.practice.model.Client;
import gr.parisk85.practice.model.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ParisAlgorithm implements Algorithm {
    @Override
    public List<String> run(Data data) {
        data.getIngredients().stream()
                .sorted(Comparator.comparing(ing -> getIngredientRating(ing, data.getClients()), Comparator.reverseOrder()))
                .forEach(ing -> System.out.printf("%s rating: %d%n", ing, getIngredientRating(ing, data.getClients())));

        List<String> toRemove = new ArrayList<>();
        for (var ing : data.getIngredients()) {
            for (var cl : data.getClients()) {
                if (cl.getDislikeList().contains(ing)) {
                    toRemove.add(ing);
                }
            }
        }
        data.getIngredients().removeAll(toRemove);

        System.out.println(data.getIngredients());

        var potentialClients = data.getClients().stream()
                .map(client -> data.getIngredients().containsAll(client.getLikeList()) && Collections.disjoint(client.getDislikeList(), data.getIngredients()))
                .mapToInt(b -> b ? 1 : 0)
                .sum();

        System.out.printf("Potential Clients %d%n", potentialClients);

        return data.getIngredients();
    }

    private int getIngredientRating(String ingredient, List<Client> clients) {
        return clients.stream()
                .map(c -> c.getLikeList().contains(ingredient) && !c.getDislikeList().contains(ingredient))
                .mapToInt(b -> b ? 1 : 0)
                .sum();
    }
}
