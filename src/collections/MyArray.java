package collections;

import java.util.Iterator;

// ���� ������� �������� �� ���������, ����� � ��� ����������� ��������� Iterable
public class MyArray implements Iterable<String>{
    private String[] array;
    private int size = 0;

    public MyArray(){
        this.array = new String[10];
    }

    // ����������� �����������
    public MyArray(int capacity){
        if (capacity == 0){
            throw new RuntimeException("����������� �� ����� ���� 0");
        }
        this.array = new String[capacity];
    }

    // ��������� for each ��� ��������
    @Override
    public Iterator<String> iterator() {
        // � ��� ����� ����� ���� ����� ����������� ���� �����, ������� ���������� ��������
        return new IteratorForMyArray();
    }
    // � ����������� �� ������� ��������� ����� ���������, � ������� ��������� hasNext() � next().
    // ���� ����� ����������� ��������� ��������� Iterator � �����-������ ����������.
    private class IteratorForMyArray implements Iterator<String>{
        private int count = -1;

        @Override
        public boolean hasNext() {
            if (count+1 == size){
                return false;
            }
            return true;
        }

        @Override
        public String next() {
            count++;
            return array[count];
        }
    }

    public void add(String element){
        array[size] = element;
        size++;
        // ����� ���������� ������ ������ � ������������
        if (size == array.length){
            String[] temp = new String[array.length*2];
            for (int i=0; i<size; i++){
                temp[i] = array[i];
            }
            array = temp;
        }
    }

    public void remove(int index){
        // ������ �� size-1, ����� ��������� ������ �� ��������, ����� size=capacity � ����� i+1 �� �������� �� �������
        // �������, ����� ��� ���������� ���������� ����� ������ ����� �������� ����� ��������.
        for (int i=index; i<size-1;i++){
            array[i] = array[i+1];
        }
        array[size-1] = null;
        size--;
    }

    public void remove(String element){
        for (int i=0; i<size;i++){
            if (array[i].equals(element)){ // �� �������� ������������ equals(), ������ � String Pull
                remove(i);
                break; // ���� ������ �� �������������
            }
        }
    }

    public String get(int index){
        return array[index];
    }

    // ���-�� ���������
    public int getSize(){
        return size;
    }

}

class Testing1{
    public static void main(String[] args) {
        MyArray array1 = new MyArray(2);
        array1.add("Max");
        array1.add("Mia");
        array1.add("Filip");
        System.out.println(array1.get(2)); // Filip
        System.out.println(array1.getSize()); // 3
        array1.remove("Max");
        System.out.println(array1.getSize()); // 2
        array1.add("Musa");
        array1.add("Pavel");
        // ��������� �������� �� ��� ��������
        for (String element: array1){
            System.out.println(element); // ��������!
        }
        // �������� �� �� ��������?
        for (String element: array1){
            System.out.println(element); // ��������!
        }

        // ���� ���������� ��� ��� � ������ ������ ����������� �������
        String[] testEmptyIndex = new String[3];
        testEmptyIndex[0] = "Nothing";
        testEmptyIndex[1] = "Nothing2";
        System.out.println(testEmptyIndex[2]); // null

        // � ���� �� �� ���� ���������, �� ����, remove �������� �� ������������� ���
        String[] afterRemove = new String[testEmptyIndex.length-1];
        testEmptyIndex[0] = null;
        for (int i=0, j=0; i < afterRemove.length-1; i++){
            if (testEmptyIndex[i] == null){
                j++;
            }
            afterRemove[i] = testEmptyIndex[j];
            j++;
        }
    }
}