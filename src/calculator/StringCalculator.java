package calculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    Set<Integer> validIntegers = Set.of(1,2,3,4,5,6,7,8,9);

    public int add(String num){
        String numbers = num.trim();
        if(numbers.length()==0){
            return 0;
        }
        if(!Character.isDigit(numbers.charAt(0))){
            return getCalculationWithDelimeter(numbers);
        } else if(numbers.contains(",")){
            return getSumWithSeperater(numbers, ",");
        } else {
            return Integer.parseInt(numbers);
        }
    }

    private int getCalculationWithDelimeter(String numbers) {
        String delimitedString = numbers.substring(2);
        String[] splitSubstrings = delimitedString.split("\n");
        String delimiterString = splitSubstrings[0];
        String number = splitSubstrings[1];
        Set<String> delimiters = getDelimitersInsideRegex(delimiterString);
        if(delimiters.size()>0) {
            return getSumWithDelimeter(number, delimiters);
        }
        return getSumWithSeperater(number, delimiterString);
    }

    public Set<String> getDelimitersInsideRegex(String input) {
        Set<String> extractedStrings = new HashSet<>();
        Pattern pattern = Pattern.compile("\\[([^]]+)\\]");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            extractedStrings.add(matcher.group(1));
        }

        return extractedStrings;
    }

    private int getSumWithDelimeter(String numbers, Set<String> regexes){
        int result = 0;
        int n = numbers.length();
        boolean isPrevDigit = false;
        StringBuilder currNumber = new StringBuilder();
        StringBuilder currDelimiter = new StringBuilder();
        for(int i=0; i<n; i++){
            char currCharacter = numbers.charAt(i);
            if(Character.isDigit(currCharacter)){
                if(currDelimiter.isEmpty() || regexes.contains(currDelimiter.toString())) {
                    currNumber.append(currCharacter);
                } else {
                    throw new RuntimeException("Unknown delimeter");
                }

                isPrevDigit = true;
            } else {
                if(isPrevDigit){
                    result+=Integer.parseInt(currNumber.toString());
                    currDelimiter.setLength(0);
                    currNumber.setLength(0);
                    isPrevDigit = false;
                }
                currDelimiter.append(currCharacter);
            }
        }
        result+=Integer.parseInt(currNumber.toString());

        return result;
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
