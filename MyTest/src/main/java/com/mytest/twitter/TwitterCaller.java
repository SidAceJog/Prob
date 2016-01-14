package com.mytest.twitter;

import java.util.ArrayList;
import java.util.List;

import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.RateLimitStatusEvent;
import twitter4j.RateLimitStatusListener;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class TwitterCaller {

	private Twitter twitter = null;

	private String userName;

	public TwitterCaller(String userName) {

		this.userName = userName;
		init();
	}

	private void init() {
		twitter = TwitterFactory.getSingleton();
		twitter.addRateLimitStatusListener(new CallerLimitListener());
		try {
			twitter.showUser(userName);
		} catch (TwitterException e) {
			throw new TwitterCallerException(
					"Invalid User Name Supplied. Please supply correct name.");
		}
	}

	private class CallerLimitListener implements RateLimitStatusListener {

		@Override
		public void onRateLimitStatus(RateLimitStatusEvent event) {

		}

		@Override
		public void onRateLimitReached(RateLimitStatusEvent event) {
			throw new TwitterCallerException(
					"Twitter call rate limit has been reached. Please wait for sometime and try again");

		}

	}

	public List<String> getLastFiveTweets(boolean allowRetweets) {
		long index = 1;
		int pageNumber = 1;
		List<String> lastFiveTweetMessages = new ArrayList<String>(5);

		while (index < 6) {
			ResponseList<Status> lastFiveTweets;
			try {
				lastFiveTweets = twitter.getUserTimeline(userName, new Paging(
						pageNumber, 5));
			} catch (TwitterException e) {
				throw new TwitterCallerException(
						"Exception occured while calling twitter", e);
			}
			if (lastFiveTweets.size() == 0)
				break;
			for (Status status : lastFiveTweets) {
				if ((allowRetweets || !status.getText().startsWith("RT"))) {
					lastFiveTweetMessages.add(status.getText());
					index++;
					if (index > 5)
						break;
				}
			}
			pageNumber++;
		}

		return lastFiveTweetMessages;
	}

	public List<String> getFollowerList() {
		long cursor = -1;

		List<String> followersList = new ArrayList<String>();

		while (cursor != 0) {

			PagableResponseList<User> followerList;
			try {
				followerList = twitter.getFollowersList(userName, cursor);
			} catch (TwitterException e) {
				throw new TwitterCallerException(
						"Exception occured while calling twitter", e);
			}

			for (User user : followerList) {
				followersList.add(user.getName());

			}
			cursor = followerList.getNextCursor();
		}

		return followersList;

	}

	public List<String> getFollowingList() {
		long cursor = -1;

		List<String> followedList = new ArrayList<String>();

		while (cursor != 0) {
			PagableResponseList<User> followerList;
			try {
				followerList = twitter.getFriendsList(userName, cursor);
			} catch (TwitterException e) {
				throw new TwitterCallerException(
						"Exception occured while calling twitter", e);
			}

			for (User user : followerList) {
				followedList.add(user.getName());

			}
			cursor = followerList.getNextCursor();
		}

		return followedList;

	}

	public int getTweetCount() {
		try {
			return twitter.showUser(userName).getStatusesCount();
		} catch (TwitterException e) {
			throw new TwitterCallerException(
					"Exception occured while calling twitter", e);
		}
	}

}
