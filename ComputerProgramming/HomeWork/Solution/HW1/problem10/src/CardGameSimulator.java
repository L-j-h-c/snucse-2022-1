public class CardGameSimulator {
	private static final Player[] players = new Player[2];

	public static void simulateCardGame(String inputA, String inputB) {
		// DO NOT change the skeleton code.
		// You can add codes anywhere you want.
	}

	private static void printLoseMessage(Player player) {
		System.out.printf("Player %s loses the game!\n", player);
	}
}

class Player {
	private String name;
	private Card[] deck;
	public Card ownLastCard = new Card();

	// 플레이어 이름 초기화
	public void setName(String name) {
		this.name = name;
	}

	// 플레이어가 가진 카드리스트 초기화
	public void initCardList(String a) {
		String[] splittedInput = a.split(" ");
		deck = new Card[10];
		for(int i=0; i<=9; i++) {
			deck[i] = new Card();
			deck[i].initCard(splittedInput[i]);
		}
	}

	// 플레이어 A의 첫 카드 사용
	public void firstCard() {
		Card[] newDeck = new Card[deck.length-1];
		int highestIndex = 0;
		for(int i=0; i<deck.length; i++) {
			if(deck[i].getNum()>deck[highestIndex].getNum()) {
				highestIndex = i;
			}
		}

		for(int i=0; i<deck.length; i++) {
			if(deck[i].getNum()==deck[highestIndex].getNum()&&deck[i].getLetter()<deck[highestIndex].getLetter()) {
				highestIndex = i;
			}
		}

		playCard(deck[highestIndex]);
		ownLastCard = deck[highestIndex];

		for(int i=0; i<deck.length-1; i++) {
			if(i<highestIndex) {
				newDeck[i] = deck[i];
			}
			else {
				newDeck[i] = deck[i+1];
			}
		}
		deck = newDeck;
	}

	// 플레이어의 카드 중에 사용된 것을 제거하고,
	public Player useDeck(Card lastcard) {
		int selectedIndex = 0;
		boolean notSameNumber = false;
		boolean notSameLetter = false;

		if(deck.length==0) return this;
		for(int i=0; i<deck.length; i++) {
			if(deck[i].getNum()==lastcard.getNum()) {
				selectedIndex = i;
				break;
			}

			if(i==deck.length-1) {
				notSameNumber = true;
				for(int j=0; j<deck.length; j++) {
					if (deck[j].getLetter() == lastcard.getLetter()) {
						selectedIndex = j;
						break;
					}
				}
			}
		}

		if(notSameNumber) {
			for(int i=0; i<deck.length; i++) {
				if(deck[i].getLetter()== lastcard.getLetter() && deck[i].getNum()>deck[selectedIndex].getNum()) {
					selectedIndex = i;
					break;
				}
				if(i==deck.length-1 && deck[selectedIndex].getLetter()!=lastcard.getLetter()) notSameLetter = true;
			}
		} else {
			for(int i=0; i<deck.length; i++) {
				if(deck[i].getNum()==lastcard.getNum() && deck[i].getLetter()<=deck[selectedIndex].getLetter()) {
					selectedIndex = i;
				}
			}
		}

		if(notSameLetter) return this;

		ownLastCard = deck[selectedIndex];
		playCard(deck[selectedIndex]);

		Card[] newDeck = new Card[deck.length-1];
		for(int i=0; i<deck.length-1; i++) {
			if(i<selectedIndex) {
				newDeck[i] = deck[i];
			}
			else {
				newDeck[i] = deck[i+1];
			}
		}
		this.deck = newDeck;
		return null;
	}

	public void playCard(Card card) {
		System.out.printf("Player %s: %s\n", name, card);
	}

	@Override
	public String toString() {
		return name;
	}
}

class Card {
	private char number; // int
	private char letter;

	public void initCard(String a) {
		this.number = a.charAt(0);
		this.letter = a.charAt(1);
	}

	public int getNum() {
		String st = Character.toString(number);

		return Integer.parseInt(st);
	}

	public char getLetter() {
		return letter;
	}

	@Override
	public String toString() {
		return "" + number + letter;
	}
}
