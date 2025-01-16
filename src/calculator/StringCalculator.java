package calculator;

public class StringCalculator {

    public int add(String num){
        String numbers = num.trim();
        if(numbers.length()==0){
            return 0;
        }
        if(numbers.contains(",")){
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
                result+=Integer.parseInt(substring);
            }

        }
        return result;
    }
}
