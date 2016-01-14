package com.mytest.carddeck;

public class Card {

	private CardValue cardValue;

	private CardSuit cardSuit;

	public Card(CardValue cardValue, CardSuit cardSuit) {
		super();
		this.cardValue = cardValue;
		this.cardSuit = cardSuit;
	}

	public CardValue getCardValue() {
		return cardValue;
	}

	public CardSuit getCardSuit() {
		return cardSuit;
	}

	public enum CardValue {
		TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(
				10), ACE(11), KING(10), QUEEN(10), JACK(11);

		private CardValue(int valueScore) {
			this.valueScore = valueScore;
		}

		private int valueScore;

		public int getValueScore() {
			return this.valueScore;
		}

	}

	public enum CardSuit {
		SPADES, HEARTS, CLUBS, DIAMONDS;
	}

}
