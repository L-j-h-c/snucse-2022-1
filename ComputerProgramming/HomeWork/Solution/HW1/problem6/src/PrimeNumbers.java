public class PrimeNumbers {
    public static void printPrimeNumbers(int m, int n) {
        for(int i=m; i<=n; i++) {
            int tmp = 2000;
            if(i==2) {
                System.out.print(i + " ");
            }
            for(int j=2; j<i; j++) {
                if(j==i-1) {
                    tmp = i;
                }
                if (i%j==0) {
                    break;
                }
            }
            if(tmp!=2000) {
                System.out.print(i + " ");
            }
        }
    }
}
