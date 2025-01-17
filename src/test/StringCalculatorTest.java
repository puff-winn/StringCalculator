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
        assertThat(addResultOf("//;\n1;3"),CoreMatchers.is(4));
        assertThat(addResultOf("//[**][$$$]\n1**3$$$6"),CoreMatchers.is(10));
        assertThat(addResultOf("//[***]\n1***2***3"),CoreMatchers.is(6));
        assertThat(addResultOf("//;\n1;3;1001"),CoreMatchers.is(4));
//        assertThat(addResultOf("//[**][$$$]\n1**-3$$$-6"),CoreMatchers.is(10));
//        assertThat(addResultOf("//;\n1;-3;-1"),CoreMatchers.is(4));


        System.out.println("Count of calls to calculator : "+stringCalculator.getCount());
    }

    private int addResultOf(String numbers){
        return stringCalculator.add(numbers);
    }
}
