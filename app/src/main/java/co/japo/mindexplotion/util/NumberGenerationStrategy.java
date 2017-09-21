package co.japo.mindexplotion.util;

import java.util.Random;

/**
 * Created by japodeveloper on 9/21/17.
 */

public class NumberGenerationStrategy {

    public static Integer random(Integer min, Integer max){
        if(min > max)
            throw new IllegalArgumentException("min value cannot exceed max value");

        Random randomGenerator = new Random();
        long range = (long)max - (long)min + 1;
        long fraction = (long) (range*randomGenerator.nextDouble());
        return (int) fraction+min;
    }
}
