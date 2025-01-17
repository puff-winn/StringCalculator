package calculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    int count = 0;

    Set<Integer> validIntegers = Set.of(1,2,3,4,5,6,7,8,9);

    // init method
    public int add(String num){
        count++;
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

    // logic encompassing calculations with delimeters
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

    // fetching all the delimeters present in the input string
    public Set<String> getDelimitersInsideRegex(String input) {
        Set<String> extractedStrings = new HashSet<>();
        Pattern pattern = Pattern.compile("\\[([^]]+)\\]");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            extractedStrings.add(matcher.group(1));
        }

        return extractedStrings;
    }

    // calculate sum when delimeters are present
    private int getSumWithDelimeter(String numbers, Set<String> regexes){
        int result = 0;
        int n = numbers.length();
        StringBuilder negativeStrings = new StringBuilder();
        boolean isPrevDigit = false;
        StringBuilder currNumber = new StringBuilder();
        StringBuilder currDelimiter = new StringBuilder();
        for(int i=0; i<n; i++){
            char currCharacter = numbers.charAt(i);
            if(Character.isDigit(currCharacter) || currCharacter=='-'){
                if(currDelimiter.isEmpty() || regexes.contains(currDelimiter.toString())) {
                    currNumber.append(currCharacter);
                } else {
                    throw new RuntimeException("Unknown delimeter");
                }

                isPrevDigit = true;
            } else {
                if(isPrevDigit){
                    isNegativePresent(negativeStrings, currNumber.toString());
                    result = getResult(result, currNumber.toString());
                    currDelimiter.setLength(0);
                    currNumber.setLength(0);
                    isPrevDigit = false;
                }
                currDelimiter.append(currCharacter);
            }
        }
        isNegativePresent(negativeStrings, currNumber.toString());
        result+=Integer.parseInt(currNumber.toString());

        if(!negativeStrings.isEmpty()){
            throw new RuntimeException("Negatives not allowed. Found "+ negativeStrings.substring(0,negativeStrings.length()-1));
        }
        return result;
    }

    // calculating sum in case of comma and new line seperated numbers
    private int getSumWithSeperater(String numbers, String regex) {
        String[] substrings = numbers.split(regex);
        int result = 0;
        StringBuilder negativeStrings = new StringBuilder();
        for(String substring: substrings){
            if(substring.contains("\n")){
                result+=getSumWithSeperater(substring, "\n");
            } else {
                isNegativePresent(negativeStrings, substring);
                result = getResult(result, substring);
            }

        }
        if(!negativeStrings.isEmpty()){
            throw new RuntimeException("Negatives not allowed. Found "+ negativeStrings.substring(0,negativeStrings.length()-1));
        }
        return result;
    }

    // calculating final result of summation
    private int getResult(int result, String substring) {
        int currentSubstring = Integer.parseInt(substring);
        if(currentSubstring<=1000){
            result +=currentSubstring;
        }
        return result;
    }

    // checking if negative is present int the number
    private void isNegativePresent(StringBuilder negativeStrings, String currNumber) {
        if(Integer.parseInt(currNumber.toString())<0){
            negativeStrings.append(currNumber+",");
        }
    }

    // fetching the total count when add is called in one iteration
    public int GetCalledCount(){
        return count;
    }
}
