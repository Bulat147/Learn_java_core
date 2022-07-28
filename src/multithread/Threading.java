package multithread;

import java.util.Random;

public class Threading {
    private static final Random randomizer =  new Random();
    private static final int ORIGIN = 100;
    private static final int BOUND = 1_000_000_000;
    // Чтобы isGuess можно было в любой момент получить в любом потоке и менять(общий ресурс), нужно чтобы он был вне
    // какого-либо потока, а значит вне какого либо метода (запуск метода == запуск потока). То есть isGuess должен
    // объявляться полем потока.
    public static boolean isGuess = false;
    public static boolean isTyred = false;
    public static final int seconds = 30;

    public static void main(String[] args) {

        // Тк main завершится раньше запущенных в нем потоков, то guessedNum - нельзя менять внутри этих потоков,
        // можно только единожды получить, ведь guessedNum умрет вместе со смертью main, а значит менять просто будет нечего
        int guessedNum = randomizer.nextInt(ORIGIN, BOUND);
        System.out.printf("Поток 'main' загадал число от %s до %s, и просит отгадать у потока 'thread2', " +
                "пока поток 'thread3' будет считать секунды... \n", ORIGIN, BOUND);

        // создаем поток, в который суем объект АНОНИМНОГО класса, реализующего интерфейс Runnable (1 способ)
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // наверное, можно это красивее реализовать через do/while
                while (!isTyred) { // если поток thread3 устал считать секунды
                    int currentChoice = randomizer.nextInt(ORIGIN, BOUND);
                    if (currentChoice == guessedNum) { // guessedNum - ресурс только для thread2
                        isGuess = true; // общий ресурс (для thread2 и thread3)
                        System.out.printf("Да, я угадал %s! \n", currentChoice);
                        return;
                    }
                    //run(); // Вот так лучше не делать в угадайках - уходим в StackOverflow
                }
                System.out.println("'thread2' говорит: 'Блин, я не успел...'");
            }
        });

        // создаем поток, в который суем объект РЕАЛЬНОГО класса 'Timer', реализующего интерфейс Runnable (2 способ)
        Thread thread3 = new Thread(new Timer());

        // Запускаем потоки
        System.out.println("'thread3' начинает считать секунды...");
        thread3.start();
        System.out.println("'thread2' начинает угадывать числа...");
        thread2.start();
    }
}

class Timer implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i<Threading.seconds; i++){
            System.out.println(i);
            if (Threading.isGuess){
                System.out.printf("'thread3' говорит: 'Красавчик, ты угадал на %s секунде' \n", i);
                return;
            }
            try {
                // Видите ли, при вызове sleep нужно обязательно обрабатывать возможное исключение
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Что-то не то сейчас было :(");
            }
        }
        System.out.println("'thread3' говорит: 'Все, я устал считать секунды'");
        System.out.println("Конец игре!");
        Threading.isTyred = true; // через этот общий ресурс поток thread3 дает знать потоку thread2, что устал
    }
}