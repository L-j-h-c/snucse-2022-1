import java.util.Scanner;

public class StudentIDChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String id = scanner.next();

        if (id.length() != 10) {
            System.out.println("The input length should be 10.");
            return;
        }
        else if (id.charAt(4) != '-') {
            System.out.println("Fifth character should be '-'.");
            return;
        }
        else {
            for (int i=0; i < id.length(); i++) {
                if (i==4)
                    continue;
                if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                    System.out.println("Contains an invalid digit.");
                    return;
                }
            }
        }

        // valid id
        System.out.println(id + " is a valid student ID.");
    }
}

