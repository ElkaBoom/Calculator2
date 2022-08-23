package Calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static final String REGEX = "[+\\-*/]";
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
        String[] numbers = input.split(REGEX); // получаем элементы по отдельности разделенные оператором
        if (numbers.length != 2) { // проверяем, что у нас не больше двух операндов
            throw new RuntimeException("Invalid expression"); // бросаем исключение, если да
        }
        boolean isRoman = false; // заводим флаг для римских чисел, изначально - ложь

        Integer leftOperand = ARABIAN.get(numbers[0]);
        Integer rightOperand = ARABIAN.get(numbers[1]);

        if (leftOperand == null || rightOperand == null) { // если это были не арабские числа - оба будут null
            leftOperand = ROME.get(numbers[0]);
            rightOperand = ROME.get(numbers[1]);
// если попытались замиксовать числа или числа не в нужном диапазоне, что-то останется null
            isRoman = true; // так как мы попали в этот if, проставляем флаг римских чисел в - истину
        }

        if (rightOperand == null || leftOperand == null) { // опять же - если попытались замиксовать числа или числа не в нужном диапазоне, что-то останется null
            throw new RuntimeException("Wrong operands in input string");
        }

        char operator = input.charAt(input.indexOf(numbers[0]) + 1); // достаём оператор, если оператора бы небыло на строке 63 вышла бы ошибка

        int result;
        switch (operator) { // по строке с оператором применяем нужную операцию к двум операндам
            case '+' -> result = leftOperand + rightOperand; // и записываем в result
            case '-' -> result = leftOperand - rightOperand;
            case '*' -> result = leftOperand * rightOperand;
            case '/' -> result = leftOperand / rightOperand;
            default -> throw new RuntimeException();
        }
        if (isRoman) { // проверяем флаг римских чисел
            if (result < 0) { // если да и результат - это отрицательное число - бросаем исключение.
                throw new RuntimeException("Operation with arabic numbers cant have negative result");
            }
            return toRoman(result); // если нет, обращаемся к методу, который написал нам умный дядя и получаем валидное римское число строкой.
        }
        return Integer.toString(result); // если мы не попали в if выше и это было не римское число, значит это обычное число,
// просто преобразуем его к строке и возвращаем.
    }

    public static String toRoman(int number) {
        int l = ROMAN_RESULT.floorKey(number);
        if (number == l) {
            return ROMAN_RESULT.get(number);
        }
        return ROMAN_RESULT.get(l) + toRoman(number - l);
    }

}
