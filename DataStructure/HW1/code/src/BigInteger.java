import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigInteger
{
    public static final String QUIT_COMMAND = "quit";
    public static final String MSG_INVALID_INPUT = "입력이 잘못되었습니다.";

    // implement this <- 이거 안 써도 됨
    public static final Pattern EXPRESSION_PATTERN = Pattern.compile("");

    static char operator;
    private int[] numArray;
    private char sign;

//    public BigInteger(int i)
//    {
//    }

    public BigInteger(int[] num1)
    {
        numArray = num1;
    }

    public BigInteger(String s)
    {
        numArray = new int[s.length()];
        for(int i=0; i < s.length(); i++) {
            numArray[i] = Character.getNumericValue(s.charAt(i));
        }
    }

    public BigInteger add(BigInteger that)
    {
        int shortLength;
        int longlength;
        BigInteger longOne;
        BigInteger shortOne;
        boolean carry=false;

        if(numArray.length<=that.numArray.length) {
            shortLength = numArray.length;
            longlength = that.numArray.length;
            shortOne = this;
            longOne = that;
        } else {
            shortLength = that.numArray.length;
            longlength = numArray.length;
            shortOne = that;
            longOne = this;
        }
        int[] resultArray = new int[longlength+1];

        if(this.getSign()==that.getSign()) {

                for(int i=0; i<shortLength; i++) {
                    if(carry) {
                        if(longOne.numArray[longlength-1-i]+shortOne.numArray[shortLength-1-i]+1>=10) {
                            resultArray[longlength-i] = longOne.numArray[longlength-1-i]+shortOne.numArray[shortLength-1-i]+1-10;
                            carry = true;
                        } else {
                            resultArray[longlength-i] = longOne.numArray[longlength-1-i]+shortOne.numArray[shortLength-1-i]+1;
                            carry = false;
                        }
                    } else {
                        if(longOne.numArray[longlength-1-i]+shortOne.numArray[shortLength-1-i]>=10) {
                            resultArray[longlength-i] = longOne.numArray[longlength-1-i]+shortOne.numArray[shortLength-1-i]-10;
                            carry = true;
                        } else {
                            resultArray[longlength-i] = longOne.numArray[longlength-1-i]+shortOne.numArray[shortLength-1-i];
                            carry = false;
                        }
                    }
                }
                for(int i=shortLength; i<longlength; i++) {
                    if(carry) {
                        if(1+longOne.numArray[longlength-i-1]>=10) {
                            resultArray[longlength-i]=0;
                            carry = true;
                        } else {
                            resultArray[longlength-i] = 1+longOne.numArray[longlength-i-1];
                            carry = false;
                        }
                    } else {
                        resultArray[longlength-i] = longOne.numArray[longlength-i-1];
                    }
                }
                if(carry) {
                    resultArray[0] = 1;
                } else {
                    int[] array = new int[resultArray.length-1];
                    for(int i=0; i<resultArray.length-1; i++) {
                        array[i] = resultArray[i+1];
                    }
                    BigInteger rt = new BigInteger(array);
                    if(this.getSign()=='+') {
                        rt.setSign('+');
                    } else {
                        rt.setSign('-');
                    }
                    return rt;
                }
            BigInteger rt = new BigInteger(resultArray);
            if(this.getSign()=='+') {
                rt.setSign('+');
            } else {
                rt.setSign('-');
            }
                return rt;
        }
        return that;
    }

    public BigInteger subtract(BigInteger big)
    {
        return big;
    }

    public BigInteger multiply(BigInteger big)
    {
        return big;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        if(getSign()=='-') result.append('-');
        for (int j : numArray) {
            result.append(j);
        }
        return result.toString();
    }

    // 생성한 BigInteger의 부호 얻어오기
    public char getSign() {
        return this.sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    static BigInteger[] makeBigIntegers(String s) {
        BigInteger[] BigIntegers = new BigInteger[2];
        int lastIndex = 0;

        // 첫 문자가 숫자이면
        if(Character.isDigit(s.charAt(0))) {
            for(int i=0; Character.isDigit(s.charAt(i)); i++) {
                lastIndex++;
            }

            // 첫 BigInteger 생성
            String firstString = s.substring(0,lastIndex);
            BigIntegers[0] = new BigInteger(firstString);
            BigIntegers[0].setSign('+');

            // operator 할당
            operator = s.charAt(lastIndex++);
            System.out.println("operator: " + operator);

            // operator의 다음 문자가 숫자이면
            if(Character.isDigit(s.charAt(lastIndex))) {
                String secondString = s.substring(lastIndex, s.length());
                BigIntegers[1] = new BigInteger(secondString);
                BigIntegers[1].setSign('+');
            } else { // 문자이면 그것이 부호이다
                String secondString = s.substring(lastIndex+1, s.length());
                BigIntegers[1] = new BigInteger(secondString);
                BigIntegers[1].setSign(s.charAt(lastIndex));
            }

        } else {
            lastIndex=1;
            for(int i=1; Character.isDigit(s.charAt(i)); i++) {
                lastIndex++;
            }

            // 첫 BigInteger 생성
            String firstString = s.substring(1,lastIndex);
            BigIntegers[0] = new BigInteger(firstString);
            BigIntegers[0].setSign(s.charAt(0));

            // operator 할당
            operator = s.charAt(lastIndex++);
            System.out.println("operator: " + operator);

            // operator의 다음 문자가 숫자이면
            if(Character.isDigit(s.charAt(lastIndex))) {
                String secondString = s.substring(lastIndex, s.length());
                BigIntegers[1] = new BigInteger(secondString);
                BigIntegers[1].setSign('+');
            } else { // 문자이면 그것이 부호이다
                String secondString = s.substring(lastIndex+1, s.length());
                BigIntegers[1] = new BigInteger(secondString);
                BigIntegers[1].setSign(s.charAt(lastIndex));
            }
        }
        return BigIntegers;
    }

    static BigInteger evaluate(String input) throws IllegalArgumentException
    {
        // implement here
        // parse input
        // 공백 제거
        String noSpaceStr = input.replaceAll(" ","");
        BigInteger[] bigIntegers = makeBigIntegers(noSpaceStr);

        BigInteger num1 = bigIntegers[0];
        BigInteger num2 = bigIntegers[1];

        BigInteger result = null;
        switch(operator) {
            case '+':
                result = num1.add(num2);
                break;
            case '-':
                result = num1.subtract(num2);
                break;
            case '*':
                result = num1.multiply(num2);
                break;
        }
        return result;
    }

    public static void main(String[] args) throws Exception
    {
        try (InputStreamReader isr = new InputStreamReader(System.in))
        {
            try (BufferedReader reader = new BufferedReader(isr))
            {
                boolean done = false;
                while (!done)
                {
                    String input = reader.readLine();

                    try
                    {
                        done = processInput(input);
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.err.println(MSG_INVALID_INPUT);
                    }
                }
            }
        }
    }

    static boolean processInput(String input) throws IllegalArgumentException
    {
        boolean quit = isQuitCmd(input);

        if (quit)
        {
            return true;
        }
        else
        {
            BigInteger result = evaluate(input);
            System.out.println(result.toString());

            return false;
        }
    }

    static boolean isQuitCmd(String input)
    {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }
}
