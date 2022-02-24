package gr.parisk85.practice.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class Data {
    int noOfContributors;
    int noOfProjects;
    List<Contributor> contributors;
    List<Project> projects;
}
