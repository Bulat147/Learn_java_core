package randome;

import java.util.Random;

public class Testing {

    public static void main(String[] args) {
        Random giveRandomValue = new Random();
        int someInt;
        for(int i=0; i<100;i++){
            someInt = giveRandomValue.nextInt(5, 11);
            System.out.print(String.format("%s ", someInt));
        }
    }
}


class Dice{ // Кости
    /** Как пользоваться костями:
     *      1) Бросьте кости - roll();
     *      2) Посмотрите что вам выпало - toString()
     *         или возьмите что вам выпало - roll() возвращает int;
     * */

    public static void main(String[] args) {
        Dice littleRoll = new Dice();
        int value = littleRoll.roll(); // Бросили кубик
        System.out.println(value); // Посмотрели что выпало 1 способом
        // Делаем 100 бросков кубиком
        for (int i=0; i<100; i++){
            littleRoll.roll();
            System.out.println(littleRoll); // Посмотрели что выпало 2 способом
        }
    }

    private static final int BOUND = 6; // верхнее значение оси кубика
    private static final int ORIGIN = 1; // нижнее значение
    private static final Random randomizer = new Random(); // источник функционала рандома

    private int currentValue;

    public int roll(){ // Бросок
        currentValue = randomizer.nextInt(ORIGIN, BOUND+1);
        return currentValue; // Дадим user самому обрабатывать то, что ему выпало
    }

    @Override
    public String toString() {
        if (currentValue==0){
            System.out.println("Мы бросим кубик за вас...");
            roll();
        }
        // String.format() - возвращает форматированную строку
        return String.format("Вам выпало %s", currentValue);
    }
}