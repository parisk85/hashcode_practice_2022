package gr.parisk85.practice.service;

import gr.parisk85.practice.model.Contributor;
import gr.parisk85.practice.model.Data;
import gr.parisk85.practice.model.Output;
import gr.parisk85.practice.model.Project;
import gr.parisk85.practice.model.Skill;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyAlgorithm implements Algorithm {
    @Override
    public Output run(Data data) {
        System.out.println(data);

        data.getProjects().stream()
                .sorted(Comparator.comparing(Project::getScore, Comparator.reverseOrder()));

        var output = Output.builder();

        Map<String, List<String>> projectsAndPersons = new HashMap<>();

        List<String> finishedProjects = new ArrayList<>();

        var projects = new ArrayList<>(data.getProjects());

        while (finishedProjects.size() < data.getNoOfProjects()) {
            Project toRemove = null;
            List<Contributor> unavailablePersons = new ArrayList<>();
            for (Project project : data.getProjects()) {
                List<String> persons = new ArrayList<>();
                var isRoleFilled = false;
                for (Skill role : project.getRoles()) {
                    for (Contributor contributor : data.getContributors()) {
                        if (isRoleFilled) {
                            break;
                        }
                        if (unavailablePersons.contains(contributor)) {
                            break;
                        }
                        for (Skill skill : contributor.getSkills()) {
                            if (skill.getName().equals(role.getName()) && skill.getLevel() >= role.getLevel() && !unavailablePersons.contains(contributor)) {
                                persons.add(contributor.getName());
                                unavailablePersons.add(contributor);
                                isRoleFilled = true;
                                contributor.getSkills().get(contributor.getSkills().indexOf(skill))
                                        .setLevel(contributor.getSkills().get(contributor.getSkills().indexOf(skill)).getLevel() + 1);
                                break;
                            }
                        }
                    }
                }
                projectsAndPersons.put(project.getName(), persons);
                if (persons.size() == project.getRoles().size()) {
                    finishedProjects.add(project.getName());
                    toRemove = project;
                }
            }
            projects.remove(toRemove);
        }

        output.projectsAndNames(projectsAndPersons);

        System.out.println(output);

        return output.build();
    }
}
