import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class CalculatorTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;

				command(input);
			}
			catch (Exception e)
			{
				System.out.println("ERROR");
			}
		}
	}

	private static void command(String input)
	{
		ChangeExpression changer = new ChangeExpression(input);
		changer.makePostFix();
		//계산 하는 자리
		changer.printPostfix();
	}
}

interface changeExpressionInterface {

}

enum Operator {
	// 5순위
	Add, // +
	Sub, // -
	// 4순위
	Mul, // *
	Div, // /
	Rem, // %
	// 3순위
	Unary, // -
	// 2순위
  	pow, // ^
	// 1순위
	LB, // (
	RB // )
}

class ChangeExpression implements changeExpressionInterface
{
	private StringTokenizer st;
	private Stack<String> stack = new Stack();

	private String[] postfix;
	private int postfixCount = 0;


	public ChangeExpression(String input) {
		st = new StringTokenizer(input);
	}

	public void makePostFix() {
		postfix = new String[st.countTokens()];
		boolean wasOp = false;
		while(st.hasMoreTokens()) {
			String str = st.nextToken();

			// 연산자인지 Operator인지 판단하기. 숫자인 경우
			if(isNumber(str.charAt(0))) {
				postfix[postfixCount++] = str;
				wasOp = false;
			}

			// 오퍼레이터인 경우
			else {
				Operator newOp = makeOperator(str);
				// 스택이 비어 있으면 바로 넣어준다.
				// 바로 앞 연산자가 여는 괄호인 경우 - 는 unary이다.
				// postfixcount가 0인 경우 -는 unary이다.
				if(stack.isEmpty()) {
					if(newOp==Operator.Sub) {
						if (postfixCount == 0) stack.push("~");
						else stack.push("-");
					}
					else {
							stack.push(str);
					}
					wasOp = true;
					continue;
				}

				Operator stackedOp = makeOperator(stack.peek());

				// 연산자 우선순위를 비교해서 새로운 것이 높으면 스택에 넣어줌
				if(getOperatorGrade(stackedOp) < getOperatorGrade(newOp)) {
					// '-'인 경우에만 unary인지 구분해서 넣어줌
					if(newOp == Operator.Sub) {
						if(wasOp || stack.peek().equals("(")) stack.push("~");
						else stack.push("-");
						wasOp = true;
						continue;
					}
					stack.push(str);
					wasOp = true;
				}
				// 연산자 우선순위가 같거나 낮은 경우
				else {
					if(newOp == Operator.Sub) {
						if(wasOp) {
							if(stack.peek().equals("~")) stack.push("~");
							else {
								postfix[postfixCount++] = stack.pop();
								stack.push("~");
							}
						}
						else stack.push("-");
					} else {
						postfix[postfixCount++] = stack.pop();
						stack.push(str);
					}
					wasOp = true;
				}
			}
		} // 한 줄 입력을 token화하는 while문 종료. 아래부터는 스택에 남은 것들을 배열에 넣는다.

		while(!stack.isEmpty()) {
			postfix[postfixCount++] = stack.pop();
		}
	}

	private boolean isNumber(char a) {
		if('0'<=a && a<='9') return true;
		else return false;
	}

	public void printPostfix() {
		for(int i = 0; i<postfixCount; i++) {
			System.out.print(postfix[i]);
			if(i!=postfixCount-1) System.out.print(" ");
		}
		System.out.println();
	}

	private Operator makeOperator(String str) {
		char a = str.charAt(0);
		switch(a) {
			case '+': return Operator.Add;
			case '-': return Operator.Sub;
			case '/': return Operator.Div;
			case '%': return Operator.Rem;
			case '*': return Operator.Mul;
			case '~': return Operator.Unary;
			case '(': return Operator.LB;
			case ')': return Operator.RB;
			case '^': return Operator.pow;
			default:
				throw new IllegalStateException("Unexpected value: " + a);
		}
	}

	private int getOperatorGrade(Operator opr) {
		switch(opr) {
			case Add: case Sub: return 0;
			case Mul: case Div: case Rem: return 1;
			case Unary: return 2;
			case pow: return 3;
			case LB: return 4;
			case RB: return 5;
			default:
				throw new IllegalStateException("Unexpected value: " + opr);
		}
	}

	public int calculatePostFix() {
		int result = 0;
		int prevNum;
		int currentNum = 0;

		for(int i = 0; i < postfixCount-1; i++) {
			if(prevNum)
			if(isNumber(postfix[i].charAt(i))) {
				prevNum = Integer.parseInt(postfix[i]);
			}
		}

		return result;
	}
}