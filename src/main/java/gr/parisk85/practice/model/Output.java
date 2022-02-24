package gr.parisk85.practice.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
@Builder
public class Output {
    Map<String, List<String>> projectsAndNames;
}
