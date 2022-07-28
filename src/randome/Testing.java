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


class Dice{ // �����
    /** ��� ������������ �������:
     *      1) ������� ����� - roll();
     *      2) ���������� ��� ��� ������ - toString()
     *         ��� �������� ��� ��� ������ - roll() ���������� int;
     * */

    public static void main(String[] args) {
        Dice littleRoll = new Dice();
        int value = littleRoll.roll(); // ������� �����
        System.out.println(value); // ���������� ��� ������ 1 ��������
        // ������ 100 ������� �������
        for (int i=0; i<100; i++){
            littleRoll.roll();
            System.out.println(littleRoll); // ���������� ��� ������ 2 ��������
        }
    }

    private static final int BOUND = 6; // ������� �������� ��� ������
    private static final int ORIGIN = 1; // ������ ��������
    private static final Random randomizer = new Random(); // �������� ����������� �������

    private int currentValue;

    public int roll(){ // ������
        currentValue = randomizer.nextInt(ORIGIN, BOUND+1);
        return currentValue; // ����� user ������ ������������ ��, ��� ��� ������
    }

    @Override
    public String toString() {
        if (currentValue==0){
            System.out.println("�� ������ ����� �� ���...");
            roll();
        }
        // String.format() - ���������� ��������������� ������
        return String.format("��� ������ %s", currentValue);
    }
}