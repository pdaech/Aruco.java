package org.texttechnologylab.project.bunta;

public class App {
    public static void main(String[] args) {

        String name;
        name = "Philipp";
        int age = 22 + 1;
        System.out.println(name + " wurde heute " + age);

        name = "Philipp";
        age = 22 + 1;
        System.out.println(name + " wurde heute " + age);
        int i = 2;
        while (i < 5) System.out.println(i++);
        int[] list = new int[10];
        list[3] = 27;
        System.out.println(list[3]);
        int[] evenNumbers = new int[]{2, 4, 6, 8, 10};
        System.out.println(evenNumbers.length);
        int[][] matrix = new int[2][3];
        matrix[1][2] = 27;
        System.out.println(matrix[1][2]);
        int[][] m = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(m[1][2]);
        int a = 5, b = 5, c = 10;
        if (a == 5) {
            System.out.println("a == 5");
        } else if (a == 10) {
            System.out.println("a == 10");
        } else {
            System.out.println("a is not 5 and not 10");
        }
        switch (a) {
            case 1:
                System.out.println("eins");
                break;

            case 2:
                System.out.println("zwei");
                break;

            default:
                System.out.println("drei");
                break;
        }
        String zahl;
        zahl = i%2 == 0 ? "gerade" : "ungrade";
    }
}


