public class FibonacciNumbers {
    public static void printFibonacciNumbers(int n) {
        int lastNum = 0;
        int prevNum = 0;
        int sum = 0;
        for(int i=0; i<n; i++) {
            System.out.print(lastNum + " ");
            if (i == 0) {
                lastNum = 1;
            } else {
                int tmp = lastNum;
                lastNum += prevNum;
                prevNum = tmp;
            }
            sum += lastNum;
        }
        sum -= lastNum;
        System.out.println();
        System.out.print("sum : " + sumToString(sum));
    }

    static String sumToString(int sum) {
        if ((sum >= 100000) && ((sum%100000)/10000==0)) {
            return "0" + Integer.toString(sum % 100000);
        }
        return Integer.toString(sum%100000);
    }
}
