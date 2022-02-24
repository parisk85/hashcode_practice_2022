package gr.parisk85.practice.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Contributor {
    String name;
    int noOfSkills;
    List<Skill> skills;
}
