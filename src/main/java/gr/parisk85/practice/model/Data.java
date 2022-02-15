package gr.parisk85.practice.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Value
@Builder
public class Data {
    int potentialClients;
    List<Client> clients;
    Set<String> ingredients;
}
