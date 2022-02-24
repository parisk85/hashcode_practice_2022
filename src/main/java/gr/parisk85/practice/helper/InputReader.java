package gr.parisk85.practice.helper;

import gr.parisk85.practice.model.Contributor;
import gr.parisk85.practice.model.Data;
import gr.parisk85.practice.model.Project;
import gr.parisk85.practice.model.Skill;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InputReader {

    public Data read(String filename) {
        var input = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filename)))).lines().collect(Collectors.toList());

        var dataBuilder = Data.builder();

        var firstLine = input.stream().findFirst()
                .map(s -> s.split(" "))
                .get();

        dataBuilder.noOfContributors(Integer.parseInt(firstLine[0]))
                .noOfProjects(Integer.parseInt(firstLine[1]));

        var data = dataBuilder.build();

        int i = 0;
        int line = 1;

        List<Contributor> contributors = new ArrayList<>();
        while (i < data.getNoOfContributors()) {
            var fileLine = input.get(line);
            var s = fileLine.split(" ");
            var contributorBuilder = Contributor.builder();

            contributorBuilder.name(s[0])
                    .noOfSkills(Integer.parseInt(s[1]));

            List<Skill> skillList = new ArrayList<>();

            for (int skill = line + 1; skill <= line + Integer.parseInt(s[1]); skill++) {
                var t = input.get(skill).split(" ");
                var sk = Skill.builder().name(t[0]).level(Integer.parseInt(t[1])).build();
                skillList.add(sk);
            }

            contributorBuilder.skills(skillList);
            contributors.add(contributorBuilder.build());

            line = line + Integer.parseInt(s[1]) + 1;
            i++;
        }

        int j = 0;
        List<Project> projectList = new ArrayList<>();
        while (j < data.getNoOfProjects()) {
            var fileLine = input.get(line);
            var split = fileLine.split(" ");
            var projectBuilder = Project.builder()
                    .name(split[0])
                    .days(Integer.parseInt(split[1]))
                    .score(Integer.parseInt(split[2]))
                    .bestBefore(Integer.parseInt(split[3]))
                    .noOfRoles(Integer.parseInt(split[4]));

            List<Skill> skillList = new ArrayList<>();

            for (int skill = line + 1; skill <= line + Integer.parseInt(split[4]); skill++) {
                var t = input.get(skill).split(" ");
                var sk = Skill.builder().name(t[0]).level(Integer.parseInt(t[1])).build();
                skillList.add(sk);
            }

            projectBuilder.roles(skillList);
            projectList.add(projectBuilder.build());

            line = line + Integer.parseInt(split[4]) + 1;
            j++;
        }

        var newData = data.toBuilder().contributors(contributors).projects(projectList).build();

        System.out.println(newData);

        return newData;
    }
}
