package gr.parisk85.practice;

import gr.parisk85.practice.model.Client;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class App {

    private static final String A = "a_an_example.in.txt";
    private static final String B = "b_basic";
    private static final String C = "c_coarse.in.txt";
    private static final String D = "d_difficult.in.txt";
    private static final String E = "e_elaborate.in.txt";

    private final InputReader reader;

    public static void main(String[] args) {
        var app = new App(new InputReader());
        var data = app.reader.read(A);
        System.out.println(data.getIngredients());

        var liked = data.getIngredients().stream()
                .map(ing -> data.getClients().stream()
                        .map(Client::getLikeList)
                        .map(l -> l.contains(ing))
                        .mapToInt(b -> b ? 1 : 0)
                        .sum())
                .collect(Collectors.toList());

        var disliked = data.getIngredients().stream()
                .map(ing -> data.getClients().stream()
                        .map(Client::getDislikeList)
                        .map(l -> l.contains(ing))
                        .mapToInt(b -> b ? 1 : 0)
                        .sum())
                .collect(Collectors.toList());

        System.out.printf("Potential Clients %d\n", data.getPotentialClients());
        System.out.println(liked);
        System.out.println(disliked);

    }
}
