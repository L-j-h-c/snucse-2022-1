public class HexNumberCounter {
    static int i = 0;
    static int k = 0;

    public static void countHexNumbers(int n) {
        char[] numArr  = new char[15];
        k=0;
        int[] countArr = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        numArr = convertNumber(n, numArr, countArr);
        for(int j=k; j>=0; j--) {
            System.out.print(numArr[j]);
        }
        System.out.println();
        for(int j=0; j<=15; j++) {
            if(countArr[j]!=0) {
                printNumberCount(convertToChar(j),countArr[j]);
            }
        }
    }

    static char[] convertNumber(int n, char[] numArr, int[] countArr) {
        if(n/16==0) {
            countArr[n%16] = countArr[n%16]+1;
            numArr[i++] = convertToChar(n);
            k=i-1;
            i=0;
            return numArr;
        } else {
            countArr[n%16] = countArr[n%16]+1;
            numArr[i++] = convertToChar(n);
            return convertNumber(n/16, numArr, countArr);
        }
    }

    static char convertToChar(int n) {
        if(n%16>=10) {
            switch(n%16){
                case 10 :
                    return 'a';
                case 11 :
                    return 'b';
                case 12 :
                    return 'c';
                case 13 :
                    return 'd';
                case 14 :
                    return 'e';
                default :
                    return 'f';
            }
        } else {
            return Integer.toString(n%16).charAt(0);
        }
    }

    private static void printNumberCount(char number, int count) {
        System.out.printf("%c: %d times\n", number, count);
    }
}
