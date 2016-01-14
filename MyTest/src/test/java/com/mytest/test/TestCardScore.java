package com.mytest.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mytest.carddeck.BlackJack;
import com.mytest.carddeck.Card;

public class TestCardScore {

	private Card card1 = null;
	private Card card2 = null;

	@Before
	public void initialize() {
		card1 = new Card(Card.CardValue.ACE, Card.CardSuit.DIAMONDS);
		card2 = new Card(Card.CardValue.SEVEN, Card.CardSuit.SPADES);
		

	}

	@Test
	public void testCardScore() {
		BlackJack blackJack = new BlackJack(card1, card2);
		int expectedValue = 18;
		Assert.assertEquals("Score calculation Error", expectedValue,
				blackJack.getBlackJackScore());
	}

	@Test
	public void testNullCard() {

		try {
			BlackJack blackJack = new BlackJack(card1, null);
			blackJack.getBlackJackScore();
			Assert.assertTrue("Exception Should have been thrown", false);
		} catch (RuntimeException exception) {
			Assert.assertTrue(true);
		}
	}
}
