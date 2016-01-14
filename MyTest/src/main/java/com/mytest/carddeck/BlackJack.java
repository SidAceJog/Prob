package com.mytest.carddeck;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {

	public List<Card> cards = new ArrayList<Card>();

	public BlackJack(Card... cards) {
		for (Card card : cards) {
			if (card == null)
				throw new RuntimeException(
						"You must provide some value for the card ! ");
			this.cards.add(card);
		}
	}

	public int getBlackJackScore() {
		int score = 0;

		for (Card card : this.cards) {
			score += card.getCardValue().getValueScore();
		}

		return score;
	}

}
