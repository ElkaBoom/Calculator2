import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String REGEX = "[+\\-*/]";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    private static final Map<String, Integer> ROME = new HashMap<>();
    private static final Map<String, Integer> ARABIAN = new HashMap<>();
    private final static TreeMap<Integer, String> ROMAN_RESULT = new TreeMap<>();


    static {
        ROME.put("I", 1);
        ROME.put("II", 2);
        ROME.put("III", 3);
        ROME.put("IV", 4);
        ROME.put("V", 5);
        ROME.put("VI", 6);
        ROME.put("VII", 7);
        ROME.put("VIII", 8);
        ROME.put("IX", 9);
        ROME.put("X", 10);

        ARABIAN.put("1", 1);
        ARABIAN.put("2", 2);
        ARABIAN.put("3", 3);
        ARABIAN.put("4", 4);
        ARABIAN.put("5", 5);
        ARABIAN.put("6", 6);
        ARABIAN.put("7", 7);
        ARABIAN.put("8", 8);
        ARABIAN.put("9", 9);
        ARABIAN.put("10", 10);

        ROMAN_RESULT.put(1000, "M");
        ROMAN_RESULT.put(900, "CM");
        ROMAN_RESULT.put(500, "D");
        ROMAN_RESULT.put(400, "CD");
        ROMAN_RESULT.put(100, "C");
        ROMAN_RESULT.put(90, "XC");
        ROMAN_RESULT.put(50, "L");
        ROMAN_RESULT.put(40, "XL");
        ROMAN_RESULT.put(10, "X");
        ROMAN_RESULT.put(9, "IX");
        ROMAN_RESULT.put(5, "V");
        ROMAN_RESULT.put(4, "IV");
        ROMAN_RESULT.put(1, "I");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            System.out.println(calc(scanner.nextLine()));
        }
    }

    public static String calc(String input) {
        String[] numbers = input.split(REGEX);
        if (numbers.length != 2) {
            throw new RuntimeException("Invalid expression");
        }
        boolean isRoman = false;

        Integer leftOperand = ARABIAN.get(numbers[0]);
        Integer rightOperand = ARABIAN.get(numbers[1]);

        if (leftOperand == null || rightOperand == null) {
            leftOperand = ROME.get(numbers[0]);
            rightOperand = ROME.get(numbers[1]);
            isRoman = true;
        }

        if (rightOperand == null || leftOperand == null) {
            throw new RuntimeException("Wrong operands in input string");
        }
        Matcher matcher = PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new RuntimeException("incorrect arithmetic action");
        }
        String operator = matcher.group();


        int result;
        switch (operator) {
            case "+" -> result = leftOperand + rightOperand;
            case "-" -> result = leftOperand - rightOperand;
            case "*" -> result = leftOperand * rightOperand;
            case "/" -> result = leftOperand / rightOperand;
            default -> throw new RuntimeException();

        }

        if (isRoman) {
            if (result <= 0) {
                throw new RuntimeException("Operation with Roman numbers cannot have a negative result");
            }
            return toRoman(result);
        }
        return Integer.toString(result);
    }

    public static String toRoman(int number) {
        int l = ROMAN_RESULT.floorKey(number);
        if (number == l) {
            return ROMAN_RESULT.get(number);
        }
        return ROMAN_RESULT.get(l) + toRoman(number - l);
    }

}

