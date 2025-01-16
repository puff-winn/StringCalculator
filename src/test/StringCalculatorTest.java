package test;

import calculator.StringCalculator;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class StringCalculatorTest {

    StringCalculator stringCalculator;

    @Test
    public void addResult(){
        stringCalculator = new StringCalculator();
        assertThat(addResultOf("0"),CoreMatchers.is(0));
        assertThat(addResultOf("1"),CoreMatchers.is(1));
        assertThat(addResultOf("1,2"),CoreMatchers.is(3));
        assertThat(addResultOf("1\n2,3"),CoreMatchers.is(6));

    }

    private int addResultOf(String numbers){
        return stringCalculator.add(numbers);
    }
}
