import java.io.*;
import java.util.*;

public class Subway
{
    public static void main(String args[])
    {

        // 역에 대한 정보를 저장하는 부분
        String fileName = args[0];
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                // 정보 저장하기
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 쿼리를 받아서 최소 시간을 출력하는 부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            try
            {
                String input = br.readLine();
                if (input.compareTo("QUIT") == 0)
                    break;

                makePath(input);
            }
            catch (IOException e)
            {
                System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
            }
        }
    }

    private static void makePath(String input)
    {
        String beginSt = input.substring(2);
        String destinationSt = input.substring(2);
    }
}