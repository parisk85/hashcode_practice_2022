package gr.parisk85.practice.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Skill {
    String name;
    int level;
}