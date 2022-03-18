import java.util.Scanner;

public class Lab2_1 {
    public static void main(String[] args) {
        // 몇 개의 인풋을 받을지 입력
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println(num);

        // 위에서 입력한 수 만큼 인풋을 넣어줌
        String[] arr = new String[num];
        for (int i=0; i<num; i++) {
            String in = scanner.next();
            arr[i] = in;
        }

        // 인풋을 차례대로 프린트함
        for (String elt : arr) {
            System.out.print(elt + " ");
        }
        System.out.println();
    }
}
