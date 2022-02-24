package gr.parisk85.practice.helper;

import gr.parisk85.practice.model.Contributor;
import gr.parisk85.practice.model.Data;
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
        //TODO: get input to create data model object

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

        var newData = data.toBuilder().contributors(contributors).build();

        System.out.println(newData);

        return newData;
    }
}
