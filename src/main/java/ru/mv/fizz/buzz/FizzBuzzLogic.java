package ru.mv.fizz.buzz;

public class FizzBuzzLogic {

    public void execute(String number) {
        int parsedNumber = Integer.parseInt(number);
        if (parsedNumber % 3 == 0 && parsedNumber % 5 == 0) {
            System.out.println("FizzBuzz!");
            return;
        }
        if (parsedNumber % 3 == 0) {
            System.out.println("Fizz!");
            return;
        }
        if (parsedNumber % 5 == 0) {
            System.out.println("Buzz!");
            return;
        }
        System.out.println(parsedNumber);
    }
}
