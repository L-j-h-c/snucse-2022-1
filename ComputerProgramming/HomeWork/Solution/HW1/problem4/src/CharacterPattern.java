public class CharacterPattern {
    public static void searchCharPattern(String str) {
        for(int i=0; i<=str.length()-3; i++) {
            if(consequtivenessChecker(str, i)!=' ') {
                System.out.print(consequtivenessChecker(str, i));
            }
            if(middleChecker(str, i)!=' ') {
                System.out.print(middleChecker(str, i));
            }
        }
    }

    static char consequtivenessChecker(String str, int i) {
        if (str.charAt(i)+1==str.charAt(i+1) && str.charAt(i+1)+1==str.charAt(i+2)) {
            return str.charAt(i+1);
        }
        return ' ';
    }

    static char middleChecker(String str, int i) {
        if (str.charAt(i)==str.charAt(i+2) && str.charAt(i)-32==str.charAt(i+1)) {
            return str.charAt(i+1);
        }
        return ' ';
    }
}
