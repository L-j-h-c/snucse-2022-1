public class DecreasingString {
    public static void printLongestDecreasingSubstringLength(String inputString) {
        int length = inputString.length();
        int tmpCount = 1;
        int finalCount = 1;
        for(int i=0; i<length-1; i++) {
            if(inputString.charAt(i)>inputString.charAt(i+1)) {
                tmpCount++;
                if (finalCount<tmpCount) {
                    finalCount = tmpCount;
                }
            } else {
                tmpCount = 1;
            }
        }
        System.out.println(finalCount);
    }
}
