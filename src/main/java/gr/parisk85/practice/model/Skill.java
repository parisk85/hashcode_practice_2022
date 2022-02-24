package gr.parisk85.practice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Skill {
    String name;
    int level;
}
