package Homework_4;
import java.util.Scanner;
public class Triangle {
    public static void main(String[] args) throws checkNumbersException {


        Scanner sc = new Scanner(System.in);
        System.out.print("Введите первую сторону. a = ");
        int a = sc.nextInt();
        System.out.print("Введите вторую сторону. b = ");
        int b = sc.nextInt();
        System.out.print("Введите третью сторону. c = ");
        int c = sc.nextInt();
        sc.close();

        checkNumbers(a, b, c);

        if (isTriangle(a, b, c)) {
            System.out.println("Стороны не образуют треугольник");
        } else {
            System.out.println("Площадь треугольника (main): " + areaTriangle(a, b, c));
        }
    }

    public static double areaTriangle(int a, int b, int c) {


        double p = (double) (a + b + c) / 2;

        return (isTriangle(a, b, c)) ? 0 : Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static boolean isTriangle(int a, int b, int c) {

        return (a + b < c || a + c < b || b + c < a);
    }

    public static class checkNumbersException extends Exception {
        public checkNumbersException(String message) {
            super(message);
        }
    }


    public static void checkNumbers(int a, int b, int c) throws checkNumbersException {
        if (a <= 0 || b <= 0 || c <= 0) throw new checkNumbersException("Число не может быть нулем или отрицательным!");
    }
}
