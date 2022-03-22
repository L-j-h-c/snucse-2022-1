public class FractionalNumberCalculator {
	private static char operator = ' ';
	private static String firstFraction;
	private static String secondFraction;
	private static int numerator;
	private static int denominator;

	public static void printCalculationResult(String equation) {
		splitEquation(equation);
		FractionalNumber fn = new FractionalNumber();
		fn.gcd(numerator, denominator);
		System.out.println(fn.getFraction());
	}

	// equation을 받아서 연산자, num1, num2로 나눠주는 메서드
	private static void splitEquation(String equation) {
		for(int i=0; i<=30 ;i++) {
			if(equation.charAt(i)==' ') {
				firstFraction = equation.substring(0,i);
				operator = equation.charAt(i + 1);
				secondFraction = equation.substring(i + 3);
				break;
			}
		}
		splitEachNumber();
	}

	// 각각의 분수들을 '/'를 전후로 분리
	private static void splitEachNumber() {
		int firstNumerator = 1;
		int firstDenominator = 1;
		int secondNumerator = 1;
		int secondDenominator = 1;

		for(int i=0; i<firstFraction.length() ;i++) {
			if(firstFraction.charAt(i)=='/') {
				firstNumerator = Integer.parseInt(firstFraction.substring(0,i));
				firstDenominator = Integer.parseInt(firstFraction.substring(i+1));
				break;
			} else {
				if(i==firstFraction.length()-1) firstNumerator = Integer.parseInt(firstFraction);
			}
		}
		for(int i=0; i<secondFraction.length() ;i++) {
			if(secondFraction.charAt(i)=='/') {
				secondNumerator = Integer.parseInt(secondFraction.substring(0,i));
				secondDenominator = Integer.parseInt(secondFraction.substring(i+1));
				break;
			} else {
				if(i==secondFraction.length()-1) secondNumerator = Integer.parseInt(secondFraction);
			}
		}

		calculate(firstNumerator, firstDenominator, secondNumerator, secondDenominator);
	}

	private static void calculate(int fn, int fd, int sn, int sd) {
		switch (operator) {
			case '+':
				numerator = fn*sd+sn*fd;
				denominator = fd*sd;
				break;
			case '-':
				numerator = fn*sd-sn*fd;
				denominator = fd*sd;
				break;
			case '*':
				numerator = fn*sn;
				denominator = fd*sd;
				break;
			case '/':
				numerator = fn*sd;
				denominator = fd*sn;
				break;
			default:
				System.out.println("error");
		}
	}
}

class FractionalNumber {
	private int numerator;
	private int denominator;

	// 최대공약수로 나눠주는 메서드
	public void gcd(int n, int d) {
		numerator = n;
		denominator = d;
		int gcd = 1;
		for(int i=1; i<=d; i++) {
			if(n%i==0 && d%i==0) {
				gcd = i;
			}
		}
		numerator /= gcd;
		denominator /= gcd;
	}

	// 계산된 분모와 분자를 return하는 메서드
	public String getFraction() {
		if (this.denominator==1) return Integer.toString(this.numerator);
		else {
			return (Integer.toString(this.numerator)+"/"+Integer.toString(this.denominator));
		}
	}
}
