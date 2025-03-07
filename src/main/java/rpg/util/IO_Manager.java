package rpg.util;

public class IO_Manager {
    public static void print(String message) {
        System.out.print(message);
    }
    public static void print(String message, boolean newLine) {
        if (newLine) {
            System.out.println(message);
        } else {
            System.out.print(message);
        }
    }

}
