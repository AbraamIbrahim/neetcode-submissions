class Twitter {
    private HashMap<Integer, HashSet<Integer>> userTable;
    private PriorityQueue<Tweet> postHeap;
    private int clock;


    public Twitter() {
        // key = user ID; value = list of users they are following
        userTable = new HashMap<>();

        // max heap (sorted by natural ordering: timestamp)
        postHeap = new PriorityQueue<>();

        // used to sort by recency
        clock = 0;
    }

    public void postTweet(int userId, int tweetId) {
        // create tweet obj
        Tweet t = new Tweet(userId, tweetId, clock);

        // add tweet to post heap (max heap)
        postHeap.add(t);

        // update clock
        clock++;
    }

    public List<Integer> getNewsFeed(int userId) {
        // to fill and return
        List<Integer> feed = new ArrayList<>();

        // fetch list of all users user ID is following
        HashSet<Integer> followingList;
        if (userTable.containsKey(userId)) {
            followingList = userTable.get(userId);
        }
        else {
            // user is only "following himself"
            followingList = new HashSet<>();
        }

        //ensure user if following themselves
        if(!followingList.contains((Integer) userId)){followingList.add(userId);}

        // for O(1) lookup of whether or not user ID is following a given poster while parsing postHeap
        Set<Integer> followingSet = new HashSet<>(followingList);

        // fill feed until 10 (or out of tweets to fill)
        boolean feedDone = false;
        PriorityQueue<Tweet> pheapCopy = new PriorityQueue<>(postHeap);

        while (feed.size() < 10 && !pheapCopy.isEmpty()) {
            Tweet topTweet = pheapCopy.poll();

            if (followingSet.contains(topTweet.getPosterID())) {
                feed.add(topTweet.getTweetID());
            }
        }

        // return
        return feed;
    }

    public void follow(int followerId, int followeeId) {
        HashSet<Integer> following;

        if (!userTable.containsKey(followerId)) {
            following = new HashSet<>();
            //prevent double follow entries
            following.add(followeeId);
            userTable.put(followerId, following);
        }
        else {
            following = userTable.get(followerId);
            following.add(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> following;

        if (userTable.containsKey(followerId)) {
            following = userTable.get(followerId);

            if (following.contains(followeeId)) {
                following.remove((Integer)followeeId);
            }
        }
    }
}

public class Tweet implements Comparable<Tweet> {
    private int posterID;
    private int tweetID;
    private int timeStamp;

    public Tweet(int pID, int tID, int ts) {
        posterID = pID;
        tweetID = tID;
        timeStamp = ts;
    }

    public int getTimeStamp() {
        return this.timeStamp;
    }

    public int getPosterID() {
        return this.posterID;
    }

    public int getTweetID(){
        return this.tweetID;
    }

    @Override
    // natural ordering of tweets is largest timestamps come first
    public int compareTo(Tweet other) {
        return Integer.compare(other.getTimeStamp(), this.getTimeStamp());
    }
}