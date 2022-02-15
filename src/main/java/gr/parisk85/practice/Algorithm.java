package gr.parisk85.practice;

import gr.parisk85.practice.model.Client;
import gr.parisk85.practice.model.Data;

import java.util.List;
import java.util.stream.Collectors;

public class Algorithm {

    public List<String> run(Data data) {
        var mostLiked = data.getIngredients().stream()
                .map(ing -> data.getClients().stream()
                        .map(Client::getLikeList)
                        .map(l -> l.contains(ing))
                        .mapToInt(b -> b ? 1 : 0)
                        .sum())
                .collect(Collectors.toList());

        var mostDisliked = data.getIngredients().stream()
                .map(ing -> data.getClients().stream()
                        .map(Client::getDislikeList)
                        .map(l -> l.contains(ing))
                        .mapToInt(b -> b ? 1 : 0)
                        .sum())
                .collect(Collectors.toList());

        System.out.println(data.getIngredients());
        System.out.printf("Potential Clients %d\n", data.getPotentialClients());
        System.out.println(mostLiked);
        System.out.println(mostDisliked);

        return List.of("tomatoes", "pepper", "basil");
        //return Collections.emptyList();
    }
}
