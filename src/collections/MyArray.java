package collections;

import java.util.Iterator;

// Чтоб сделать итерацию по коллекции, нужно в ней реализовать интерфейс Iterable
public class MyArray implements Iterable<String>{
    private String[] array;
    private int size = 0;

    public MyArray(){
        this.array = new String[10];
    }

    // Перегружаем конструктор
    public MyArray(int capacity){
        if (capacity == 0){
            throw new RuntimeException("Вместимость не может быть 0");
        }
        this.array = new String[capacity];
    }

    // Реализуем for each или итерацию
    @Override
    public Iterator<String> iterator() {
        // А для этого всего лишь нужно реализовать этот метод, который возвращает итератор
        return new IteratorForMyArray();
    }
    // И обязательно мы создаем вложенный класс итератора, в котором реализуем hasNext() и next().
    // Этот класс обязательно реализует интерфейс Iterator с каким-нибудь дженериком.
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
        // После добавления размер совпал с вместимостью
        if (size == array.length){
            String[] temp = new String[array.length*2];
            for (int i=0; i<size; i++){
                temp[i] = array[i];
            }
            array = temp;
        }
    }

    public void remove(int index){
        // делаем до size-1, чтобы исключить ошибку на ситуации, когда size=capacity и делая i+1 мы вылетаем за границы
        // массива, вдруг при дальнейших изменениях этого класса такая ситуация будет возможна.
        for (int i=index; i<size-1;i++){
            array[i] = array[i+1];
        }
        array[size-1] = null;
        size--;
    }

    public void remove(String element){
        for (int i=0; i<size;i++){
            if (array[i].equals(element)){ // Не забываем использовать equals(), помним о String Pull
                remove(i);
                break; // чтоб дальше не итерироваться
            }
        }
    }

    public String get(int index){
        return array[index];
    }

    // кол-во элементов
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
        // Проверяем работает ли наш итератор
        for (String element: array1){
            System.out.println(element); // Работает!
        }
        // Работает ли он повторно?
        for (String element: array1){
            System.out.println(element); // Работает!
        }

        // Хочу посмотреть что там в пустой ячейке встроенного массива
        String[] testEmptyIndex = new String[3];
        testEmptyIndex[0] = "Nothing";
        testEmptyIndex[1] = "Nothing2";
        System.out.println(testEmptyIndex[2]); // null

        // А если бы не было коллекций, то напр, remove пришлось бы реализовывать так
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