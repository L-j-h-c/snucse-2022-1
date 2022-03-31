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

    public BigInteger(int i)
    {
    }

    public BigInteger(int[] num1)
    {
    }

    public BigInteger(String s)
    {
        numArray = new int[s.length()];
        for(int i=0; i < s.length(); i++) {
//            System.out.print(Character.getNumericValue(s.charAt(i)));
            numArray[i] = Character.getNumericValue(s.charAt(i));
        }
    }

//    public BigInteger add(BigInteger big)
//    {
//    }
//
//    public BigInteger subtract(BigInteger big)
//    {
//    }
//
//    public BigInteger multiply(BigInteger big)
//    {
//    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (int j : numArray) {
            result.append(j);
        }
        return result.toString();
    }

    // 생성한 BigInteger의 부호 얻어오기
    public char getSign(BigInteger num) {
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
        // BigInteger num1 = new BigInteger(arg1);
        // BigInteger num2 = new BigInteger(arg2);
        // BigInteger result = num1.add(num2);
        // return result;
        return num2;
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
