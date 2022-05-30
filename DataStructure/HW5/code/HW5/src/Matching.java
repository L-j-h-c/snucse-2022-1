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
					table.put(hashStr, new Pair<>(yPos,i));
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
		LinkedList<Pair<Integer,Integer>> initList = new LinkedList<>();

		for(int i=0; i<pattern.length()-5; i++) {
			String substr = pattern.substring(i,i+6);
			HashableString hashStr = new HashableString(substr);

			AVLNode<HashableString, Pair<Integer,Integer>> testList = table.search(hashStr);

			if(i==0) {
				// 맨 처음에 아예 맞는것들이 업을때 0,0을 프린트
				if(testList==null||testList.data.isEmpty()||testList.data.getFirst()==null) {
					System.out.println("(0, 0)");
					return;
				} // 그렇지 않으면 반환리스트에 처음 애들을 추가

				else {
					initList.addAll(testList.data);
				}
			}
			// 처음에 서치한 것으로부터 점점 반환리스트를 줄여나갈 것임
			else {
				if(testList==null||testList.data.isEmpty()||testList.data.getFirst()==null) {
					System.out.println("(0, 0)");
					return;
				}
				LinkedList<Pair<Integer,Integer>> finalList = new LinkedList<>();
				for(Pair<Integer,Integer> p : initList) {
					for(Pair<Integer,Integer> t : testList.data) {
						if(t.row==p.row&&t.column==p.column+i) {
							finalList.add(p);
						}
					}
				}
				initList = finalList;
				if(initList==null) {
					System.out.println("(0, 0)");
					return;
				}
			}
		} // 모든 패턴에 대해 검사 완료

		Collections.sort(initList);
		int count = 0;
		for(Pair<Integer,Integer> p : initList) {
			System.out.print(p);
			count++;
			if(initList.size() != count) System.out.print(" ");
		}
		System.out.println();
	}
}
