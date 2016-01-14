package com.mytest.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mytest.twitter.TwitterCaller;

public class TestTwitterCaller {
	

	
	private TwitterCaller caller;

	@Test
	public void testInvalidName() {
		try {
			caller = new TwitterCaller("jalsjw");
			Assert.assertFalse("Invalid name should lead to exception", false);
		} catch (Exception e) {
			Assert.assertTrue("Exception thrown correctly", true);
		}
	}

	@Test
	public void testTweetCount() {
		caller = new TwitterCaller("SidAceJog");
		Assert.assertEquals(158, caller.getTweetCount());
	}

	@Test
	public void testFollowers() {
		caller = new TwitterCaller("SidAceJog");
		List<String> followers = caller.getFollowerList();
		Assert.assertEquals(42, followers.size());
		Assert.assertTrue(followers.contains("Mihir Kaulgi"));

	}

	@Test
	public void testFollowed() {
		caller = new TwitterCaller("SidAceJog");
		List<String> following = caller.getFollowingList();
		Assert.assertEquals(77, following.size());
		Assert.assertTrue(following.contains("Mihir Kaulgi"));

	}

	@Test
	public void testNoReTweets() {
		caller = new TwitterCaller("SidAceJog");
		List<String> lastFiveTweets = caller.getLastFiveTweets(false);
		Assert.assertEquals(5, lastFiveTweets.size());
		for (String tweet : lastFiveTweets) {
			Assert.assertTrue("Retweets not allowed", !tweet.startsWith("RT"));
		}
	}

}
