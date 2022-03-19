public class DrawingFigure {
    public static void drawFigure(int n) {
        for(int i=1; i<=2*n-1; i++) {
            for(int j=i+1; j<=n; j++) {
                System.out.print("  ");
            }
            for(int j=n; j<i; j++) {
                System.out.print("  ");
            }

            if(i<=n) {
                for (int j = 1; j <= 2 * i - 1; j++) {
                    if (j <= i) {
                        System.out.print(j%10);
                    } else {
                        System.out.print((2*i-j)%10);
                    }
                    if(j!=2*i-1) System.out.print(" ");
                }
            } else {
                for (int j = 1; j <= 2*(2*n-i)-1; j++) {
                    if (j <= 2*n-i) {
                        System.out.print(j%10);
                    } else {
                        System.out.print((2*(2*n-i)-j)%10);
                    }
                    if(j!=2*(2*n-i)-1) System.out.print(" ");
                }
            }
            for(int j=i+1; j<=n; j++) {
                System.out.print("  ");
            }
            for(int j=n; j<i; j++) {
                System.out.print("  ");
            }
            if(i!=2*n-1) System.out.println();
        }
    }
}
