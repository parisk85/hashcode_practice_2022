package gr.parisk85.practice.model;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class Individual<T> {
    Set<T> individual;
}
