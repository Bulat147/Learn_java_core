package multithread;

import java.util.Random;

public class Threading {
    private static final Random randomizer =  new Random();
    private static final int ORIGIN = 100;
    private static final int BOUND = 1_000_000_000;
    // ����� isGuess ����� ���� � ����� ������ �������� � ����� ������ � ������(����� ������), ����� ����� �� ��� ���
    // ������-���� ������, � ������ ��� ������ ���� ������ (������ ������ == ������ ������). �� ���� isGuess ������
    // ����������� ����� ������.
    public static boolean isGuess = false;
    public static boolean isTyred = false;
    public static final int seconds = 30;

    public static void main(String[] args) {

        // �� main ���������� ������ ���������� � ��� �������, �� guessedNum - ������ ������ ������ ���� �������,
        // ����� ������ �������� ��������, ���� guessedNum ����� ������ �� ������� main, � ������ ������ ������ ����� ������
        int guessedNum = randomizer.nextInt(ORIGIN, BOUND);
        System.out.printf("����� 'main' ������� ����� �� %s �� %s, � ������ �������� � ������ 'thread2', " +
                "���� ����� 'thread3' ����� ������� �������... \n", ORIGIN, BOUND);

        // ������� �����, � ������� ���� ������ ���������� ������, ������������ ��������� Runnable (1 ������)
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // ��������, ����� ��� �������� ����������� ����� do/while
                while (!isTyred) { // ���� ����� thread3 ����� ������� �������
                    int currentChoice = randomizer.nextInt(ORIGIN, BOUND);
                    if (currentChoice == guessedNum) { // guessedNum - ������ ������ ��� thread2
                        isGuess = true; // ����� ������ (��� thread2 � thread3)
                        System.out.printf("��, � ������ %s! \n", currentChoice);
                        return;
                    }
                    //run(); // ��� ��� ����� �� ������ � ��������� - ������ � StackOverflow
                }
                System.out.println("'thread2' �������: '����, � �� �����...'");
            }
        });

        // ������� �����, � ������� ���� ������ ��������� ������ 'Timer', ������������ ��������� Runnable (2 ������)
        Thread thread3 = new Thread(new Timer());

        // ��������� ������
        System.out.println("'thread3' �������� ������� �������...");
        thread3.start();
        System.out.println("'thread2' �������� ��������� �����...");
        thread2.start();
    }
}

class Timer implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i<Threading.seconds; i++){
            System.out.println(i);
            if (Threading.isGuess){
                System.out.printf("'thread3' �������: '���������, �� ������ �� %s �������' \n", i);
                return;
            }
            try {
                // ������ ��, ��� ������ sleep ����� ����������� ������������ ��������� ����������
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("���-�� �� �� ������ ���� :(");
            }
        }
        System.out.println("'thread3' �������: '���, � ����� ������� �������'");
        System.out.println("����� ����!");
        Threading.isTyred = true; // ����� ���� ����� ������ ����� thread3 ���� ����� ������ thread2, ��� �����
    }
}