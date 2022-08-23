package Calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static String calc(String input) {
        return null;
    }

    private static final String REGEX = "[+\\-*/]";
    private static final Map<String, Integer> rome = new HashMap<>();
    private static final Map<String, Integer> arabian = new HashMap<>();

    static {
        rome.put("I", 1);
        rome.put("II", 2);
        rome.put("III", 3);
        rome.put("IV", 4);
        rome.put("V", 5);
        rome.put("VI", 6);
        rome.put("VII", 7);
        rome.put("VIII", 8);
        rome.put("IX", 9);
        rome.put("X", 10);

        arabian.put("1", 1);
        arabian.put("2", 2);
        arabian.put("3", 3);
        arabian.put("4", 4);
        arabian.put("5", 5);
        arabian.put("6", 6);
        arabian.put("7", 7);
        arabian.put("8", 8);
        arabian.put("9", 9);
        arabian.put("10", 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            System.out.println(calculate(scanner.nextLine()));
        }
    }

    private static int calculate(String input) {
        String[] numbers = input.split(REGEX);
        char operator = input.charAt(input.indexOf(numbers[0]) + 1);
        Integer leftOperand = rome.get(numbers[0]);
        Integer rightOperand = rome.get(numbers[1]);

        if (leftOperand == null && rightOperand == null) {
            leftOperand = arabian.get(numbers[0]);
            rightOperand = arabian.get(numbers[1]);
        }
        if (rightOperand == null || leftOperand == null) {
            throw new RuntimeException("You are trying to add numbers from different systems");
        }
        if( numbers.length != 2) throw new RuntimeException("You enter more than two operands");

        int result;
        switch (operator) {
            case '+' -> result = leftOperand + rightOperand;
            case '-' -> result = leftOperand - rightOperand;
            case '*' -> result = leftOperand * rightOperand;
            case '/' -> result = leftOperand / rightOperand;
            default -> throw new RuntimeException();
        }
        return result;
    }
}
