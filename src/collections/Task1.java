package collections;

import java.util.ArrayList;
import java.util.HashSet;

public class Task1 {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(6);
        for (int i=6; i<11; i++){
            numbers.add(i);
        }

        ArrayList<String> names = new ArrayList<>();
        names.add("Max");
        names.add("Kane");
        names.add("Jarvis");
        names.add("Io");
        names.add("Mate");

        ArrayList<String> rating = new ArrayList<>();
        int iter = 0;
        for (String name: names){
            rating.add(numbers.get(iter)+" - "+name);
            iter++;
        }
        System.out.println(rating);

        // Поработаем с HashSet
        HashSet<String> hashSet = fillSet();
        // Единственный прямой способ получить элементы Set - for each
        for (String str: hashSet){
            System.out.println(str);
        }
        // Или можно просто проверить наличие эл-та в HashSet
        System.out.println("Is it contains Ella? - "+hashSet.contains("Ella"));
    }

    public static HashSet<String> fillSet(){
        HashSet<String> hashSet = new HashSet<>();

        hashSet.add("Mark");
        hashSet.add("Kina");
        hashSet.add("Joseph");
        hashSet.add("Jeremy");
        hashSet.add("Jeremy"); // Этот не добавится, Set не допускает дубликаты
        System.out.println(hashSet); // Выведет в другом порядке, тк Set неиндексируемый и хранит беспорядочно
        return hashSet;
    }
}
