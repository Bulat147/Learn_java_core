package exceptions;

class Task1 {

    public static void main(String[] args) {
        // Задача: создать и обработать ArrayIndexOutOfBoundsException
        try{
            int[] array = new int[3];
            array[0] = 45;
            array[1] = 56;
            System.out.println(array[2]);
            int error = array[3];
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Вот мы и поймали "+e.getClass());
        }
    }

    public static void doException() {
        // Пример исключения
        try{
           int someNum = Integer.parseInt("ffg"); // Будем ловить это исключение
           int a = 0;
           int b = 7/a;
        }catch(Exception e){  // так можно писать, если хотим все исключения обрабатывать одинаково
            System.out.println("Поймана ошибка " + e.getClass()); // Так можно узнать тип исключения
        }
    }
}
