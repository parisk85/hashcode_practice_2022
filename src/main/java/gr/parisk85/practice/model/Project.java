package gr.parisk85.practice.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Project {
    String name;
    int days;
    int score;
    int bestBefore;
    int noOfRoles;
    List<Skill> roles;
}
