package gr.parisk85.practice.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Data {
    int potentialClients;
    List<Client> clients;
    List<String> ingredients;
}
