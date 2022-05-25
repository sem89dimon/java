public class HomeWork2 {
    public static void main (String[] args){
        System.out.println(First(10, 9));
        Second(15);
        System.out.println(Third(5));
        Fourth("Hello", 5);
        System.out.println(LeapYear(2020));
    }
    public static boolean First (int a, int b) {
        int c = a + b;
        return c >= 10 && c <= 20; // тут я воспользовался всплывающей подсказкой идеа, что то тут поменялось,
        // но все работает. стало даже красивее)
    }
    //    if (c >= 10 && c <= 20) return true;
    //        else return false;

    public static void Second (int x) {
        if (x >= 0) {
            System.out.println("Положительное");
        }
        else System.out.println("Отрицательное");

    }

    public static boolean Third (int y) {
        return y < 0;
    }

    public static void Fourth (String str, int n) {
        for (int i = 1; i <= n; i++ ){
            System.out.println(str);
        }

    }

    public static boolean LeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;


    }
}
