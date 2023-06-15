package ru.mv.fizz.buzz;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Runner {

    private static final String WELCOME_MESSAGE = "Добро пожаловать в игру - Fizz Buzz!\n" +
                                                  "Отправь любое число и получи ответ!\n" +
                                                  "Если тебе нужна помощь - отправь \"help\"! Удачи!";

    private static final String NEED_REPEAT = "Это не число!";
    private static final Pattern DIGIT_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_END = "end";

    public static void main(String[] args) {
        FizzBuzzLogic fizzBuzzLogic = new FizzBuzzLogic();
        System.out.println(WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String currentAnswer = scanner.nextLine();
            if(COMMAND_END.equals(currentAnswer)) {
                System.out.println("Прощай!");
                break;
            }
            if (COMMAND_HELP.equals(currentAnswer)) {
                showHelp();
                continue;
            }
            if (isNotNumber(currentAnswer)) {
                System.out.println(NEED_REPEAT);
                continue;
            }
            System.out.print("Число: ");
            fizzBuzzLogic.execute(currentAnswer);
        }
    }

    private static void showHelp() {
        System.out.println("Вместо чисел, кратных трем, программа должна выводить слово «Fizz», а вместо чисел, кратных пяти — слово «Buzz».\n" +
                           "Если число кратно и 3, и 5, то программа должна выводить слово «FizzBuzz»Если вы хотите выйти из программы отправьте \"end\"\n" +
                           "через командную строку.");
    }

    private static boolean isNotNumber(String currentLine) {
        if (currentLine == null) {
            return true;
        }
        return !DIGIT_PATTERN.matcher(currentLine).matches();
    }
}