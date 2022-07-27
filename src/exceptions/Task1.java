package exceptions;

class Task1 {

    public static void main(String[] args) {
        // ������: ������� � ���������� ArrayIndexOutOfBoundsException
        try{
            int[] array = new int[3];
            array[0] = 45;
            array[1] = 56;
            System.out.println(array[2]);
            int error = array[3];
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("��� �� � ������� "+e.getClass());
        }
    }

    public static void doException() {
        // ������ ����������
        try{
           int someNum = Integer.parseInt("ffg"); // ����� ������ ��� ����������
           int a = 0;
           int b = 7/a;
        }catch(Exception e){  // ��� ����� ������, ���� ����� ��� ���������� ������������ ���������
            System.out.println("������� ������ " + e.getClass()); // ��� ����� ������ ��� ����������
        }
    }
}
