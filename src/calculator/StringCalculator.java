package calculator;

import java.util.Set;

public class StringCalculator {

    Set<Integer> validIntegers = Set.of(1,2,3,4,5,6,7,8,9);

    public int add(String num){
        String numbers = num.trim();
        if(numbers.length()==0){
            return 0;
        }
        if(!Character.isDigit(numbers.charAt(0))){
            String[] splitSubstrings = numbers.substring(2).split("\n");
            String delimiter = splitSubstrings[0];
            String number = splitSubstrings[1];
            return getSumWithSeperater(number, delimiter);
        } else if(numbers.contains(",")){
            return getSumWithSeperater(numbers, ",");
        } else {
            return Integer.parseInt(numbers);
        }
    }

    private int getSumWithSeperater(String numbers, String regex) {
        String[] substrings = numbers.split(regex);
        int result = 0;
        for(String substring: substrings){
            if(substring.contains("\n")){
                result+=getSumWithSeperater(substring, "\n");
            } else {
                int currentSubstring = Integer.parseInt(substring);
                if(currentSubstring>1000){
                    // do nothing
                } else if(currentSubstring>=0)
                    result+=currentSubstring;
                else
                    throw new RuntimeException("Negatives not allowed. Found "+currentSubstring);
            }

        }
        return result;
    }
}
