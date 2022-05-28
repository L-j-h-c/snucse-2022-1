import java.io.*;

public class Matching
{
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
		char command = input.substring(0).charAt(0);
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

	}

	private static void printStringByIndex(int index) {

	}

	private static void searchPatternString(String pattern) {

	}
}
