import java.io.*;
import java.util.*;

public class Matching
{
	private static HashAVLTable table = new HashAVLTable<String, Pair<Integer,Integer>>();
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;

				command(input);
			}
			catch (IOException e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
		char command = input.charAt(0);
		String content = input.substring(2);

		switch(command) {
			case '<' :
				loadData(content);
				break;
			case '@' :
				printStringByIndex(Integer.parseInt(content));
				break;
			case '?' :
				searchPatternString(content);
				break;
			default :
				System.out.println("오류");
		}
	}

	private static void loadData(String fileName) {
		table = new HashAVLTable<String, Pair<Integer,Integer>>();

		int yPos = 1;
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String line = scanner.nextLine();

				for(int i=1; i<=line.length()-5; i++) {
					String substr = line.substring(i-1,i+5);
					HashableString hashStr = new HashableString(substr);
					table.put(hashStr, new Pair<>(i,yPos));
				}
				yPos++;
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printStringByIndex(int index) {
		table.printTree(index);
	}

	private static void searchPatternString(String pattern) {

	}
}
