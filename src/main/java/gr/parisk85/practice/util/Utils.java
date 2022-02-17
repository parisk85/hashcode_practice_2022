package gr.parisk85.practice.util;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;

@UtilityClass
public class Utils {

    public static String getRandomStringFromListWithNull(List<String> strings) {
        strings.add(null);
        Random random = new Random();
        return strings.get(random.nextInt(strings.size()));
    }
}
