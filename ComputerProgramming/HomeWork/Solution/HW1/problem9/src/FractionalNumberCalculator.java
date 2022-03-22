public class FractionalNumberCalculator {
	public static void printCalculationResult(String equation) {
		// DO NOT change the skeleton code.
		// You can add codes anywhere you want.
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
