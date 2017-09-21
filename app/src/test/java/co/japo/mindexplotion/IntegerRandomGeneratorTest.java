package co.japo.mindexplotion;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import co.japo.mindexplotion.util.NumberGenerationStrategy;

/**
 * Created by japodeveloper on 9/21/17.
 */

public class IntegerRandomGeneratorTest {

    @Test
    public void integerGenerationTest() throws Exception{
        int min = 1, max = 4;
        assertThat(NumberGenerationStrategy.random(min,max),allOf(greaterThanOrEqualTo(min),lessThanOrEqualTo(max)));
        assertThat(NumberGenerationStrategy.random(min,max),allOf(greaterThanOrEqualTo(min),lessThanOrEqualTo(max)));
        assertThat(NumberGenerationStrategy.random(min,max),allOf(greaterThanOrEqualTo(min),lessThanOrEqualTo(max)));
        assertThat(NumberGenerationStrategy.random(min,max),allOf(greaterThanOrEqualTo(min),lessThanOrEqualTo(max)));
        assertThat(NumberGenerationStrategy.random(min,max),allOf(greaterThanOrEqualTo(min),lessThanOrEqualTo(max)));
        assertThat(NumberGenerationStrategy.random(min,max),allOf(greaterThanOrEqualTo(min),lessThanOrEqualTo(max)));
        assertThat(NumberGenerationStrategy.random(min,max),allOf(greaterThanOrEqualTo(min),lessThanOrEqualTo(max)));
    }
}
