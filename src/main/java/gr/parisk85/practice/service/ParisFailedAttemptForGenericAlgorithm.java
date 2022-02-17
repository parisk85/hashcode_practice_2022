package gr.parisk85.practice.service;

import gr.parisk85.practice.model.Client;
import gr.parisk85.practice.model.Data;
import gr.parisk85.practice.model.Individual;
import gr.parisk85.practice.model.Population;
import gr.parisk85.practice.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParisFailedAttemptForGenericAlgorithm implements Algorithm {

    private static final int POP_SIZE = 10;
    private static final int MAX_INGREDIENTS = 5;
    private static final int CROSSOVER_SIZE = POP_SIZE / 3;

    @Override
    public List<String> run(Data data) {
        var mostLiked = getMostLikedIngredient(data);

        var mostDisliked = getMostDislikedIngredient(data);

        Population<String> initialPopulation = createInitialPopulation(data.getIngredients());

        initialPopulation.getPopulation().sort(Comparator.comparing(ind -> individualFitness(data, ind), Comparator.reverseOrder()));

        Population<String> nextGeneration = Population.<String>builder()
                .population(initialPopulation.getPopulation().subList(0, CROSSOVER_SIZE))
                .build();

        var joined = Stream.concat(initialPopulation.getPopulation().get(0).getIndividual().stream(),
                        initialPopulation.getPopulation().get(1).getIndividual().stream())
                .distinct()
                .collect(Collectors.toList());

        IntStream.range(0, CROSSOVER_SIZE)
                .forEach(i -> {
                    Collections.shuffle(joined);
                    nextGeneration.getPopulation().add(Individual.<String>builder()
                            .individual(new HashSet<>(joined.subList(0, Math.min(joined.size(), MAX_INGREDIENTS))))
                            .build());
                });

        System.out.println(initialPopulation.getPopulation());
        System.out.println(calculateFitness(data, initialPopulation));
        System.out.println(nextGeneration);
        System.out.println(calculateFitness(data, nextGeneration));

        return Collections.emptyList();
    }

    private List<Integer> getMostDislikedIngredient(Data data) {
        return data.getIngredients().stream()
                .map(ing -> data.getClients().stream()
                        .map(Client::getDislikeList)
                        .map(l -> l.contains(ing))
                        .mapToInt(b -> b ? 1 : 0)
                        .sum())
                .collect(Collectors.toList());
    }

    private List<Integer> getMostLikedIngredient(Data data) {
        return data.getIngredients().stream()
                .map(ing -> data.getClients().stream()
                        .map(Client::getLikeList)
                        .map(l -> l.contains(ing))
                        .mapToInt(b -> b ? 1 : 0)
                        .sum())
                .collect(Collectors.toList());
    }

    private Population<String> createInitialPopulation(List<String> ingredients) {
        var temp = IntStream.range(0, POP_SIZE)
                .mapToObj(i -> IntStream.range(0, MAX_INGREDIENTS)
                        .mapToObj(j -> Utils.getRandomStringFromListWithNull(new ArrayList<>(ingredients)))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet()))
                .map(s -> Individual.<String>builder().individual(s).build())
                .collect(Collectors.toList());
        return Population.<String>builder().population(temp).build();
    }

    private Integer individualFitness(Data data, Individual individual) {
        return data.getClients().stream()
                .map(client -> client.getLikeList().containsAll(individual.getIndividual())
                        && Collections.disjoint(client.getDislikeList(), individual.getIndividual()))
                .mapToInt(b -> b ? 1 : 0)
                .sum();
    }

    private List<Integer> calculateFitness(Data data, Population<String> population) {
        return population.getPopulation().stream()
                .map(Individual::getIndividual)
                .map(toppings ->
                        data.getClients().stream()
                                .map(client -> client.getLikeList().containsAll(toppings)
                                        && Collections.disjoint(client.getDislikeList(), toppings))
                                .mapToInt(b -> b ? 1 : 0)
                                .sum()).collect(Collectors.toList());
    }
}
